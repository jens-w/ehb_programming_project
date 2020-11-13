@extends('main')


@section('content')
<div class="row d-flex justify-content-center">
    <div class="col-md-6">
        <h2>Inloggen</h2>
        <form autocomplete="off" method="POST" action="{{ route('login') }}">
            {{ csrf_field() }}
            <!--since chrome autocompletes first two inputs, create two hidden ones to prevent rest from autocompleting. -->
            <input style="display:none">
            
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" class="form-control" id="email" name="email">
            </div>
            <div class="form-group">
                <label for="userkey">Userkey:</label>
                <input type="password" autocomplete="false" class="form-control disabled show" id="password"
                    name="password">                
            </div>
            <div class="form-group">
                <button style="cursor:pointer" type="submit" class="btn btn-primary">Inloggen</button>
            </div>

        </form>
    </div>
</div>
@endsection

<!-- define section scripts -->
@section('script')
<script src="{{ asset('js/registration/register.js')}}" type="text/javascript"></script>
@endsection