<div class="container-fluid min-vw-100 min-vh-100 d-flex align-items-center">
    <div class="row">
        <div class="col-12">
            <img src="{{ asset('images/password/coming-soon.png')}}" />
        </div>
        <div class="row">
            <div class="col-12">                                  
                <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseAdminLogin" aria-expanded="false" aria-controls="collapseAdminLogin">
                        Login (admin)
                </button>               
                <div class="collapse" id="collapseAdminLogin">
                    <div class="card card-body">
                    <form method="POST" action="{{ route('loginPasswordPage') }}">
                        <div class="form-group">
                            <label for="password"></label>
                            <input type="password" name="password" id="password"/>
                        </div>
                    </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>