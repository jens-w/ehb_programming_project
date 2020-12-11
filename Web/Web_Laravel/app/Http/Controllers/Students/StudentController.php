<?php

namespace App\Http\Controllers\Students;


use Illuminate\Http\Request;
use \App\Models\Student;
use \App\Models\Users\User;
use Illuminate\Support\Facades\DB;

class StudentController extends \App\Http\Controllers\Controller
{

    public function index()
    {
       // Get alle students (met rol type '1')
       // Eigenlijk zijn dit users -> met type '1'
       // Rol word toegewezen tijdens registratie adhv funct.(zal van API komen)
       // De get() functie word overschreven in het model zelf om elke opvraging 
       // een extentie van het model User te maken.
       $students = Student::whereActive(true)->get()->pluck('email');
        return ['studentNames' => $students];
    }
}

