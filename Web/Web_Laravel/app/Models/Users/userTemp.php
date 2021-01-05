<?php

namespace App\Models\Users;

use Illuminate\Database\Eloquent\Model;


class UserTemp extends Model
{
    public $fillable = ["id", "voornaam", "email"];
    public $id;
    public $voornaam;
    public $email;
   
}
