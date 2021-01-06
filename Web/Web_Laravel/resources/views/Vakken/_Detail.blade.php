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
                            <i class="fas fa-graduation-cap"></i>
                        <span><?php echo $vakName ?></span></a>
                    </li>
                </ul>
            </div>
            <!-- tab effective -->
            <div class="col-sm-6 col-md-8">
                <div class="tab-content border-0">
                    <div class="tab-pane fade in active show" id="AccountInfoTab">
                        @foreach($quizlijst as $quiz)
                        @include('Vakken/partials/._quiz')
                        @endforeach
                    </div>
                </div>
            </div>
        </div>
</div>
@endsection