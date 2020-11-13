<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class AddForeignKeysToGegevenantwoordenTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::table('gegevenantwoorden', function (Blueprint $table) {
            $table->foreign('antwoordId', 'gegevenantwoord_antwoordId')->references('id')->on('antwoorden')->onUpdate('RESTRICT')->onDelete('RESTRICT');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::table('gegevenantwoorden', function (Blueprint $table) {
            $table->dropForeign('gegevenantwoord_antwoordId');
        });
    }
}
