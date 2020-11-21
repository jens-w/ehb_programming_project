<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateQuizvragenTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('quizvragen', function (Blueprint $table) {
            $table->integer('id')->primary();
            $table->integer('quizId')->nullable()->index('quizvraag_quizId_idx');
            $table->integer('vraagId')->nullable()->index('quizvraag_vraagId_idx');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('quizvragen');
    }
}
