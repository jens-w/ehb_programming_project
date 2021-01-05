<?php

namespace App\Http\Controllers\AccountBeheer;


use Illuminate\Http\Request;
use \App\Models\Users\Docent;
use \App\Models\Users\Admin;
use \App\Models\Users\User;
use \App\Models\Users\Student;
use \App\Models\Users\UserTemp;
use \App\Models\Courses\Course;
use \App\Models\Vakken\Vak;
use Illuminate\Support\Facades\DB;
use View;
use Session;
use Redirect;
use Illuminate\Support\Facades\Http;


class QuizController extends \App\Http\Controllers\Controller
{

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
                        if (Session::has('userData')) {
                            Session::forget('userData');
                        }
                        if (Session::has('coursesList')) {
                            Session::forget('coursesList');
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