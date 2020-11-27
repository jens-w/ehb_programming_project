<?php

namespace App\Http\Controllers\AccountBeheer;


use Illuminate\Http\Request;
use \App\Models\Users\Student;
use \App\Models\Users\Teacher;
use \App\Models\Users\Admin;
use \App\Models\User;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Log;
use View;
use Symfony\Component\HttpFoundation\JsonResponse;

class AccountBeheerController extends \App\Http\Controllers\Controller
{

    public function index()
    {

        $AccountViewModel = Auth::user();
        return view('AccountBeheer/Gegevens.Overview', ['Account' => $AccountViewModel]);
    }

    public function GetJsonDummyDataAccount(){

        // creeër dummy data ( parsen naar Json )
      
        $loggedInUser = Auth::user();
        $loggedInUser->toJson();
        return $loggedInUser;

    }
    
    public function GetAccountInfo(Request $request){

        // retrieve request, parse to json 
        // getData() = removes header information from reques, BELANGERIJK!
        $rawJson = response()->json($request->all())->getData();

        //encode request to proper json
        $decodedAsArray = json_encode($rawJson, true);
        //decode request to proper array (thats why second param. = true !!)
        $result = json_decode($decodedAsArray, true);
        // we use 'request' as param since the main 'node' of our requested json in this istance is 'request'
        // request example: {request : { "id" : "1" , "naam" : "michael"}}...
        $innerPost = $result['request'];

        // create user object based on given array and its attributes
        $user= new User();
        $user->forceFill($innerPost);
        
    
        // return view 
        return View::make('AccountBeheer/Gegevens.AccountInfo')->with('userViewModel', $user);
      
    }

}