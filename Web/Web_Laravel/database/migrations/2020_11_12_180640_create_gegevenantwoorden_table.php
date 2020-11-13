<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateGegevenantwoordenTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('gegevenantwoorden', function (Blueprint $table) {
            $table->integer('id')->primary();
            $table->integer('antwoordId')->nullable()->index('gegevenantwoord_antwoordId_idx');
            $table->integer('aantalJuistGekozen')->nullable();
            $table->integer('aantalFoutGekozen')->nullable();
            $table->dateTime('jaarMaand')->nullable();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('gegevenantwoorden');
    }
}
