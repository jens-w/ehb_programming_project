@extends('main')


@section('content')
<!--  ERROR MESSAGES -->

<div class="row d-flex justify-content-center">
    <div class="col-md-6">
        @if($errors->any())
            @foreach($errors->getMessages() as $key => $message)
            <!--          
            $key bevat de foutcode (bv. voornaam_ongeldig ), 
            Deze is terug te vinden in de 'lang/en/auth.php' file, met respectievelijk zijn waarde
            om deze waarde aan te roepen gebruik je FILENAME.STRINGKEYWAARDE
            -->
            <h2 class="alert-danger p-1">{{ __("auth.".$key)}}</h2> <br />
            @endforeach
        @endif
    </div>
</div>


<div class="row d-flex justify-content-center">
    <div class="col-md-6">
        <h2>Registreren</h2>
        <form method="POST" action="{{ route('storeUser') }}">
            @csrf

            <div class="form-group row">
                <label for="name" class="col-md-4 col-form-label text-md-right">{{ __('Name') }}</label>

                <div class="col-md-6">
                    <input id="voornaam" type="text" class="form-control @error('name') is-invalid @enderror" name="voornaam" value="{{ old('voornaam') }}" required autocomplete="name" autofocus>

                    @error('name')
                    <span class="invalid-feedback" role="alert">
                        <strong>{{ $message }}</strong>
                    </span>
                    @enderror
                </div>
            </div>
            <div class="form-group row">
                <label for="lastname" class="col-md-4 col-form-label text-md-right">{{ __('Lastname') }}</label>

                <div class="col-md-6">
                    <input id="familienaam" type="text" class="form-control @error('name') is-invalid @enderror" name="familienaam" value="{{ old('familienaam') }}" required autocomplete="name" autofocus>

                    @error('name')
                    <span class="invalid-feedback" role="alert">
                        <strong>{{ $message }}</strong>
                    </span>
                    @enderror
                </div>
            </div>
            <div class="form-group row">
                <label for="email" class="col-md-4 col-form-label text-md-right">{{ __('E-Mail Address') }}</label>

                <div class="col-md-6">
                    <input id="email" type="email" class="form-control @error('email') is-invalid @enderror" name="email" value="{{ old('email') }}" required autocomplete="email">

                    @error('email')
                    <span class="invalid-feedback" role="alert">
                        <strong>{{ $message }}</strong>
                    </span>
                    @enderror
                </div>
            </div>

            <div class="form-group row">
                <label for="type" class="col-md-4 col-form-label text-md-right">Type</label>

                <div class="col-md-6">
                    <select id="type" name="type">
                        <option value="1">Student</option>
                        <option value="2" selected="selected">Teacher</option>
                    </select>
                    @error('email')
                    <span class="invalid-feedback" role="alert">
                        <strong>{{ $message }}</strong>
                    </span>
                    @enderror
                </div>
            </div>

            <div class="form-group row">
                <label for="password" class="col-md-4 col-form-label text-md-right">{{ __('Password') }}</label>

                <div class="col-md-6">
                    <input id="password" type="password" class="form-control @error('password') is-invalid @enderror" name="password" required autocomplete="new-password">

                    @error('password')
                    <span class="invalid-feedback" role="alert">
                        <strong>{{ $message }}</strong>
                    </span>
                    @enderror
                </div>
            </div>

            <div class="form-group row">
                <label for="password-confirm" class="col-md-4 col-form-label text-md-right">{{ __('Confirm Password') }}</label>

                <div class="col-md-6">
                    <input id="password-confirm" type="password" class="form-control" name="password_confirmation" required autocomplete="new-password">
                </div>
            </div>

            <div class="form-group row mb-0">
                <div class="col-md-6 offset-md-4">
                    <button type="submit" class="btn btn-primary">
                        {{ __('Register') }}
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
@endsection

<!-- define section scripts -->
@section('script')
<script src="{{ asset('js/registration/register.js')}}" type="text/javascript"></script>
@endsection