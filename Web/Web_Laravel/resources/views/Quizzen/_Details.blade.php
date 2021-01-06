@extends('main')

@section('style')
<!-- Import custom Navigation file -->
<link rel="stylesheet" href="{{ asset('css/Account/Account.css')}}">
@endsection

@section('content')
<div class="account-info-wrapper">
    <div class="row">
        <div class="col-sm-6 col-md-4">
            <ul class="nav nav-tabs border-0">
                <li class="d-flex align-items-center">
                    <a data-toggle="tab" class="d-flex" href="#AccountInfoTab">
                        <i class="fab fa-accusoft"></i>
                        <span><?php echo $quizName ?></span></a>
                </li>
            </ul>
        </div>
        <!-- tab effective -->
        <div class="col-sm-6 col-md-8">
            <div class="tab-content border-0">
                <div class="tab-pane fade in active show" id="AccountInfoTab">
                    {{ Form::open([ 'url'=>route('sendQuiz'), 'method'=>'post' ]) }}
                    <!-- hiddn fld for the quizId (used in send response in controller)-->
                    <input type="hidden" name="quizId" id="quizId" value="<?php echo $quizId ?>">
                    @foreach($vragenlijst as $vraag)                       
                        @if($vraag['multiplemultiplechoice'] == false)
                          @include('Quizzen/partials/._questionNotMultiple')
                        @else
                          @include('Quizzen/partials/._questionMultiple')
                        @endif
                    @endforeach
                    <button type="submit" class="btn text-white button-cst">Indienen</button>
                    {{ Form::close() }}
                </div>
            </div>
        </div>
    </div>
</div>
@endsection