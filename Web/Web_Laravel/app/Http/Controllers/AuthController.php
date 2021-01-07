<?php

namespace App\Http\Controllers;

use Session; // USE SESSION VAR.
use View;

use Illuminate\Http\Request;
use Validator, Redirect, Response;

use App\Models\Users\User;
use App\Models\Users\Admin;
use App\Models\Users\Docent;
use App\Models\Users\Student;


use App\Models\Vakken\Vak;
use App\Models\Vakken\VakkenList;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Hash;

use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Http;
use phpDocumentor\Reflection\Types\This;

// created controller through artisan command
// php artisan make:controller AuthController

class AuthController extends Controller
{

    public function loginInit()
    {
        return view('user.login');
    }


    public function register()
    {
        return view('registration.create');
    }

    public function logout()
    {
        session()->flush();
        return view('home');
    }

    public function loginPost(Request $request)
    {
        // retrieve user from db based on input email
        $user = DB::table('users')->where('email', $request->input('email'))->first();
        // if user exists
        if ($user != null) { // create md5 hashing appended with salt retrieved from local db
            $hashedPassword = md5($request->input('password') . $user->salt);
        } else {
            // if user doesn't exist don't return will error messages
            return Redirect::back()->withErrors(['Foute username/wachtwoord combinatie!'])->withInput($request->all());
        }

        /* API REQUEST ------ */

        /*try to login with email & hashed ( password + salt ) */
        $response = Http::post('http://api.brielage.com:8081/user/login/', [
            'email' => $request->input('email'),
            'password' => $hashedPassword
        ]);
        /*
        
        $response = '{
            "success" : true,
            "userkey" : "def456",
            "voornaam" : "Jos",
            "familienaam" : "Dewolf",
            "email": "jos@live.be",
            "eigenrol":"docent",
            "opleiding" : {
                "id" : 1,
                "naam": "testopleiding"
            },
            "vakken" : {

                "vak1" : {
                    "id": 1,
                    "naam": "testvak numero uno"
                },
                "vak2" : {
                "id" : 2,
                "naam" : ".Net advanced"
                }

            }
        }'; 
        */


        //  serialize data
        // 'true' parameter zorgt ervoor om een array terug te geven ipv een object
        $decodedArray = json_decode($response, true);

        /* In de response van de api zal uiteindelijk een Json zitten welke of
        1. errors bevat, deze errors moeten er dan uitgehaald worden en meegestuurd worden met de redirect
        2. geldige validatie & dus ook een json met de gegevens van de user. Deze gegevens worden dan zoals hier onderstaand voorbeeld
        in de server side SESSION gestoken, je kan de verschillende data opvragen door rechtstreeks de sessie aan te spreken of door het model mee te geven. */

        // check response 
        foreach ($decodedArray as $key => $output) {
            if ($key === 'success') {
                if (boolval($output)) { //true, login succesfull in api
                    // create user
                    $user = new User();
                    // loop over the rest of the key values
                    foreach ($decodedArray as $key => $output) {
                        switch ($key) {
                            case 'voornaam':
                                $user->voornaam = $output;
                                break;
                            case 'familienaam':
                                $user->familienaam = $output;
                                break;
                            case 'email':
                                $user->email = $output;
                                break;
                            case 'avatarpad':
                                break;
                            case 'rol':
                                $user->type = $output;
                                break;
                            case 'opleiding':
                                /* LOGICA OM OPLEIDINGEN TOE TE VOEGEN
                                foreach ($output as $key => $innerOutput) { }
                                 */
                                break;
                            case 'vakken':
                                $vakkenList = [];
                                foreach ($output as $key => $out) {
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
                            case 'eigenrol':
                                $user->type = $output;
                        }
                    }

                    $lowerCaseType = strtolower($user->type);
                    switch ($lowerCaseType) {
                        case "admin":
                            $admin = new Admin();
                            $admin->fill($user);
                            // put user data in session called 'userData'
                            // any other param here
                            Session::put('userData', $admin);
                            // save the session
                            Session::save();
                            return redirect()->route('accountBeheer');
                            break;
                        case "docent":
                            $docent = new Docent();
                            $docent->fill($user);
                            // put user data in session called 'userData'
                            // any other param here
                            Session::put('userData', $docent);
                            // save the session
                            Session::save();
                            return redirect()->route('accountBeheer');
                            break;
                        case "student":
                            // any other param here
                            // put user data in session called 'userData'
                            Session::put('userData', $user);
                            // save the session
                            Session::save();
                            return redirect()->route('accountBeheer');
                            break;
                        case "user":
                            // any other param here
                            // put user data in session called 'userData'
                            Session::put('userData', $user);
                            // save the session
                            Session::save();
                            return redirect()->route('accountBeheer');
                            break;
                    }
                    // fallback
                    return view('AccountBeheer/Gegevens.Overview')->with('AccountViewModel', $user);
                } else { // success is false -> login failed
                    return View::make('test/test')->with('testResponse',  $decodedArray);
                    return Redirect::back()->withErrors(['Foute username/wachtwoord combinatie!'])->withInput($request->all());
                }
            }
        }
    }
}
