<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\User;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Http;
use View;
use Redirect;

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
        
       
        /* api request om user aan te maken */
        $response = Http::post('api.brielage.com:8088/user/create/', [
            'voornaam' => $request->input('voornaam'),
            'familienaam' => $request->input('familienaam'),
            'email' =>$request->input('email'),
            'password' => Hash::make($request['password']),
        ]); 

        /*   test response when api is down
        $response = '{
			"success" : true
		}';   */
        
        // 'true' parameter zorgt ervoor om een array terug te geven ipv een object

        $decodedArray = json_decode($response, true);
        
        // check response 
        foreach($decodedArray as $key => $output) {
            if($key === 'success') {
                if(boolval($output)){ //true, registered in api
                    return View::make('registration/success');
                }
                else { // false
                    // preservering space for actions when succes is false BEFORE checking the errors                 
                }              
            }
            if($key === 'errors'){
                return Redirect::back()->withErrors($output);
            }
        }
        // fallback
        return View::make('test/test')->with('testResponse', $decodedArray );
    }
}
