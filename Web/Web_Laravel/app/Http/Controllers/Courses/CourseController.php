<?php

namespace App\Http\Controllers\Courses;


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


class CourseController extends \App\Http\Controllers\Controller
{

public static function GetCoursesList($userkey, $type){
        $response = '
        {
			"success" : true,
			"eigenrol" : "docent",
			"opleidingen" : {
				"opleiding1" : {
					"id" : 3,
					"naam" : "opleiding_naam"
				},
				"opleiding2" : {
					"id" : 4,
					"naam" : "opleiding_naam"
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
                        if ($type == 'docent') {
                            $AccountViewModel = new Docent();
                        } else {
                            $AccountViewModel = new Admin();
                        }
                        $AccountViewModel->fill($sessionData);
                        // loop over the rest of the key values
                        foreach ($decodedArray as $keyInner => $outputInner) {
                            switch ($keyInner) {
                                case 'eigenrol':
                                    $AccountViewModel->type = $outputInner;
                                    break;
                                case 'opleidingen':
                                    $coursesList = [];
                                    foreach ($outputInner as $key => $out) {
                                        // define vak with class - init the var.
                                        $opleiding = new Course();
                                        // fill the object (opleiding) with the array data
                                        $opleiding->fill($out);
                                        // add each 'opleiding' to an array
                                        $coursesList[] = $opleiding;
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
                        Session::put('coursesList', $coursesList);
                        // save the session
                        Session::save();
                    }
                    break;
            }
        }
        return $coursesList;

    }
}
    