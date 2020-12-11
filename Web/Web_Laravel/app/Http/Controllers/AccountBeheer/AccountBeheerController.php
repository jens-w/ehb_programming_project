<?php

namespace App\Http\Controllers\AccountBeheer;


use Illuminate\Http\Request;
use \App\Models\Users\Student;
use \App\Models\Users\Teacher;
use \App\Models\Users\Admin;
use \App\Models\Users\User;
use \App\Models\Vakken\Vak;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Log;
use View;
use Illuminate\Support\Collection;
use Session;
use Symfony\Component\HttpFoundation\JsonResponse;
use Redirect;
use Illuminate\Support\Facades\Http;
use PhpParser\Node\Expr\List_;

class AccountBeheerController extends \App\Http\Controllers\Controller
{

    public function index()
    {
        
        // check wether you have a session active (komende dus van verificatie api) 
        if(!Session::has('userData')){
            return Redirect('/loginInit')->withErrors(['Je moet aangemeld zijn om je account te kunnen bekijken']);
        }
        
        /* ACCOUNT */
         
        // if not, get the data and create model
        $sessionData = Session::get('userData');     
        //decode request to proper object (thats why second param. = true !!)
        //$result = json_encode( $sessionData, true);

        $AccountViewModel = new User(); 
        $AccountViewModel->fill($sessionData);


       

        
        return view('AccountBeheer/Gegevens.Overview')->with('AccountViewModel',$AccountViewModel);
    }

    public function GetJsonDummyDataAccount(){
        // creeër dummy data ( parsen naar Json )
     
          $loggedInUser = session()->get('dataUser')->toJson();
        return $loggedInUser;   
    }
    
   
    public function GetAccountInfo(Request $request){
        /* get dummy data -> this will be replaced with api response ----------
        $response = Http::get('api.brielage.com:8088/user/something/')->json(); */

        // SELF:: is needed when calling methods within same controller!
        $response = Self::GetJsonDummyDataAccount();

        // retrieve request, parse to json 
        // getData() = removes header information from reques, BELANGERIJK!
        $rawJson = response()->json($request->all())->getData();
         
        //encode request to proper json
        $decodedAsArray = json_encode($response, true);
        //decode request to proper array (thats why second param. = true !!)
        $result = json_decode($decodedAsArray, true);
        // we use 'request' as param since the main 'node' of our requested json in this istance is 'request'
        // request example: {request : { "id" : "1" , "naam" : "michael"}}...
        // $innerPost = $result['user'];

        // create user object based on given array and its attributes
        $user= new User();
        $user->forceFill($result);
        
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

        // return redirect with error messages containing
        // I use an array to be send with the messages, first value is the 'style classe' to be used, second value is the actual value of the msg
        return Redirect::back()->withErrors(['Succesvol ge-updated!']);
       
     }


    public function TestApiRequest(){
        // test naar api
        $response = Http::post('api.brielage.com:8088/user/something/', [
            'naam' => 'Steve',
            'password' => '#hash12345',
        ]);
        return View::make('test/test')->with('testResponse', $response);
    }

}