<?php

namespace App\Http\Controllers;

use App\Http\Controllers\Controller;
use \App\Http\Controllers\Courses\CourseController as _courseService;
use Session;
use App\Models\Users\User;

class BaseController extends Controller
{

    protected $courseService;
    protected $baseService;

    public function __construct() 
    {
        // Fetch the Site Settings object
        $this->courseService = _courseService::class;
        
    }


    public function validateeUser()
    {
        $this->validateLogic();    
    }

    protected function validateLogic(){
        return;
    }

    public function retrieveCurrentUser(){
    
        $sessionData = Session::get('userData');
        $user = new User();
        $user->fill($sessionData);
        return $user;
    }


}