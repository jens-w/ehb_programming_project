<?php

namespace App\Http\Controllers;
use Illuminate\Http\Request;
use Validator,Redirect,Response;
use App\Models\User; // USE USER MODEL
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Hash;
use Session; // USE SESSION VAR.
use View;
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
        Session::forget('userData');
        return view('home');
    }

    public function loginPost (Request $request) {
        $credentials = $request->only('email', 'password');
        // dit wordt de uiteindelijke call naar de api, afhankelijk daar van zal de er een user in
        // de sessie gestored worden 
        /*
        $response = Http::post('api.brielage.com:8088/user/something/', [
            'email' => $request->only('email'),
            'password' => $request->only('password'),
        ]); */

        /* In de response van de api zal uiteindelijk een Json zitten welke of
        1. errors bevat, deze errors moeten er dan uitgehaald worden en meegestuurd worden met de redirect
        2. geldige validatie & dus ook een json met de gegevens van de user. Deze gegevens worden dan zoals hier onderstaand voorbeeld
        in de server side SESSION gestoken, je kan de verschillende data opvragen door rechtstreeks de sessie aan te spreken of door het model mee te geven. */

        if(request('email') == "michaelbracke@hotmail.com" && request('password') == "Becosoft2020!"){
            $user = new User();
            $user -> id = 1;
            $user -> email = request('email');
            $user -> voornaam = "Jantje";
            $user -> familienaam = "Vermeulen";
            $user -> type = 1;
            Session::put('userData', $user);
            Session::save();
            // return view 
            return View::make('AccountBeheer/Gegevens.Overview')->with('AccountViewModel', $user);
        }
        else {
            return Redirect::back()->withErrors(['Foute username/wachtwoord combinatie!']);
        }
             
        
    }
}
