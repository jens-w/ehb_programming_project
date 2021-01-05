<?php

namespace App\Models\Users;

use App\Models\Vakken\VakkenList;


class Admin extends User
{
    
  // param eigen aan model nog te voorzien

  public $coursesList;
  public $userList;

  public function fill(User $user){
        $this-> voornaam = $user -> voornaam;
        $this-> email = $user -> email;
        $this-> familienaam =$user -> familienaam;
        $this-> password = $user -> password;
        $this-> type = $user -> type;
        $this-> userKey = $user -> userKey;
    }
}



