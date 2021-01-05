<?php

namespace App\Models\Courses;

use Illuminate\Database\Eloquent\Model;

class Course extends Model
{
    public $fillable = ["id","naam"];
    public $id;
    public $naam;
   

}


