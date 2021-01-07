<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Users\User;
use App\Models\Users\Salt\UserSalt;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Http;
use View;
use Illuminate\Support\Str;
use Redirect;
use Illuminate\Support\Facades\Input;

class RegistrationController extends Controller
{
    public function register()
    {
        return view('registration.create');
    }

    protected function validator(array $data)
    {
        return Validator::make($data, [
            'name' => ['required', 'string', 'max:255'],
            'email' => ['required', 'string', 'email', 'max:255', 'unique:users'],
            'password' => ['required', 'string', 'min:8', 'confirmed'],
        ]);
    }

    /**
     * Create a new user in api db after a valid registration.
     *
     * @param  Request  $request (form data)
     * @return $response from api
     */
    protected function create(Request $request)
     
    
    {

        if(strlen($request->input('password')) <= 8){
            $key = array('wachtwoord_is_ongeldig');
            // create array and fill it with the key
            $a = array_fill_keys($key, 'error');
            return Redirect::back()->withErrors($a)->withInput($request->all());
        }

        $user = DB::table('users')->where('email', $request->input('email'))->first();
        // if user exists
        if ($user != null) { // if user does exist inform client that user exist
            // create key
            $key = array('gebruiker_bestaat_al');
            // create array and fill it with the key
            $a = array_fill_keys($key, 'error');
            return Redirect::back()->withErrors($a)->withInput($request->all());
        } else {
            // if user doesn't exist continue creating new user
            $salt = $this->GetRandomSalt();
            $psswrdSalt = $request['password'] . $salt;
            $hashedmd5 = md5($psswrdSalt);
        }
        
        /* api request om user aan te maken */
        $response = Http::post('http://api.brielage.com:8081/user/create/', [
            'voornaam' => $request->input('voornaam'),
            'familienaam' => $request->input('familienaam'),
            'email' => $request->input('email'),
            'password' => $hashedmd5,
        ]);

        /*   test response when api is down
        $response = '{
			"success" : true
		}';   */

        // 'true' parameter zorgt ervoor om een array terug te geven ipv een object

        $decodedArray = json_decode($response, true);

        // check response 
        foreach ($decodedArray as $key => $output) {
            if ($key === 'success') {
                if (boolval($output)) { //true, registered in api
                    $userLocalDb = new UserSalt;
                    $userLocalDb->email = $request['email'];
                    $userLocalDb->salt = $salt;
                    $userLocalDb->save();
                    return View::make('registration/success');
                } else { // false
                    // preservering space for actions when succes is false BEFORE checking the errors                 
                }
            }
            if ($key === 'errors') {
                return Redirect::back()->withErrors($output)->withInput($request->all());;
            }
        }
        // fallback & testing
        return View::make('test/test')->with('testResponse',  Hash::make($request['password']));
    }


    protected function GetRandomSalt()
    {
        /* salt aanmaken */
        return Str::random(12);
    }
}
