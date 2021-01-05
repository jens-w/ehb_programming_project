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
use Illuminate\Support\Facades\DB;
use View;
use Session;
use Redirect;
use Illuminate\Support\Facades\Http;


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
        //decode request to proper object (thats why second param. = true !!)
        //$result = json_encode( $sessionData, true);
        $AccountViewModel = new User();
        $AccountViewModel->fill($sessionData);
        $lowerCaseType = strtolower($AccountViewModel->type);
        switch ($lowerCaseType) {
            case "admin":
                $admin = new Admin();
                $admin->fill($AccountViewModel);
                $admin->userList = Self::GetUserList($admin->userKey, 'admin');
                // enkel admin kan courses zien
                $admin->coursesList = $this->courseService::GetCoursesList($admin->userKey, 'admin');
                return view('AccountBeheer/Gegevens.Overview_Admin')->with('AccountViewModel', $admin);
                break;
            case 'docent':
                $docent = new Docent();
                $docent->fill($AccountViewModel);
                $docent->userList = Self::GetUserList($docent->userKey, 'docent');
                $docent->coursesList = $this->courseService::GetCoursesList($docent->userKey, 'docent');
                return view('AccountBeheer/Gegevens.Overview_Docent')->with('AccountViewModel', $docent);
                break;
            case "student":
                $student = new Student();
                $student -> fill($student);
                
                return view('AccountBeheer/Gegevens.Overview')->with('AccountViewModel', $AccountViewModel);
                break;
        }
        // fallback
        return view('AccountBeheer/Gegevens.Overview')->with('AccountViewModel', $AccountViewModel);
    }

    public function GetUserList($userKey, $type)
    {
        // link voor api
        /* $response = Http::post('URL HERE',  [
               'userkey' => $userKey]
            ); */

        $response = '{
			"success" : true,
			"eigenrol" : "Docent",
			"users" : {
				"user1" : {
					"id" : 38,
					"voornaam" : "een_voornaam",
					"familienaam" : "een_familienaam",
					"email" : "een@ema.il",
					"rol" : "student"
				},
				"user2" : {
					"id" : 12,
					"voornaam" : "een_voornaam",
					"familienaam" : "een_familienaam",
					"email" : "een@ema.il",
					"rol" : "docent"
				}
			}
        }';

        $decodedArray = json_decode($response, true);
        foreach ($decodedArray as $key => $output) {
            switch ($key) {
                case 'success':
                    if (!boolval($output)) {
                        foreach ($decodedArray as $key => $output) {
                            if ($key === 'errors') { }
                        }
                    } else {
                        // if not, get the data and create model
                        $sessionData = Session::get('userData');
                        //decode request to proper object (thats why second param. = true !!)
                        //$result = json_encode( $sessionData, true);
                        if ($type == 'docent') {
                            $AccountViewModel = new Docent();
                        } else {
                            $AccountViewModel = new Admin();
                        }
                        $AccountViewModel->fill($sessionData);
                        // loop over the rest of the key values
                        foreach ($decodedArray as $keyInner => $outputInner) {
                            switch ($keyInner) {
                                case 'eigenrol':
                                    $AccountViewModel->type = $outputInner;
                                    break;
                                case 'users':
                                    $userList = [];
                                    foreach ($outputInner as $key => $out) {
                                        // define vak with class - init the var.
                                        $user = new UserTemp();
                                        // fill the object (vak) with the array data
                                        $user->fill($out);
                                        // add each 'vak' to an array called 'vakkenList'
                                        $userList[] = $user;
                                    }

                                    break;
                            }
                        }
                        if (Session::has('userData')) {
                            Session::forget('userData');
                        }
                        if (Session::has('userListForCourse')) {
                            Session::forget('userListForCourse');
                        }
                        // put user data in session called 'userData'
                        Session::put('userData', $AccountViewModel);
                        Session::put('userListForCourse', $userList);
                        // save the session
                        Session::save();
                    }
                    break;
            }
        }
        return $userList;
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
                    if (Session::has('userData')) {
                        Session::forget('userData');
                    }
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
        // get all inputs
        $userKey = $request->input('userKey');
        $upFirstName = $request->input('voornaam');
        $upEmail = $request->input('email');
        $upLastName = $request->input('familienaam');
        $upAvatarpad = $request->input('avatarPad');
        $upPassword = $request->input('password');

        // retrieve user from db based on input email
        $user = DB::table('users')->where('email', 'michaelbracke@hotmail.com')->first();
        //$user = DB::table('users')->where('email', $request->input('email'))->first();
        //$user = DB::table('users')->where('email', 'michaelbracke@hotmail.com')->first();
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



        Self::genUserForSession($response);

        // return redirect with error messages containing
        // I use an array to be send with the messages, first value is the 'style classe' to be used, second value is the actual value of the msg
        return Redirect::back()->withErrors(['Succesvol ge-updated!']);
    }

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
                                case 'userkey':
                                    $user->userKey = $output;
                                    break;
                                case 'rol':
                                    $user->type = $output;
                                    break;
                            }
                        }
                        if (Session::has('userData')) {
                            Session::forget('userData');
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
