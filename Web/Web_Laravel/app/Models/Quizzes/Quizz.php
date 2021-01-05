<?php

namespace App\Models\Quizzes;

use Illuminate\Database\Eloquent\Model;

class Quiz extends Model
{
    public $fillable = ["id","naam", "vakid", "hoofdstukId", "omschrijving"];
    public $id;
    public $naam;
    public $vakId;
    public $hoofdstukId;
    public $omschrijving;

}

