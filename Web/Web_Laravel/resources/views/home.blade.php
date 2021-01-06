@extends('main')


@section('content')
<!-- Home page -->
<div class="py-5">
    <div class="row">
        <div class="col-12">
            <div class="row d-flex justify-content-center">
                <div class="col-6">
                    <img class="w-100 h-auto img-home" src="{{asset('images\home\quiz.png')}}" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-3">
                </div>
                <div class="col-md-3">
                    <a href="" class="btn btn-dark w-100 button-cst">Wat is coursequiz?</a>
                </div>
                <div class="col-md-3">
                    <a href="Account/overview/#tab_AccountCourses" class="btn btn-dark w-100 button-cst">Ga direct aan de
                        slag!</a>
                </div>
                <div class="col-md-3">
                </div>
            </div>

        </div>
    </div>
</div>
@endsection