<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class AddForeignKeysToHoofdstukkenTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::table('hoofdstukken', function (Blueprint $table) {
            $table->foreign('vakId', 'hoofdstuk__vakId')->references('id')->on('vakken')->onUpdate('RESTRICT')->onDelete('RESTRICT');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::table('hoofdstukken', function (Blueprint $table) {
            $table->dropForeign('hoofdstuk__vakId');
        });
    }
}
