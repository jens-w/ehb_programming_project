<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class opleiding extends Model
{
    protected $table = 'opleidingen';
    protected $fillable = ['naam'];
    public $timestamps = false;

    public function newQuery($excludeDeleted = true)
    {
        return parent::newQuery($excludeDeleted)->whereId(1);
    }
}