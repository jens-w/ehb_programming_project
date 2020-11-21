<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateStudentenvakkenTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('studentenvakken', function (Blueprint $table) {
            $table->integer('userId')->index('studentenvak_userId_idx');
            $table->integer('vakId')->nullable()->index('studentenvak_vakId_idx');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('studentenvakken');
    }
}
