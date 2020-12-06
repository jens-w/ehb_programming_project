@extends('main')


@section('content')
<div class="row d-flex justify-content-center">
    <div class="col-md-8">
        <div class="row">
            <div class="col-md-6">
                <img src="{{ asset('images/account/registration/message.png')}}" class="fadein-left w-100">
            </div>
            <div class="col-md-6">
                <h2>Succesvol geregistreerd!</h2>
                <h3>Je ontvangt zo dadelijk een bevestigingsmail.<h3>
                <h3>Geen zin om te wachten?</h3>
                <a href="Account/overview/#tab_AccountStats" class="btn btn-dark w-100 btn-home">Ga direct aan de
                        slag!</a>
            </div>
        </div>
    </div>
</div>
@endsection

<!-- define section scripts -->
@section('script')
<script src="{{ asset('js/registration/register.js')}}" type="text/javascript"></script>
@endsection