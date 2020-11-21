<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateBeantwoordevragenTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('beantwoordevragen', function (Blueprint $table) {
            $table->integer('id')->primary();
            $table->integer('vraagId')->nullable()->index('beantwoordevraag_vraagId_idx');
            $table->tinyInteger('juistBeantwoord')->nullable();
            $table->dateTime('jaarMaand')->nullable();
            $table->tinyInteger('isBeantwoord')->nullable();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('beantwoordevragen');
    }
}
