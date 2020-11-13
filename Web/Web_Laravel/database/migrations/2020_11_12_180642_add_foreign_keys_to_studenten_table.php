<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class AddForeignKeysToStudentenTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::table('studenten', function (Blueprint $table) {
            $table->foreign('opleidingId', 'student_opleidingId')->references('id')->on('opleidingen')->onUpdate('RESTRICT')->onDelete('RESTRICT');
            $table->foreign('userId', 'student_userId')->references('id')->on('users')->onUpdate('RESTRICT')->onDelete('RESTRICT');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::table('studenten', function (Blueprint $table) {
            $table->dropForeign('student_opleidingId');
            $table->dropForeign('student_userId');
        });
    }
}
