<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateHoofdstukkenTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('hoofdstukken', function (Blueprint $table) {
            $table->integer('id')->primary();
            $table->string('titel')->nullable();
            $table->float('nummer', 10, 0)->nullable();
            $table->integer('vakId')->nullable()->index('vakId_idx');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('hoofdstukken');
    }
}
