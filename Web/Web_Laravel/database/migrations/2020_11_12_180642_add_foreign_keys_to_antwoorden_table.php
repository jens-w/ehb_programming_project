<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class AddForeignKeysToAntwoordenTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::table('antwoorden', function (Blueprint $table) {
            $table->foreign('vraagId', 'antwoord_vraagId')->references('id')->on('vragen')->onUpdate('RESTRICT')->onDelete('RESTRICT');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::table('antwoorden', function (Blueprint $table) {
            $table->dropForeign('antwoord_vraagId');
        });
    }
}
