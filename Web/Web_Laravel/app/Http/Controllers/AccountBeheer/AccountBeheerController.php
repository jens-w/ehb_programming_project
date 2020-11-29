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
use Redirect;
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
        return View::make('AccountBeheer/Gegevens.AccountInfo')->with('AccountViewModel', $user);
      
    }


    public function update(Request $request) {
        // get the id from hidden field in
        $userId = $request->input('id');
        // Get the correct user
        $user = User::find($userId);      
        // get the other input fields
        $upFirstName = $request->input('voornaam');
        $upEmail= $request->input('email');
        // set sql statement
        $sql = "UPDATE users SET voornaam=?, email=? WHERE Id= ?";
        DB::update($sql, array($upFirstName, $upEmail, $user->id));
        $msg = "test";
        // return redirect with error messages containing
        // I use an array to be send with the messages, first value is the 'style classe' to be used, second value is the actual value of the msg
        return Redirect::back()->with(compact('msg'));
       
     }

}