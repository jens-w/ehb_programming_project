<?php

namespace App\Models\Users;

use App\Models\Vakken\VakkenList;


class Docent extends User
{
    
    
    public $quizzen;
   


    public function fill(User $user){
        $this-> voornaam = $user -> voornaam;
        $this-> email = $user -> email;
        $this-> familienaam =$user -> familienaam;
        $this-> password = $user -> password;
        $this-> type = $user -> type;
        $this-> userKey = $user -> userKey;
    }
}




