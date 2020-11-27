@extends('main')

@section('style')
<!-- Import custom Navigation file -->
<link rel="stylesheet" href="{{ asset('css/Account/Account.css')}}">
@endsection



@section('content')
<!-- Main account beheer paneel -->
 <!-- div wordt opgevuld door ajax call & dynamisch aangemaakt in partial view _AccountInfo.blade.php -->
<div id="AccountInfo" class="my-5">
</div>
@endsection


<!-- Temp fix omdat boostrap zijn active klasse niet init. achteraf ?? 
FIXED : 
Als je gebruik maakt van tabs - moet je de tab-links ook in een enclosing 'nav-tabs' klasse steken 
-->



@section('script')
<script src="{{asset('js/Account/Account.js')}}"></script>
@endsection