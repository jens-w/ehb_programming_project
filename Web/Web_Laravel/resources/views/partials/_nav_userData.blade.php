@if(session()->has('userData'))
<div class="user-link d-flex align-items-center">
    <!-- link naar accountbeheer -->
    <a href="{{ route('accountBeheer')}}" class="text-decoration-none d-flex align-items-baseline">
        <i class="far fa-user"></i>
        {{ session('userData')->voornaam }}
    </a>
    <!-- TODO: Link/route naar statistieken -->
    <a href="" class="text-decoration-none d-flex align-items-baseline">
        <i class="fas fa-chart-line"></i>
    </a>
    <a href="{{ route('forgetSession')}}" class="btn button-cst">Uitloggen</a>
</div>
@else
<!-- Routes benamingen worden gedefiniëerd onder routes/web.php -->
<a href="{{route('loginInit')}}" class="btn button-cst mx-2">Inloggen</a>
<a href="{{route('createUser')}}" class="btn button-cst">Registreren</a>
@endif