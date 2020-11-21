<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class AddForeignKeysToVraagmeerderemultiplechoiceTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::table('vraagmeerderemultiplechoice', function (Blueprint $table) {
            $table->foreign('vraagId', 'meerderemultiple_vraagId')->references('id')->on('vragen')->onUpdate('RESTRICT')->onDelete('RESTRICT');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::table('vraagmeerderemultiplechoice', function (Blueprint $table) {
            $table->dropForeign('meerderemultiple_vraagId');
        });
    }
}
