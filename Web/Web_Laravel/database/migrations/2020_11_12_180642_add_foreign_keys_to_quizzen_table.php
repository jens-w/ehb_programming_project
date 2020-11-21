<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class AddForeignKeysToQuizzenTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::table('quizzen', function (Blueprint $table) {
            $table->foreign('vakken_id', 'fk_quizzen_vakken1')->references('id')->on('vakken')->onUpdate('RESTRICT')->onDelete('RESTRICT');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::table('quizzen', function (Blueprint $table) {
            $table->dropForeign('fk_quizzen_vakken1');
        });
    }
}
