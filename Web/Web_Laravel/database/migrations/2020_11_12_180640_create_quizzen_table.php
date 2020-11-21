<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateQuizzenTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('quizzen', function (Blueprint $table) {
            $table->integer('id')->primary();
            $table->string('naam')->nullable();
            $table->string('omschrijving')->nullable();
            $table->tinyInteger('isBeschikbaar')->nullable();
            $table->integer('vakken_id')->index('fk_quizzen_vakken1_idx');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('quizzen');
    }
}
