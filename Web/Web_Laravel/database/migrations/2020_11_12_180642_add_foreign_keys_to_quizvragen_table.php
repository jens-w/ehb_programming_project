<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class AddForeignKeysToQuizvragenTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::table('quizvragen', function (Blueprint $table) {
            $table->foreign('quizId', 'quizvraag_quizId')->references('id')->on('quizzen')->onUpdate('RESTRICT')->onDelete('RESTRICT');
            $table->foreign('vraagId', 'quizvraag_vraagId')->references('id')->on('vragen')->onUpdate('RESTRICT')->onDelete('RESTRICT');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::table('quizvragen', function (Blueprint $table) {
            $table->dropForeign('quizvraag_quizId');
            $table->dropForeign('quizvraag_vraagId');
        });
    }
}
