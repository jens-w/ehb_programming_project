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
            <h4 class="status-message-success">{{ $errors -> first()}}</h4>
            @endif
        </div>
    </div>
</div>

<div id="AccountInfo" class="my-5">
  @include('AccountBeheer/Gegevens.AccountInfo')
</div>
@endsection


<!-- Temp fix omdat boostrap zijn active klasse niet init. achteraf ?? 
FIXED : 
Als je gebruik maakt van tabs - moet je de tab-links ook in een enclosing 'nav-tabs' klasse steken 
-->



@section('script')
<script src="{{asset('js/Account/Account.js')}}"></script>
@endsection