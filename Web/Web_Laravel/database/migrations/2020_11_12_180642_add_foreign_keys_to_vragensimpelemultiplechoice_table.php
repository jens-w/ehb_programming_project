<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class AddForeignKeysToVragensimpelemultiplechoiceTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::table('vragensimpelemultiplechoice', function (Blueprint $table) {
            $table->foreign('vraagId', 'simpelmultiple_vraagId')->references('id')->on('vragen')->onUpdate('RESTRICT')->onDelete('RESTRICT');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::table('vragensimpelemultiplechoice', function (Blueprint $table) {
            $table->dropForeign('simpelmultiple_vraagId');
        });
    }
}
