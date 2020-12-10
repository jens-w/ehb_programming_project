<?php

namespace App\Models\Users\Salt;

use Illuminate\Database\Eloquent\Model;

class UserSalt extends Model
{
    protected $table = 'users';
    protected $fillable = ['email','salt'];
    public $timestamps = true;

}