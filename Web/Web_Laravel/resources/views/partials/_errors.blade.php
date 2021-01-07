<!-- check wether you have errors in session active (komende dus van api)  
    Session::pull trekt de session binnen en VERWIJDERD deze ook direct indien aanwezig.
-->
@if(Session::has('errorsApi'))
<?php
$errors = Session::pull("errorsApi");
?>

@foreach($errors as $key => $out)
@if($key === 'succesvol_aangemaakt')
<h2 class="alert alert-success  p-1">{{ __("errors.".$key)}}</h2> <br />
@else
<h2 class="alert alert-danger  p-1">{{ __("errors.".$key)}}</h2> <br />
@endif
@endforeach
@endif