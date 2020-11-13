<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateVraagmeerderemultiplechoiceTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('vraagmeerderemultiplechoice', function (Blueprint $table) {
            $table->integer('id')->primary();
            $table->integer('vraagId')->nullable()->index('meerderemultiple_vraagId_idx');
            $table->integer('aantalJuisteAntwoordenNodig')->nullable();
            $table->integer('minimumAantalJuisteAntwoordenTonen')->nullable();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('vraagmeerderemultiplechoice');
    }
}
