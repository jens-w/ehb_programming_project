<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class AddForeignKeysToAfgenomenquizzenTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::table('afgenomenquizzen', function (Blueprint $table) {
            $table->foreign('quizId', 'afgenomenquiz_quizId')->references('id')->on('quizzen')->onUpdate('RESTRICT')->onDelete('RESTRICT');
            $table->foreign('studentId', 'afgenomenquiz_studentId')->references('userId')->on('studenten')->onUpdate('RESTRICT')->onDelete('RESTRICT');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::table('afgenomenquizzen', function (Blueprint $table) {
            $table->dropForeign('afgenomenquiz_quizId');
            $table->dropForeign('afgenomenquiz_studentId');
        });
    }
}
