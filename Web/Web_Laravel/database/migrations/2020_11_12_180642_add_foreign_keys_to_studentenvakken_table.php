<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class AddForeignKeysToStudentenvakkenTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::table('studentenvakken', function (Blueprint $table) {
            $table->foreign('userId', 'studentenvak_userId')->references('id')->on('users')->onUpdate('RESTRICT')->onDelete('RESTRICT');
            $table->foreign('vakId', 'studentenvak_vakId')->references('id')->on('vakken')->onUpdate('RESTRICT')->onDelete('RESTRICT');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::table('studentenvakken', function (Blueprint $table) {
            $table->dropForeign('studentenvak_userId');
            $table->dropForeign('studentenvak_vakId');
        });
    }
}
