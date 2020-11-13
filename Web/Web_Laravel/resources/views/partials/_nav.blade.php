<!-- navigatie // -->

<nav class="navbar navbar-light bg-light">
    <div class="container-fluid bg-white">
        <div class="row w-100">
            <div class="col-md-4 nav-menu d-flex align-items-center">
                <div class="row d-flex align-items-center">
                    <div class="col-6 d-flex justify-content-start align-items-center">
                        <a class="navContentLink" data-toggle="collapse" href="#navContent" role="button"
                            aria-expanded="false" aria-controls="navbar">
                            <i class="fas fa-bars"></i>
                            <i class="fas fa-times"></i>
                        </a>
                    </div>

                    <div class="col-6 d-flex justify-content-center align-items-center">
                        @auth
                        <ul class="list-unstyled">
                            <li>Welkom terug, {{ Auth::user()->voornaam }}!</li>
                            <!-- Routes benamingen worden gedefiniëerd onder routes/web.php -->
                            <li><a href="{{route('logout')}}">Uitloggen</a></li>
                        </ul>
                        @endauth
                    </div>
                </div>
            </div>
            <div class="col-md-4 d-flex justify-content-center">
                <img src="{{ asset('images/logo/Main_Logo_V1.png')}}" />
            </div>
            <div class="col-md-4 d-flex justify-content-center align-items-center">
                @auth
                <div class="row d-flex justify-content-center align-items-center">
                    <div class="col-6">
                        <a type="button" class="btn btn-light" href="{{ route('accountBeheer')}}">AccountBeheer</a>
                    </div>
                </div>
                @endauth
            </div>
        </div>
        <div class="row w-100 collapse" id="navContent">
            <div class="col-12 d-flex justify-content-start">
                @guest
                <ul class="list-unstyled">
                    <!-- Routes benamingen worden gedefiniëerd onder routes/web.php -->
                    <li class=""><a href="{{route('createUser')}}">Registreren</a></li>
                    <li><a href="{{route('loginInit')}}">Inloggen</a></li>
                </ul>
                @endguest
                @auth
                <ul class="list-unstyled">
                    <li>
                        <!-- AUTH CONTENT MENU -->
                    </li>
                </ul>
                @endauth
            </div>
        </div>
    </div>
</nav>