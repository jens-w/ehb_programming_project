<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class AddForeignKeysToBeantwoordevragenTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::table('beantwoordevragen', function (Blueprint $table) {
            $table->foreign('vraagId', 'beantwoordevraag_vraagId')->references('id')->on('vragen')->onUpdate('RESTRICT')->onDelete('RESTRICT');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::table('beantwoordevragen', function (Blueprint $table) {
            $table->dropForeign('beantwoordevraag_vraagId');
        });
    }
}
