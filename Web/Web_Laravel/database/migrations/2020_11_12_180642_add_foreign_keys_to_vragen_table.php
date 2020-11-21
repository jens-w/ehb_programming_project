<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class AddForeignKeysToVragenTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::table('vragen', function (Blueprint $table) {
            $table->foreign('hoofdstukId', 'vraag_hoofdstukId')->references('id')->on('hoofdstukken')->onUpdate('RESTRICT')->onDelete('RESTRICT');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::table('vragen', function (Blueprint $table) {
            $table->dropForeign('vraag_hoofdstukId');
        });
    }
}
