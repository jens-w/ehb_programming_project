<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateVragensimpelemultiplechoiceTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('vragensimpelemultiplechoice', function (Blueprint $table) {
            $table->integer('id')->primary();
            $table->integer('vraagId')->nullable()->index('vraag_vraagId_idx');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('vragensimpelemultiplechoice');
    }
}
