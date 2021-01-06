<!-- navigatie // -->

<nav class="navbar navbar-light bg-light">
    <div class="container-fluid bg-white">
        <div class="row w-100">
            <div class="col-4 nav-menu d-flex align-items-center">
                <div class="row d-flex align-items-center">
                    <div class="col-4 d-flex d-md-none justify-content-start align-items-center">
                        <a class="navContentLink" data-toggle="collapse" href="#navContent" role="button"
                            aria-expanded="false" aria-controls="navbar">
                            <i class="fas fa-bars"></i>
                            <i class="fas fa-times"></i>
                        </a>
                    </div>

                    <div class="col-8 d-none d-md-flex justify-content-center align-items-center">
                     @if(session()->has('userData'))
                        <div class="col-12 d-flex welcome-msg">
                            <p>Welkom terug, {{ session('userData')->voornaam}}!</p>   
                        </div>                      
                     @endif
                    </div>
                </div>
            </div>
            <div class="col-4 d-flex justify-content-center">
                <a class="" href="{{ route('homepage')}}"><img class="" src="{{asset('/images/home/banner-home_1.png')}}" /></a>
            </div>
            <div class="col-4 d-flex justify-content-center align-items-center justify-content-md-end">
                <div class="row d-flex justify-content-center align-items-center">
                    <div class="col-12 d-none d-md-flex justify-content-center align-items-center">
                      @include('partials._nav_userData')   
                    </div>
                </div>
            </div>
        </div>
        <div class="row w-100 collapse" id="navContent">
            <div class="col-12 d-flex d-md-none justify-content-center">
                <div class="my-3">
               @include('partials._nav_userData')
                </div>
            </div>
        </div>
    </div>
</nav>