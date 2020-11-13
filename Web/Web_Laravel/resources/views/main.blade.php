<!-- MAIN LAYOUT PAGINA-->

<!doctype html>
<html @lang('nl')>
<head>
 @include('partials/_head')
 @include('partials/_css')
</head>

<body id="page-top" >
   @include('partials/_nav')

<div class="container" >
  @yield('content')
</div>

  @include('partials/_footer')
  @include('partials/_script')

@yield('script')
 </body>
</html>