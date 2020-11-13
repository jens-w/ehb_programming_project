<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateVragenTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('vragen', function (Blueprint $table) {
            $table->integer('id')->primary();
            $table->string('vraag')->nullable();
            $table->integer('aantalAntwoordenTonen')->nullable();
            $table->tinyInteger('juisteAntwoordTonen')->nullable();
            $table->integer('hoofdstukId')->index('fk_vragen_hoofdstukken1_idx');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('vragen');
    }
}
