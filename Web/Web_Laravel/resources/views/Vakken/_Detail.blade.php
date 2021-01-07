@extends('main')

@section('style')
<!-- Import custom Navigation file -->
<link rel="stylesheet" href="{{ asset('css/Account/Account.css')}}">
@endsection

@section('content')
<div class="account-info-wrapper">
    <div class="row">
        <div class="col-lg-6 col-xl-4">
            <a data-toggle="tab" class="d-flex" href="#AccountInfoTab">
                <div class="vak-outer vak-detail-page">
                    <div class="vak">
                        <div class="front">
                            <div class="cover">
                                <p class=author> <span class="vak-title mx-2 button-cst">
                                        <span><?php echo $vakName ?></span>
                                    </span></p>
                            </div>
                        </div>
                        <div class="left-side">
                        </div>
                    </div>
                </div>
            </a>
        </div>
        <!-- tab effective -->
        <div class="col-lg-6 col-xl-8">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        @include('partials/_errors')
                    </div>
                </div>
            </div>
            <div class="tab-content border-0">
                <div class="tab-pane fade in active show" id="AccountInfoTab">
                    <?php echo Session::get('errorsApi'); ?>

                    <div class="quizzes-list-wrapper">
                        @foreach($quizlijst as $quiz)
                        @include('Vakken/partials/._quiz')
                        @endforeach
                    </div>
                    <div class="row">
                        <div class="col-12">
                            @if(Session::has('rol'))
                            <?php
                            $currentRol = Session::pull("rol");
                            $currentRol = strtolower($currentRol);
                            ?>
                            @if($currentRol == "docent")
                            <ul class="nav nav-tabs border-0">
                                <li class="list-unstyled my-5 ml-0">
                                    <a data-toggle="tab" class="d-flex align-items-center" href="#NewQuizTab"><span><i class="fas fa-plus"></i>Voeg een nieuwe quiz
                                            toe</span></a>
                                </li>
                            </ul>
                            @endif
                            @endif
                        </div>
                    </div>
                </div>
                <!-- new quiz tab -->
                <div class="tab-pane fade in" id="NewQuizTab">
                    <div class="row">
                        <div class="col-12">
                            {{ Form::open([ 'url'=>route('addNewQuiz'), 'method'=>'post' ]) }}
                            <input type="hidden" name="vakId" id="vakId" value="{{$vakId}}">
                            <div class="row">
                                <div class="col-12">
                                    <div class="form-group">
                                        <label>Naam van de quiz: </label>
                                        <input name="quizName" id="quizName" class="form-control">
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="form-group">
                                        <label>quiz omschrijving: </label>
                                        <input name="quizDescription" id="quizDescription" class="form-control">
                                    </div>
                                </div>
                            </div>
                            <button type="update" class="btn btn-dark button-cst">Maak nieuwe quiz aan</button>
                            {{ Form::close() }}
                        </div>
                    </div>
                </div>
                <!-- new question tab -->
                @foreach($quizlijst as $quiz)
                @include('Vakken/partials/._form_add_question')
                @endforeach

            </div>
        </div>
    </div>
</div>
@endsection

@section('script')
<script src="{{asset('js/Account/Account.js')}}"></script>
<script src="{{asset('js/Quiz/Quiz.js')}}"></script>
@endsection