<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class AddForeignKeysToDocentenTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::table('docenten', function (Blueprint $table) {
            $table->foreign('userId', 'docent__userId')->references('id')->on('users')->onUpdate('RESTRICT')->onDelete('RESTRICT');
            $table->foreign('vakId', 'docent__vakId')->references('id')->on('vakken')->onUpdate('RESTRICT')->onDelete('RESTRICT');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::table('docenten', function (Blueprint $table) {
            $table->dropForeign('docent__userId');
            $table->dropForeign('docent__vakId');
        });
    }
}
