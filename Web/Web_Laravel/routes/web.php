<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\RegistrationController;
use App\Http\Controllers\Opleidingen\OpleidingController;
use App\Http\Controllers\Students\StudentController;
use App\Http\Controllers\AccountBeheer\AccountBeheerController;
use App\Http\Controllers\AuthController;
use App\Http\Controllers\LoginController;
use App\Models\User;
use App\Models\Opleiding;
/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('main');
});

// custom validation

// Definiëren van routes()

 



Route::get('/loginInit', [AuthController::class, 'loginInit'])->name('loginInit');
Route::post('/loginPost', [AuthController::class, 'loginPost'])->name('loginPost');
Route::get('/logout', [AuthController::class, 'logout'])->name('logout');
 
Route::post('/authenticate',[LoginController::class, 'authenticate'])->name('authenticate');

Route::get('/Opleidingen/Overview', [OpleidingController::class, 'index']);
Route::get('/Opleidingen/Create', [OpleidingController::class,'create']);
Route::post('/Opleidingen/CreatePost', [OpleidingController::class,'createPost']);

Route::get('/Students/Overview', [StudentController::class, 'index']);

Route::get('/Account/overview', [AccountBeheerController::class, 'index'])->name('accountBeheer');
Route::get('/Account/GetAccountInfo', [AccountBeheerController::class, 'GetAccountInfo'])->name('accountInfo');
Route::get('/Account/GetJsonDummyDataAccount', [AccountBeheerController::class, 'GetJsonDummyDataAccount'])->name('accountInfoDummy');

Auth::routes();
Route::get('/createUser', [RegistrationController::class, 'register'])->name('createUser');
Route::post('/registration/store', [RegistrationController::class, 'create']);

Route::get('/home', [App\Http\Controllers\HomeController::class, 'index'])->name('home');
