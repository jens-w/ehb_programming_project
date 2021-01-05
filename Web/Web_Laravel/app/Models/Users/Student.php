<?php

namespace App\Models\Users;

use App\Models\Vakken\VakkenList;


class Student extends User
{
    
    
    public $opleiding;


    public function fill(User $user){
        $this-> voornaam = $user -> voornaam;
        $this-> email = $user -> email;
        $this-> familienaam =$user -> familienaam;
        $this-> password = $user -> password;
        $this-> type = $user -> type;
        $this-> vakken = $user -> vakken;
        $this-> opleiding = $user -> opleiding;
        $this-> userKey = $user -> userKey;
    }
}




    
 
