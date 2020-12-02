<?php

namespace App\Models;

use Illuminate\Contracts\Auth\MustVerifyEmail;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Foundation\Auth\User as Authenticatable;
use Illuminate\Notifications\Notifiable;

class User extends Authenticatable
{
    use HasFactory, Notifiable;
    protected $table = 'users';
    /**
     * The attributes that are mass assignable.
     *
     * @var array
     */
    public $fillable = [
        'voornaam',
        'email',
        'familienaam',
        'password',
        'type',
    ];


    /**
     * The attributes that should be cast to native types. 
     *
     * @var array
     */
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


