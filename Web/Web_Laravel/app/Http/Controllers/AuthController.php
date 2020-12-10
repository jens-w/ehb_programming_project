<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Validator, Redirect, Response;
use App\Models\User; // USE USER MODEL
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Hash;
use Session; // USE SESSION VAR.
use View;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Http;
use Mockery\Undefined;

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
        Session::forget('userData');
        return view('home');
    }

    public function loginPost(Request $request)
    {
        // retrieve user from db based on input email
        $user = DB::table('users')->where('email', $request->input('email'))->first();
        // if user exists
        if ($user != null) { // create md5 hashing appended with salt retrieved from local db
            $hashedPassword = md5($request->input('email') . $user->salt);
        } else {
            // if user doesn't exist don't return will error messages
            return Redirect::back()->withErrors(['Foute username/wachtwoord combinatie!'])->withInput($request->all());
        }
        // try to login with email & hashed ( password + salt )
        $response = Http::post('http://api.brielage.com:8081/user/login/', [
            'email' => $request->input('email'),
            'password' => $hashedPassword,
        ]);

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
                            case 'eigenrol':
                                break;
                        }
                    }
                    Session::put('userData', $user);
                    Session::save();
                    return View::make('AccountBeheer/Gegevens.Overview')->with('AccountViewModel', $user);
                } else { // success is false -> login failed
                    return Redirect::back()->withErrors(['Foute username/wachtwoord combinatie!'])->withInput($request->all());                
                }
            }          
        }

        // fall back 
        return View::make('test/test')->with('testResponse',  'Error: End of line auth');
    }
}
