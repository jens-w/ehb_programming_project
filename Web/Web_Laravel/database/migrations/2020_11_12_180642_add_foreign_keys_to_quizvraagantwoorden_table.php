<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class AddForeignKeysToQuizvraagantwoordenTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::table('quizvraagantwoorden', function (Blueprint $table) {
            $table->foreign('antwoordId', 'quizvraagantwoord_antwoordId')->references('id')->on('antwoorden')->onUpdate('RESTRICT')->onDelete('RESTRICT');
            $table->foreign('quizVraagId', 'quizvraagantwoord_quizVraagId')->references('id')->on('quizvragen')->onUpdate('RESTRICT')->onDelete('RESTRICT');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::table('quizvraagantwoorden', function (Blueprint $table) {
            $table->dropForeign('quizvraagantwoord_antwoordId');
            $table->dropForeign('quizvraagantwoord_quizVraagId');
        });
    }
}
