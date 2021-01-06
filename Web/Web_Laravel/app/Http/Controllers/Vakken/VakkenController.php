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

        if(!Session::has('userData')){
            return Redirect('/loginInit')->withErrors(['Je moet aangemeld zijn om je account te kunnen bekijken']);
        }
               
        $sessionData = Session::get('userData');
        $user = new User();
        $user->fill($sessionData);
        $uKey = $user->userKey;

        // create array for request to api
         
        $apiArray = array();
        $apiArray['userkey'] = $uKey;
        $apiArray['vakid'] = $vakId;
 
        


        /*
        $response = Http::get('api/linkHIER', $apiArray);
        
        USER KAN QUIZLIJST OPVRAGEN PER VAK/HOOFDSTUK
        REQUEST
            {
                "userkey" : "userkey1234",
                "vakid" : 2,
                "hoofdstukid" : 3
            }
            
            hoofdstukid optioneel
            
        RESPONSE SUCCESS
        */
        $response ='
            {
                "success" : true,
                "eigenrol" : "rol",
                "vak" : true,
                "hoofdstuk" : true,
                "quizlijst" : {
                    "quiz1" : {
                        "id" : "5",
                        "quiznaam" : "Intro Quiz - Fundamentals",
                        "quizomschrijving" : "Test je basiskennis van .Net in deze basis quiz"
                    },
                    "quiz2" : {
                        "id" : "78",
                        "quiznaam" : "Unit Testing",
                        "quizomschrijving" : "Denk jij te weten wat unit testen allemaal inhoud? Test het hier!"
                    }
                }
            }
            ';
            /*
            
            id1 = id quiz
            
        RESPONSE FAIL
            {
                "success" : false,
                "error" : [
                    "rechten_ongeldig" : true,
                    "vakid_ongeldig" : true,
                    "vakid_bestaat_niet" : true,
                    "geen_docent_van_vak" : true,
                    "hoofdstukid_ongeldig" : true,
                    "hoofdstukid_bestaat_niet" : true,
                    "geen_docent_van_hoofdstuk" : true,
                    "geen_quizzen_gevonden" : true,
                    "andere" : true
                ]
            }
 */

        $req = json_decode($response, true);
      
       

        return View::make('Vakken/_Detail')->with(['quizlijst' => $req['quizlijst'], 'vakName' => $naam]);
    }

}