<?php

namespace App\Models\Vakken;

use Illuminate\Database\Eloquent\Model;

class Vak extends Model
{
    public $fillable = ["id","naam"];
    public $id;
    public $Naam;
    public $jaar;
    public $opleidingsId;

}


class VakkenList
{
    private $list;
    public function __construct(Vak ...$list)
    {
        $this->list = $list;
    }
   
}