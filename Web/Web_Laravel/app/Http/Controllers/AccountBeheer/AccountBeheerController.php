<?php

namespace App\Http\Controllers\AccountBeheer;


use Illuminate\Http\Request;
use \App\Models\Student;
use \App\Models\User;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Auth;

class AccountBeheerController extends \App\Http\Controllers\Controller
{

    public function index()
    {
       // Get alle students (met rol type '1')
       // Eigenlijk zijn dit users -> met type '1'
       // Rol word toegewezen tijdens registratie adhv funct.(zal van API komen)
       // De get() functie word overschreven in het model zelf om elke opvraging 
       // een extentie van het model User te maken.
        $AccountViewModel = Auth::user();
        return view('AccountBeheer.Overview', ['Account' => $AccountViewModel]);
    }
}

