<?php

namespace App\Http\Controllers;

use App\Http\Controllers\Controller;
use \App\Http\Controllers\Courses\CourseController as _courseService;

class BaseController extends Controller
{

    protected $courseService;

    public function __construct() 
    {
        // Fetch the Site Settings object
        $this->courseService = _courseService::class;
       
    }

}