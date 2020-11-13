<?php

namespace App\Http\Controllers\Opleidingen;

use Illuminate\Http\Request;
use App\Models\Opleiding;
use Illuminate\Support\Facades\DB;

class OpleidingController extends Controller
{
    public function create()
    {
        return view('Opleidingen.create');
    }

    public function index()
    {
       // $opleidingen = DB::table('opleidingen')->get();
        $names = DB::table('opleidingen')->get();
        return view('opleidingen.overview', ['opleidingNamen' => $names]);
    }

    public function CreatePost()
    {
        // eerst gaan we input data valideren
        $this->validate(request(),[
           'naam' => 'required'        
        ]);
        // if the data is gevalideerd
        // try to create opleiding
        $opleiding = Opleiding::create(request(['naam']));       
        return redirect('Opleidingen/Overview');
    }
}
