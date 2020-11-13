<div class="col-12">
<div class="navbar">
   <div class="navbar-inner">      
       <ul class="nav">
           <li><a href="{{route('home')}}">Home</a></li>
           <li><a href="{{route('contact')}}">Contact</a></li>
           @if (Route::has('login'))
                <div class="hidden fixed top-0 right-0 px-6 py-4 sm:block">
                    @auth <!-- als user is aangemeld -->
                        <a href="{{route('home')}}" class="text-sm text-gray-700 underline">Home</a>
                            <form method="POST" action="{{ route('logout') }}">
                                <!-- Token auth. MOET aanwezig zijn in logout form -->
                                {{ csrf_field() }}
                                <button onclick=$(this).closest().submit()>xxx</button>
                            </form>
                    @else <!-- als geen user is aangemeld -->
                        <a href="{{ route('login') }}" class="text-sm text-gray-700 underline">Login</a>                      
                        @if (Route::has('register'))
                            <a href="{{ route('register') }}" class="ml-4 text-sm text-gray-700 underline">Register</a>
                        @endif
                    @endif
                </div>  
            @endif
       </ul>
   </div>
</div>
</div>