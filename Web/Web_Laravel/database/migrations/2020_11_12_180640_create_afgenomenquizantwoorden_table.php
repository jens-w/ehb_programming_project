<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateAfgenomenquizantwoordenTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('afgenomenquizantwoorden', function (Blueprint $table) {
            $table->integer('id')->primary();
            $table->integer('afgenomenQuizId')->nullable()->index('afgenomenantwoord_quizId_idx');
            $table->integer('antwoordId')->nullable()->index('afgenomenantwoord_antwoordId_idx');
            $table->tinyInteger('juistBeantwoord')->nullable();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('afgenomenquizantwoorden');
    }
}
