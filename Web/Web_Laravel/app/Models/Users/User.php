<?php

namespace App\Models\Users;

use App\Models\Vakken\VakkenList;


class User 
{
    
    public $id;
    public $voornaam;
    public $email;
    public $familienaam;
    public $password;
    public $type;
    public $vakken;
    public $opleiding;

    public function __construct(VakkenList ...$vakken)
    {
        $this->vakken = $vakken;
    }

    public function fill(User $user){
        $this-> voornaam = $user -> voornaam;
        $this-> email = $user -> email;
        $this-> familienaam =$user -> familienaam;
        $this-> password = $user -> password;
        $this-> type = $user -> type;
        $this-> vakken = $user -> vakken;
        $this-> opleiding = $user -> opleiding;
    }
}


class UserList
{
    private $list;
    public function __construct(User ...$list)
    {
        $this->list = $list;
    }
}

function PrintThings(ThingList $list)
{
    // ... do something to the thing list
    echo $list;
}




    /*
     * The attributes that should be cast to native types. 
     *
     * @var array
     *
    protected $casts = [
        'email_verified_at' => 'datetime',
    ];

    // setter - its SetFooAttribute ( where Foo is the name of the column from the attribute )
    public function SettypeAttribute($value){
       switch($value){
           case 1:
            $this->attributes['type'] = "Admin";
           break;
           case 2:
            $this->attributes['type'] = "Leraar";
           break;
           case 3:
            $this->attributes['type'] = "Student";
           break;
       }
        
    }
    
    public $timestamps = false;
}
*/
