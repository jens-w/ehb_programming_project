<?php

namespace App\Http\Controllers\Vakken;


use Illuminate\Http\Request;
use \App\Models\Users\Student;
use \App\Models\Users\Teacher;
use \App\Models\Users\Admin;
use \App\Models\Users\User;
use App\Models\Vakken\Hoofdstuk;
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


    public function GetHoofdstukken($userkey, $vakId){
        $response = '
        {
			"success" : true,
			"eigenrol" : "rol",
			"hoofdstukken" : {
				"hoofdstuk1" : {
					"id" : 38,
					"nummer" : "3.2",
					"titel" : "titel"
				},
				"hoofdstuk2" : {
					"id" : 42,
					"nummer" : "3.3",
					"titel" : "titel"
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
                        $AccountViewModel = new User();
                        $AccountViewModel->fill($sessionData);
                        // loop over the rest of the key values
                        foreach ($decodedArray as $keyInner => $outputInner) {
                            switch ($keyInner) {
                                case 'eigenrol':
                                    $AccountViewModel->type = $outputInner;
                                    break;
                                case 'opleidingen':
                                    $hoofdstukList = [];
                                    foreach ($outputInner as $key => $out) {
                                        // define vak with class - init the var.
                                        $hoofdstuk = new Hoofdstuk();
                                        // fill the object (opleiding) with the array data
                                        $hoofdstuk->fill($out);
                                        // add each 'opleiding' to an array
                                        $hoofdstukList[] = $hoofdstuk;
                                    }

                                    break;
                            }
                        }
                        if (Session::has('userData')) {
                            Session::forget('userData');
                        }
                        if (Session::has('coursesList')) {
                            Session::forget('coursesList');
                        }
                        // put user data in session called 'userData'
                        Session::put('userData', $AccountViewModel);
                        Session::put('hoofdstukList', hoofdstukList);
                        // save the session
                        Session::save();
                    }
                    break;
            }
        }
        return $hoofdstukList;
    }


}