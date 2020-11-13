@extends('main')

<!-- define section styles -->

@section('style')
@endsection

<!-- define body content // check yield in the main layout -->
@section('content')
<div class="inner-container">
<form method="POST" action="CreatePost">
        {{ csrf_field() }}
        <div class="form-group">
            <label for="name">Naam opleiding:</label>
            <input type="text" class="form-control" id="naam" name="naam">
        </div>    
        <input type="submit" class="btn btn-primary">  
    </form>
</div>-
@endsection

<!-- define section scripts -->
@section('script')
@endsection