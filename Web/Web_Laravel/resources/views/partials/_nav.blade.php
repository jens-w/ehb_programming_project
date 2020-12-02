<!-- navigatie // -->

<nav class="navbar navbar-light bg-light">
    <div class="container-fluid bg-white">
        <div class="row w-100">
            <div class="col-md-4 nav-menu d-flex align-items-center">
                <div class="row d-flex align-items-center">
                    <div class="col-md-4 d-flex justify-content-start align-items-center">
                        <a class="navContentLink" data-toggle="collapse" href="#navContent" role="button"
                            aria-expanded="false" aria-controls="navbar">
                            <i class="fas fa-bars"></i>
                            <i class="fas fa-times"></i>
                        </a>
                    </div>

                    <div class="col-md-8 d-flex justify-content-center align-items-center">
                     @if(session()->has('userData'))
                        <div class="col-12 welcome-msg">
                            <p>Welkom terug,</p>
                            <p>  {{ session('userData')['voornaam']}} !</p>
                        </div>                      
                     @endif
                    </div>
                </div>
            </div>
            <div class="col-md-4 d-flex justify-content-center">
                <a class="" href="{{ route('home')}}"><img class="" src="{{asset('images\home\banner-home_1.png')}}" /></a>
            </div>
            <div class="col-md-4 d-flex justify-content-center align-items-center">
                <div class="row d-flex justify-content-center align-items-center">
                    <div class="col-12 d-flex justify-content-center align-items-center">
                      @if(session()->has('userData'))
                        <div class="user-link d-flex">
                            <!-- link naar accountbeheer -->
                            <a href="{{ route('accountBeheer')}}" class="text-decoration-none d-flex align-items-baseline">
                                <i class="far fa-user"></i>
                                {{ session('userData')['voornaam']}}
                            </a>
                            <!-- TODO: Link/route naar statistieken -->
                            <a href="" class="text-decoration-none d-flex align-items-baseline">
                                <i class="fas fa-chart-line"></i>
                            </a>
                            <a href="{{ route('forgetSession')}}" class="btn btn-dark">Uitloggen</a>
                        </div>                      
                        @else
                        <!-- Routes benamingen worden gedefiniëerd onder routes/web.php -->
                        <a href="{{route('loginInit')}}" class="btn btn-dark mx-2">Inloggen</a>
                        <a href="{{route('createUser')}}" class="btn btn-dark">Registreren</a>                                              
                        @endif
                    </div>
                </div>
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