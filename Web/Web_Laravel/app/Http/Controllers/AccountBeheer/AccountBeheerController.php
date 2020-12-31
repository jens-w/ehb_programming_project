<?php

namespace App\Http\Controllers\AccountBeheer;


use Illuminate\Http\Request;
use \App\Models\Users\Student;
use \App\Models\Users\Teacher;
use \App\Models\Users\Admin;
use \App\Models\Users\User;
use \App\Models\Vakken\Vak;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Log;
use View;
use Illuminate\Support\Collection;
use Session;
use Symfony\Component\HttpFoundation\JsonResponse;
use Redirect;
use Illuminate\Support\Facades\Http;
use PhpParser\Node\Expr\List_;

class AccountBeheerController extends \App\Http\Controllers\Controller
{

    public function index()
    {

        // check wether you have a session active (komende dus van verificatie api) 
        if (!Session::has('userData')) {
            return Redirect('/loginInit')->withErrors(['Je moet aangemeld zijn om je account te kunnen bekijken']);
        }
        /* ACCOUNT */

        // if not, get the data and create model
        $sessionData = Session::get('userData');
        //decode request to proper object (thats why second param. = true !!)
        //$result = json_encode( $sessionData, true);
        $AccountViewModel = new User();
        $AccountViewModel->fill($sessionData);
        return view('AccountBeheer/Gegevens.Overview')->with('AccountViewModel', $AccountViewModel);
    }

    public function GetJsonDummyDataAccount()
    {
        // creeër dummy data ( parsen naar Json )
        $loggedInUser = session()->get('dataUser')->toJson();
        return $loggedInUser;
    }


    public function GetAccountInfo(Request $request)
    {
        /* get dummy data -> this will be replaced with api response ----------
        $response = Http::get('api.brielage.com:8088/user/something/')->json(); */

        // SELF:: is needed when calling methods within same controller!
        $response = Self::GetJsonDummyDataAccount();

        // retrieve request, parse to json 
        // getData() = removes header information from reques, BELANGERIJK!
        $rawJson = response()->json($request->all())->getData();

        //encode request to proper json
        $decodedAsArray = json_encode($response, true);
        //decode request to proper array (thats why second param. = true !!)
        $result = json_decode($decodedAsArray, true);
        // we use 'request' as param since the main 'node' of our requested json in this istance is 'request'
        // request example: {request : { "id" : "1" , "naam" : "michael"}}...
        // $innerPost = $result['user'];

        // create user object based on given array and its attributes
        $user = new User();
        $user->forceFill($result);

        // return view 
        return View::make('AccountBeheer/Gegevens.AccountInfo')->with('AccountViewModel', $user);
    }

    public function NewUserKey(Request $request)
    {
        $response = Http::post('api.brielage.com:8081/user/userkey', [
            'userkey' => $request->input('userKey'),
        ]);

        $decodedArray = json_decode($response, true);
        // check response 
        foreach ($decodedArray as $key => $output) {
            switch ($key) {
                case 'success':
                    if (boolval($output)) { //true, userkey request         
                        // if not, get the data and create model
                        $sessionData = Session::pull('userData');
                        //decode request to proper object (thats why second param. = true !!)
                        //$result = json_encode( $sessionData, true);
                        $AccountViewModel = new User();
                        $AccountViewModel->fill($sessionData);
                    }
                    break;
                case 'userkey':
                    $AccountViewModel->userKey = $output;
                    // put user data in session called 'userData'
                    Session::put('userData', $AccountViewModel);
                    Session::Save();
                    return View::make('AccountBeheer/Gegevens.Overview')->with('AccountViewModel', $AccountViewModel);
                    break;
            }
        }

        //return Redirect::Back();
    }


    public function update(Request $request)
    {
        // get all inputs
        $userKey = $request->input('userKey');
        $upFirstName = $request->input('voornaam');
        $upEmail = $request->input('email');
        $upLastName = $request->input('familienaam');
        $upAvatarpad = $request->input('avatarPad');
        $upPassword = $request->input('password');

        // retrieve user from db based on input email
        $user = DB::table('users')->where('email', $request->input('email'))->first();
        $user = DB::table('users')->where('email', 'michaelbracke@hotmail.com')->first();
        // if user exists
        if ($user != null) { // create md5 hashing appended with salt retrieved from local db
            $hashedPassword = md5($upPassword . $user->salt);
        } else {
            // if user doesn't exist don't return will error messages

        }

        $response = Http::post('api.brielage.com:8081/user/edit/', [
            'userkey' => $userKey,
            'voornaam' => $upFirstName,
            'familienaam' => $upLastName,
            'email' => $upEmail,
            'avatarpad' =>  $upAvatarpad,
            "password" =>  $hashedPassword
        ]);

        $decodedArray = json_decode($response, true);


        function genUserForSession($apiResponse)
        {
            $decodedArray = json_decode($apiResponse, true);
            foreach ($decodedArray as $key => $output) {
                switch ($key) {
                    case 'success':
                        if (!boolval($output)) {
                            foreach ($decodedArray as $key => $output) {
                                if ($key === 'errors') { }
                            }
                        } else {
                            $user = new User();
                            // loop over the rest of the key values
                            foreach ($decodedArray as $keyInner => $outputInner) {
                                switch ($keyInner) {
                                    case 'voornaam':
                                        $user->voornaam = $outputInner;
                                        break;
                                    case 'familienaam':
                                        $user->familienaam = $outputInner;
                                        break;
                                    case 'email':
                                        $user->email = $outputInner;
                                        break;
                                    case 'avatarpad':
                                        break;
                                    case 'eigenrol':
                                        break;
                                    case 'opleiding':
                                        foreach ($output as $key => $innerOutput) { }
                                        break;
                                    case 'vakken':
                                        $vakkenList = [];
                                        foreach ($outputInner as $key => $out) {
                                            // define vak with class - init the var.
                                            $vak = new Vak();
                                            // fill the object (vak) with the array data
                                            $vak->fill($out);
                                            // add each 'vak' to an array called 'vakkenList'
                                            $vakkenList[] = $vak;
                                        }
                                        $user->vakken = $vakkenList;
                                        break;
                                    case 'userkey':
                                        $user->userKey = $output;
                                        break;
                                    case 'rol':
                                        $user->type = $output;
                                        break;
                                }
                            }
                            // put user data in session called 'userData'
                            Session::put('userData', $user);
                            // save the session
                            Session::save();
                           
                        }
                        break;
                }
            }
        }

        return View::make('AccountBeheer/Gegevens.Overview')->with('AccountViewModel', $user);


        // set sql statement
        $sql = "UPDATE users SET voornaam=?, email=? WHERE Id= ?";
        DB::update($sql, array($upFirstName, $upEmail, $user->id));

        // return redirect with error messages containing
        // I use an array to be send with the messages, first value is the 'style classe' to be used, second value is the actual value of the msg
        return Redirect::back()->withErrors(['Succesvol ge-updated!']);
    }


    public function TestApiRequest()
    {
        // test naar api
        $response = Http::post('api.brielage.com:8088/user/something/', [
            'naam' => 'Steve',
            'password' => '#hash12345',
        ]);
        return View::make('test/test')->with('testResponse', $response);
    }
}
