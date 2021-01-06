@extends('main')


@section('content')
<div class="success-message-wrapper mb-5 bg-white">
    <div class="row d-flex justify-content-center">
        <div class="col-md-8">
            <div class="row">
                <div class="col-12 text-center">
                    <img src="{{ asset('images/quiz/rocketSuccess.gif')}}" class="fadein-left w-100">
                    <h3>Gefeliciteerd!</h3>
                    <p>Goed gedaan, jouw werk is hier afgerond</p>
                    <br>
                    <div class="row d-flex justify-content-center">
                        <div class="col-md-8">
                            <div class="row d-flex justify-content-center">
                                <div
                                    class="col-md-6 d-flex justify-content-center justify-content-md-start align-items-center">
                                    <h4 class="ml-2"> Jouw behaalde score: </h4>
                                </div>
                                <div
                                    class="col-md-6 d-flex justify-content-center justify-content-md-end align-items-center">
                                    <span class="score mr-2"><?php echo $score ?></span>
                                </div>
                            </div>
                        </div>
                        <div class="col-12">
                            <ul class="nav nav-tabs border-0 justify-content-center mt-3">  
                                <li class="list-unstyled">
                                    <a href="{{ route('accountBeheer')}}"
                                        class="btn btn-dark w-100 my-3 button-cst">Terug naar overzicht
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
@endsection

<!-- define section scripts -->
@section('script')
<script src="{{ asset('js/registration/register.js')}}" type="text/javascript"></script>
@endsection