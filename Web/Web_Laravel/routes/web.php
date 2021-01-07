<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\RegistrationController;
use App\Http\Controllers\Opleidingen\OpleidingController;
use App\Http\Controllers\Students\StudentController;
use App\Http\Controllers\AccountBeheer\AccountBeheerController;
use App\Http\Controllers\Quizzen\QuizController;
use App\Http\Controllers\AuthController;
use App\Http\Controllers\LoginController;
use App\Http\Controllers\Vakken\VakkenController;
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
    return view('home');
})->name('homepage');

// custom validation

// Definiëren van routes()

// PASSWORD PAGE TEMP

//Route::get('/home', [App\Http\Controllers\HomeController::class, 'index'])->name('loginPasswordPage'); 

// test ////

Route::get('/test',[AccountBeheerController::class, 'TestApiRequest'])->name('test');


/// -- end test ///

// -- LOGIN -- //
Route::get('/loginInit', [AuthController::class, 'loginInit'])->name('loginInit');
Route::post('/loginPost', [AuthController::class, 'loginPost'])->name('loginApi');
Route::get('/logout', [AuthController::class, 'logout'])->name('forgetSession');

// -- AUTH -- //
Route::post('/authenticate',[LoginController::class, 'authenticate'])->name('authenticate');


Route::get('/Opleidingen/Overview', [OpleidingController::class, 'index']);
Route::get('/Opleidingen/Create', [OpleidingController::class,'create']);
Route::post('/Opleidingen/CreatePost', [OpleidingController::class,'createPost']);

Route::get('/Students/Overview', [StudentController::class, 'index']);

// -- COURSES -- //

Route::get('/vakken/{naam}/{vakId}', [VakkenController::class, 'detail'])->name('detailCourse');

// -- QUIZZEN -- //
Route::post('/quiz/questions/add', [QuizController::class, 'addNewQuestion'])->name('addNewQuestion');
Route::get('/quiz/detail/{naam}/{quizId}', [QuizController::class, 'GetQuiz'])->name('getQuiz');
Route::post('/quiz/indienen', [QuizController::class , 'sendQuiz'])->name('sendQuiz');
Route::post('/quiz/add', [QuizController::class, 'addNewQuiz'])->name('addNewQuiz');
// -- ACCOUNT -- //
Route::get('/Account/overview', [AccountBeheerController::class, 'index'])->name('accountBeheer');
Route::post('/Account/update', [AccountBeheerController::class, 'update'])->name('update');
Route::get('/Account/GetAccountInfo', [AccountBeheerController::class, 'GetAccountInfo'])->name('accountInfo');
Route::get('/Account/GetJsonDummyDataAccount', [AccountBeheerController::class, 'GetJsonDummyDataAccount'])->name('accountInfoDummy');
Route::post('/Account/RequestUserKey', [AccountBeheerController::class, 'NewUserKey'])->name('requestNewUserkey');

Auth::routes();
Route::get('/createUser', [RegistrationController::class, 'register'])->name('createUser');
Route::post('/registration/store', [RegistrationController::class, 'create'])->name('storeUser');

Route::get('/home', [App\Http\Controllers\HomeController::class, 'index'])->name('home');
