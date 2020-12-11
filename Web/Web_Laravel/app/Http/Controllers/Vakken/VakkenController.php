<?php

namespace App\Http\Controllers\Vakken;


use Illuminate\Http\Request;
use \App\Models\Users\Student;
use \App\Models\Users\Teacher;
use \App\Models\Users\Admin;
use \App\Models\Users\User;
use View;
use Session;
use Symfony\Component\HttpFoundation\JsonResponse;
use Redirect;
use Illuminate\Support\Facades\Http;


class VakkenController extends \App\Http\Controllers\Controller
{

    public function index()
    {
        // request naar api ( momenteel is dit onze dummy data, onze lokale user storage dus)
        $AccountViewModel = new User();  
        // check wether you have a session active  
        if(!Session::has('userData')){
            return Redirect('/loginInit')->withErrors(['Je moet aangemeld zijn om je account te kunnen bekijken']);
        }
        // if not, get the data and create model
        $sessionData = Session::get('userData');
        //encode request to proper json
        $decodedAsArray = json_encode($sessionData, true);
        //decode request to proper array (thats why second param. = true !!)
        $result = json_decode($decodedAsArray, true);
        $AccountViewModel->forceFill($result);
        return view('AccountBeheer/Gegevens.Overview')->with('AccountViewModel',$AccountViewModel);
    }


}