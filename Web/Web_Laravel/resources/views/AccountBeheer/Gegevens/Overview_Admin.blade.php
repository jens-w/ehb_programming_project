@extends('main')

@section('style')
<!-- Import custom Navigation file -->
<link rel="stylesheet" href="{{ asset('css/Account/Account.css')}}">
@endsection



@section('content')
<!-- Main account beheer paneel -->
<!-- showing any status messages -->
<div class="container">
    <div class="row">
        <div class="col-12">
        @if($errors->any())
     
            @foreach($errors->getMessages() as $key => $message)
            <!--          
            $key bevat de foutcode (bv. voornaam_ongeldig ), 
            Deze is terug te vinden in de 'lang/en/auth.php' file, met respectievelijk zijn waarde
            om deze waarde aan te roepen gebruik je FILENAME.STRINGKEYWAARDE
            -->
            <h2 class="alert alert-danger  p-1">{{ __("auth.".$key)}}</h2> <br />
            @endforeach
        @endif
        </div>
    </div>
</div>

<div id="AccountInfo" class="my-5">
  @include('AccountBeheer/Gegevens.AccountInfo_Admin')
</div>
@endsection


<!-- Temp fix omdat boostrap zijn active klasse niet init. achteraf ?? 
FIXED : 
Als je gebruik maakt van tabs - moet je de tab-links ook in een enclosing 'nav-tabs' klasse steken 
-->



@section('script')
<script src="{{asset('js/Account/Account.js')}}"></script>
@endsection