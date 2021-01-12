<!-- ///// Check if the user is an admin \\\\\\\ -->
<!-- If so, render the other admins & docenten-->
@if(strtolower(Session::get('rol')) == "admin")
<h2>Admins:</h2>
<?php $rol = 'admin' ?>
@foreach($AccountViewModel->userList['admins'] as $user)

@include('AccountBeheer/Gegevens/partials._user')

@endforeach
<h2>Docenten:</h2>
<?php $rol = 'docent' ?>
@foreach($AccountViewModel->userList['docenten'] as $user)
@include('AccountBeheer/Gegevens/partials._user')

@endforeach

@endif
<!-- ///// Check if the user is a docent or if its still an admin \\\\\\\ -->
<!-- If so, render the students aswell -->
@if(strtolower(Session::get('rol')) == "admin" or strtolower(Session::get('rol')) == "docent")
<h2>Studenten:</h2>
<?php $rol = 'student' ?>
@foreach($AccountViewModel->userList['studenten'] as $user)

@include('AccountBeheer/Gegevens/partials._user')

@endforeach
@endif
<!-- ///// render the final users //// -->
@if(strtolower(Session::get('rol')) == "admin")
<h2>Users:</h2>
<?php $rol = 'user' ?>
@foreach($AccountViewModel->userList['users'] as $user)

@include('AccountBeheer/Gegevens/partials._user')

@endforeach
@endif