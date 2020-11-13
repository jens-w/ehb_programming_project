<?php
namespace App\Models;

use App\Models\User;

class Student extends User {
    

    public function newQuery($excludeDeleted = true)
    {
        return parent::newQuery($excludeDeleted)->whereType(1);
    }
}