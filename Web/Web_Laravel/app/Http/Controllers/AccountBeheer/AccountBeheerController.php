<?php

namespace App\Http\Controllers\AccountBeheer;


use Illuminate\Http\Request;
use \App\Models\Users\Docent;
use \App\Models\Users\Admin;
use \App\Models\Users\User;
use \App\Models\Users\Student;
use \App\Models\Users\UserTemp;
use \App\Models\Courses\Course;
use \App\Models\Vakken\Vak;
use \App\Http\Controllers\BaseController;
use \App\Http\Controllers\AuthController;
use App\Http\Controllers\Vakken\VakkenController;
use Illuminate\Support\Facades\DB;
use View;
use Session;
use Redirect;
use Illuminate\Support\Facades\Http;
use Illuminate\Support\Arr;

class AccountBeheerController extends BaseController
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
        // init cour
        //decode request to proper array with assosciation (thats why second param. = true !!)
        //$result = json_encode( $sessionData, true);
        $AccountViewModel = new User();
        $AccountViewModel->fill($sessionData);
        $lowerCaseType = strtolower($AccountViewModel->type);
        switch ($lowerCaseType) {
            case "admin":
                $admin = new Admin();
                $admin->fill($AccountViewModel);
                $admin->userList = Self::GetUserListAll($admin->userKey, 'admin');
                $admin->vakkenList = VakkenController::GetVakkenList($admin->userKey);
                return view('AccountBeheer/Gegevens.Overview')->with('AccountViewModel', $admin);
                break;
            case 'docent':
                $docent = new Docent();
                $docent->fill($AccountViewModel);
                $docent->userList = Self::GetUserStudents($docent->userKey, 'docent');
                return view('AccountBeheer/Gegevens.Overview')->with('AccountViewModel', $docent);
                break;
            case "student":
                $student = new Student();
                $student->fill($student);
                return view('AccountBeheer/Gegevens.Overview')->with('AccountViewModel', $AccountViewModel);
                break;
        }
        // fallback
        return view('AccountBeheer/Gegevens.Overview')->with('AccountViewModel', $AccountViewModel);
    }


    public function changeUserRole(Request $request){

        $user = AuthController::CheckUser();
        $apiArray = array();
        $apiArray['userkey'] = $user->userKey;
        $apiArray['id'] = $request->input('id');
        $newRol = strtoupper($request->rollen[0]);
        $apiArray['nieuwerol'] = $newRol;
        if($newRol == "DOCENT"){
            $apiArray['vakid'] = 2;
        }
        elseif($newRol == "STUDENT") {
            $apiArray["opleidingid"] = 1;
        }

        $response = Http::post('api.brielage.com:8081/user/rol/', $apiArray);

       // check if success is true
        if (boolval($response["success"])) {    
            $errorArray["succesvol_aangemaakt"] = true;
            Session::put("errorsApi", $errorArray);
            return Redirect::back();

         } else {
            foreach ($response['errors'] as $key => $value) {
                $errorArray[$key] = $value;
            }
            Session::put("errorsApi", $errorArray);
            return Redirect::back();
        }  
        
        
    }

    // GET ALL USERS (ENKEL ALS ADMIN)
    public function GetUserListAll($userKey, $type)
    {
        // link voor api
        $apiArray = array();
        $apiArray['userkey'] = $userKey;
        $apiArray['rol'] = "ALL";
        $response = Http::post('api.brielage.com:8081/user/list', $apiArray );

        
        $response = json_decode($response, true);

        // check if success is true
        if (boolval($response["success"])) {    
            Session::put('rol', $response['eigenrol']);  
            return $response;
        } else {
            foreach ($response['errors'] as $key => $value) {
                $errorArray[$key] = $value;
            }
            Session::put("errorsApi", $errorArray);
            return view('AccountBeheer/Gegevens.Overview');
        }   

    }

    // GET ALL USERS (ENKEL ALS ADMIN)
    public function GetUserStudents($userKey, $type)
    {
        // link voor api
        $apiArray = array();
        $apiArray['userkey'] = $userKey;
        $apiArray['rol'] = "STUDENT";
        $response = Http::post('api.brielage.com:8081/user/list', $apiArray );

        
        $response = json_decode($response, true);

        // check if success is true
        if (boolval($response["success"])) {    
            Session::put('rol', $response['eigenrol']);  
            return $response;
        } else {
            foreach ($response['errors'] as $key => $value) {
                $errorArray[$key] = $value;
            }
            Session::put("errorsApi", $errorArray);
            return view('AccountBeheer/Gegevens.Overview');
        }   

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
                        $sessionData = Session::get('userData');
                        //decode request to proper object (thats why second param. = true !!)
                        //$result = json_encode( $sessionData, true);
                        $AccountViewModel = new User();
                        $AccountViewModel->fill($sessionData);
                    }
                    break;
                case 'userkey':
                    $AccountViewModel->userKey = $output;
                    if (Session::has('userData')) { }
                    // put user data in session called 'userData'
                    Session::put('userData', $AccountViewModel);
                    Session::Save();
                    break;
            }
        }
        return Redirect::back();

        //return Redirect::Back();
    }


    public function update(Request $request)
    {

        // get local user data
        $sessionData = Session::get('userData');
        //decode request to proper object (thats why second param. = true !!)        
        $AccountViewModel = new User();
        $AccountViewModel->fill($sessionData);

        $response = Self::getResponseBasedOnInputs($AccountViewModel, $request);
        $response = json_decode($response, true);

        if ($response != null) {
            if (boolval($response['success'])) {
                // retrieve user from db based on input email
                $user = DB::table('users')->where('email', $AccountViewModel->email)->first();
                // if user exists
                if ($user != null) { // create md5 hashing appended with salt retrieved from local db
                    DB::table('users')->where('email', $AccountViewModel->email)->update(['email' => $request->input('email')]);
                } else {
                    // if user doesn't exist don't return will error messages
                    return Redirect::back()->withErrors(['Foute username/wachtwoord combinatie!'])->withInput($request->all());
                }
                Self::genUserForSession($response);
                $errorArray["succesvol_aangemaakt"] = true;
            Session::put("errorsApi", $errorArray);
            return Redirect::back();
            } else {
                $errorArray = array('andere' => true);
            }
        } else {
            $errorArray = array('geen_aanpassingen' => true);
            Session::put("errorsApi", $errorArray);
            return Redirect::back();
        }
      
    }


    function getResponseBasedOnInputs(User $user, Request $request)
    {
        // get all inputs
        $userKey = $request->input('userKey');
        $upFirstName = $request->input('voornaam');
        $upEmail = $request->input('email');
        $upLastName = $request->input('familienaam');
        $upAvatarpad = $request->input('avatarPad');
        $upPassword = $request->input('password');


        // BUILD API RESPONSE Array

        $apiArray = array();
        $changed = false;


        if ($user->voornaam != $upFirstName) {
            $changed = true;
            $apiArray['voornaam'] = $upFirstName;
        };
        if ($user->familienaam != $upLastName) {
            $changed = true;
            $apiArray['familienaam'] = $upLastName;
        };
        if ($user->avatarPad != $upAvatarpad) {
            $changed = true;
            $apiArray['avatarpad'] = $upAvatarpad;
        };
        if ($user->email != $upEmail) {
            $changed = true;
            $apiArray['email'] = $upEmail;
        };
        if ($changed) {
            $apiArray['userkey'] = $userKey;
            return Http::post('api.brielage.com:8081/user/edit', $apiArray);
        }
        return Redirect::back();


        //return View::make('test/test')->with('testResponse', $apiarray);

    }

    function genUserForSession($apiResponse)
    {

        foreach ($apiResponse as $key => $output) {
            switch ($key) {
                case 'success':
                    if (!boolval($output)) {
                        $errorArray = array();
                        foreach ($apiResponse['errors'] as $key => $value) {
                            $errorArray[$key] = $value;
                        }
                        Session::put("errorsApi", $errorArray);
                        return Redirect::back();
                    } else {
                        $user = new User();
                        // loop over the rest of the key values
                        foreach ($apiResponse as $keyInner => $outputInner) {
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
                                case 'rol':
                                    $user->type = $outputInner;
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
                            }
                        }
                        // retrieve old UserKey

                        $oldUser = Session::get('userData');
                        $AccountViewModel = new User();
                        $AccountViewModel->fill($oldUser);
                        $user->userKey = $AccountViewModel->userKey;
                        $user->vakken = $AccountViewModel->vakken;
                        // put user data in session called 'userData'
                        Session::put('userData', $user);
                        // save the session
                        Session::save();
                    }
                    break;
            }
        }
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
