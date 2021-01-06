<?php

namespace App\Http\Controllers\Quizzen;

use App\Http\Controllers\BaseController;
use Illuminate\Http\Request;
use \App\Models\Users\Docent;
use \App\Models\Users\Admin;
use \App\Models\Users\User;
use View;
use \App\Models\Courses\Course;
use Session;


class QuizController extends BaseController
{


    // verkrijg een quiz op basis van quizId in Url 
    


 public function getQuiz($naam, $quizId){
     
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
    $apiArray['quizid'] = $quizId;

    // respone api
    // $response = Http::get('api/linkHIER', $apiArray);

   $response = '{
    "success" : true,
    "eigenrol" : "rol",
    "quizid" : 38,
    "vragenlijst" : {
        "vraag1" : {
            "vraagid" : 48,
            "vraag" : "Hoe wordt een collectie van unit testen genoemd?",
            "multiplemultiplechoice" : true,
            "antwoorden" : {
                "antwoord1" : {
                    "antwoordid" : 83,
                    "antwoord" : "Een collectie"
                },
                "antwoord2" : {
                    "antwoordid" : 19,
                    "antwoord" : "Een evaluatie"
                },
                "antwoord3" : {
                    "antwoordid" : 21,
                    "antwoord" : "Een suite"
                }
            }
        },
        "vraag2" : {
            "vraagid" : 43,
            "vraag" : "Unit testing vraagt initieel meer tijd wanneer je een product gaat ontwikkelen",
            "multiplemultiplechoice" : true,
            "antwoorden" : {
                "antwoord1" : {
                    "antwoordid" : 33,
                    "antwoord" : "Waar"
                },
                "antwoord2" : {
                    "antwoordid" : 17,
                    "antwoord" : "Niet waar"
                }
            }
        }
        ,
        "vraag3" : {
            "vraagid" : 46,
            "vraag" : "Op het einde van de dag helpt unit testing ons om...(vul aan)",
            "multiplemultiplechoice" : true,
            "antwoorden" : {
                "antwoord3" : {
                    "antwoordid" : 103,
                    "antwoord" : "Geeft ons een hogere kwaliteit aan software"
                },
                "antwoord4" : {
                    "antwoordid" : 107,
                    "antwoord" : "Zorgt voor de ontwikkelaars een zelf gedocumenteerde code"
                },
                "antwoord1" : {
                    "antwoordid" : 803,
                    "antwoord" : "Problemen te ontdekken"
                },
                "antwoord2" : {
                    "antwoordid" : 517,
                    "antwoord" : "Alles van het bovenstaande"
                }
            }
        }
    }
}';

Session::put('quizData', $response);
Session::save();

$req = json_decode($response, true);
return View::make('Quizzen/_Details')->with(['vragenlijst' => $req['vragenlijst'], 'quizName' => $naam , 'quizId' => $quizId]);
}


public function sendQuiz(Request $request){

    if(!Session::has('userData')){
        return Redirect('/loginInit')->withErrors(['Je moet aangemeld zijn om je account te kunnen bekijken']);
    }
    $sessionData = Session::get('userData');
    $user = new User();
    $user->fill($sessionData);
    $uKey = $user->userKey;

    // get quiz info from session
    // pull command zal het ook direct na retrieven flushen

    $quizSession = Session::pull('quizData');
    $quizSession = json_decode($quizSession, true);


    $apiArray = array();
    $apiArray['userkey'] = $uKey;
    $apiArray['quizid'] = $request->quizId;
    $count = 0;
    $countIn = 0;
    $choices = $request->choice; // get array of all poss. questions & answers
    foreach($choices as $key => $value){
        $count += 1;
        $delim = "vraag".$count;
        //$key = vraag
        //$value = antwoord
        $apiArray['antwoordenlijst'][$delim]['vraagid'] = $key;
        foreach($quizSession['vragenlijst'] as $vraag){
            if($vraag['vraagid'] == $key){
                foreach($vraag['antwoorden'] as $antwoord){
                    $countIn += 1;
                    $delimIn = "antwoord".$countIn;
                    if($antwoord['antwoordid'] == $value){
                        $apiArray['antwoordenlijst'][$delim]['antwoorden'][$delimIn]['antwoordid'] = $value;
                        $apiArray['antwoordenlijst'][$delim]['antwoorden'][$delimIn]['gekozen'] = true;
                    }
                    else {
                        $apiArray['antwoordenlijst'][$delim]['antwoorden'][$delimIn]['antwoordid'] = $antwoord['antwoordid'];
                        $apiArray['antwoordenlijst'][$delim]['antwoorden'][$delimIn]['gekozen'] = false;
                    }
                }
            }
        }
        
        
    }


    // maak de request naar api met de apiArray
    // $response = Http::post('apiUrl', $apiArray)
    // 
    $response = '{
        "success" : true,
        "eigenrol" : "'. $user->type . '",
        "score" : 8.3
    }';
    $response = json_decode($response, true);
    // check if success is true
    if(boolval($response["success"])){
        return View::make('Quizzen._Score')->with(['score'=> $response['score'], 'quizId' => $request->quizId ]);
    } else {
        // ERROR TEMPLATE NOG VOORZIEN
        //return View::make('Errors/errorTemplate')
    }

 }
     
    
public function GetQuizzes($userKey, $vakid, $hoofdstukId = null){
            $response = 
            '{
                "success" : true,
                "eigenrol" : "docent",
                "vak" : true,
                "hoofdstuk" : true,
                "quizlijst" : {
                    "quiz1" : {
                        "id" : "89"
                        "naam" : "quiz_naam1",
                        "omschrijving" : "dit is de omschrijving"
                    },
                    "quiz2" : {
                        "id" : "12",
                        "naam" : "quiz_naam2",
                        "omschrijving" : "dit is de omschrijving"
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
                                    case 'quizlijst':
                                        $QuizzesList = [];
                                        foreach ($outputInner as $key => $out) {
                                            // define vak with class - init the var.
                                            $quiz = new Quiz();
                                            // fill the object (opleiding) with the array data
                                            $quiz->fill($out);
                                            // add each 'opleiding' to an array
                                            $QuizzesList[] = $quiz;
                                        }
    
                                        break;
                                }
                            }
                         
                            // put user data in session called 'userData'
                            Session::put('userData', $AccountViewModel);
                            Session::put('quizzesList', $QuizzesList);
                            // save the session
                            Session::save();
                        }
                        break;
                }
            }
            return $QuizzesList;
    
        }   
    
    
    
    
}
    