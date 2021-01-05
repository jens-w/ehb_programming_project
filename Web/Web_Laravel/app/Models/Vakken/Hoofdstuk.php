<?php

namespace App\Models\Vakken;

use Illuminate\Database\Eloquent\Model;

class Hoofdstuk extends Model
{
    public $fillable = ["id","titel", "nummer"];
    public $id;
    public $titel;
    public $nummer;

}


