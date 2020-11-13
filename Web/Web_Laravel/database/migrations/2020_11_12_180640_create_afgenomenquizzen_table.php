<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateAfgenomenquizzenTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('afgenomenquizzen', function (Blueprint $table) {
            $table->integer('id')->primary();
            $table->integer('quizId')->nullable()->index('afgenomenquiz_quizId_idx');
            $table->integer('studentId')->nullable()->index('afgenomenquiz_studentId_idx');
            $table->dateTime('timestamp')->nullable();
            $table->decimal('score', 3)->nullable();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('afgenomenquizzen');
    }
}
