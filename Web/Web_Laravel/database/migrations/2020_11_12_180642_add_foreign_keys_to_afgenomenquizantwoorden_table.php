<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class AddForeignKeysToAfgenomenquizantwoordenTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::table('afgenomenquizantwoorden', function (Blueprint $table) {
            $table->foreign('afgenomenQuizId', 'afgenomenantwoord_afgenomenQuizId')->references('id')->on('afgenomenquizzen')->onUpdate('RESTRICT')->onDelete('RESTRICT');
            $table->foreign('antwoordId', 'afgenomenantwoord_antwoordId')->references('id')->on('antwoorden')->onUpdate('RESTRICT')->onDelete('RESTRICT');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::table('afgenomenquizantwoorden', function (Blueprint $table) {
            $table->dropForeign('afgenomenantwoord_afgenomenQuizId');
            $table->dropForeign('afgenomenantwoord_antwoordId');
        });
    }
}
