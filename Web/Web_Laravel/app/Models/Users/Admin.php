<?php
namespace App\Models\Users;

use App\Models\User;

class Admin extends User {
    
    public function newQuery($excludeDeleted = true)
    {
        return parent::newQuery($excludeDeleted)->whereType(3);
    } 
    
}