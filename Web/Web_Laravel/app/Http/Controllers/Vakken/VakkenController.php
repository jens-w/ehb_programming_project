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


class VakkenController extends \App\Http\Controllers\BaseController
{

    
  
    public function index()
    {
        // request naar api ( momenteel is dit onze dummy data, onze lokale user storage dus)
        $AccountViewModel = new User();  
        // check wether you have a session active  
        $this->validateUser();
        // if not, get the data and create model
        $AccountViewModel = $this->retrieveCurrentUser();
        return view('AccountBeheer/Gegevens.Overview')->with('AccountViewModel',$AccountViewModel);
    }


    public static function GetVakkenList($userKey){
        $apiArray = array();
        $apiArray['userkey'] = $userKey;
        $response = Http::post('api.brielage.com:8081/vak/list/all', $apiArray);
         // check if success is true
         if (boolval($response["success"])) {    
            Session::put('rol', $response['eigenrol']);  
            return $response;;
        } else {
            foreach ($response['errors'] as $key => $value) {
                $errorArray[$key] = $value;
            }
            Session::put("errorsApi", $errorArray);
            return Redirect::back();
        }   
        
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

    public function detail($naam,$vakId){
                
        /* hier nog checken of user wel aangemeld is */

        $user = Self::CheckUser();

        // create array for request to api
         
        $apiArray = array();
        $apiArray['userkey'] = $user->userKey;
        $apiArray['vakid'] = $vakId;
 
        
        // API connection

        /*
        $response = '{
			"success" : true,
			"eigenrol" : "docent",
			"vak" : true,
			"hoofdstuk" : true,
			"quizlijst" : {
				"id1" : {
                    "id":"1",
					"naam" : "quiz_naam1",
					"omschrijving" : "dit is de omschrijving"
				},
				"id2" : {
                    "id":"2",
					"naam" : "quiz_naam2",
					"omschrijving" : "dit is de omschrijving"
				}
			}
		}'; */
        $response = Http::post('api.brielage.com:8081/quiz/list', $apiArray);             
        $response = json_decode($response, true);


        // check if success is true
        if (boolval($response["success"])) {    
            Session::put('rol', $response['eigenrol']);  
            return View::make('Vakken/_Detail')->with(['quizlijst' => $response['quizlijst'], 'vakName' => $naam, 'vakId' => $vakId]);
        } else {
            foreach ($response['errors'] as $key => $value) {
                $errorArray[$key] = $value;
            }
            Session::put("errorsApi", $errorArray);
            return View::make('Vakken/_Detail')->with(['vakName' => $naam, 'vakId' => $vakId]);
        }   
    }


    public function CheckUser(){
        if (!Session::has('userData')) {
            return Redirect('/loginInit')->withErrors(['Je moet aangemeld zijn om je account te kunnen bekijken']);
        }
        $sessionData = Session::get('userData');
        $user = new User();
        $user->fill($sessionData);
        return $user;
    }

}