<?php

namespace App\Http\Controllers;
use Illuminate\Http\Request;
use Validator,Redirect,Response;
use App\Models\User; // USE USER MODEL
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Hash;
use Session; // USE SESSION VAR.

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

    public function logout () {
        //logout user
        auth()->logout();
        // redirect to homepage
        return view('main');
    }

    public function loginPost (Request $request) {
        $credentials = $request->only('email', 'password');
        $user = new User($credentials);
        Auth::login($user);
             
        
    }
}
