<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Illuminate\Foundation\Auth\AuthenticatesUsers;

class LoginController extends Controller
{
    /**
     * Handle an authentication attempt.
     *
     * @param  \Illuminate\Http\Request $request
     *
     * @return Response
     */
    public function authenticate(Request $request)
    {
        // create our user data for the authentication
        $userdata = array(
            
            'password'  => Request('password'),
            'email'     => Request('email')
        );

        if(Auth::guard('coursequiz')){
            echo 'SUCCESS!';
            echo  Auth::user()->uname;
        }
        /*
        if (Auth::attempt($userdata,true)) {

            // validation successful!
            // redirect them to the secure section or whatever
            // return Redirect::to('secure');
            // for now we'll just echo success (even though echoing in a controller is bad)
            // return Redirect::to('Home');

            echo 'SUCCESS!';
            echo  Auth::user()->uname;
        }
        */
        else {
            redirect()->back();
        }
    }
}