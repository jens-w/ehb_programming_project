<div class="account-info-wrapper">

    <div class="container">
        <div class="row">
            <!-- tab links -->
            <div class="col-lg-3">
                <ul class="nav nav-tabs border-0">
                    <li class="d-flex align-items-center">
                        <a data-toggle="tab" class="d-flex" href="#AccountInfoTab"><i class="far fa-user"></i><span>Account</span></a>
                    </li>
                    <li class="d-flex align-items-center">
                        <a data-toggle="tab" class="d-flex" href="#AccountCourses"><i class="fas fa-book-open"></i><span>Vakken</span></a>
                    </li>
                </ul>
            </div>
            <!-- tab effective -->
            <div class="col-lg-9">
                <div class="tab-content border-0">
                    <div class="tab-pane fade in active" id="AccountInfoTab">
                        <div class="row">
                            <div class="col-6 sub-title">
                                Voornaam:
                            </div>
                            <div class="col-6 ">
                                <?php echo $AccountViewModel->voornaam ?>
                            </div>
                            <div class="col-6 sub-title">
                                Familienaam:
                            </div>
                            <div class="col-6 ">
                                <?php echo $AccountViewModel->familienaam ?>
                            </div>
                            <div class="col-6 sub-title">
                                Email:
                            </div>
                            <div class="col-6 ">
                                <?php echo $AccountViewModel->email ?>
                            </div>
                            <div class="col-6 sub-title">
                                Type:
                            </div>
                            <div class="col-6 ">
                                <?php echo $AccountViewModel->type ?>
                            </div>
                            <div class="col-12 d-flex justify-content-start">
                           
                                
                                    <a data-toggle="tab" class="d-flex btn btn-light" href="#AccountEdit"><span>Bewerken</span><i class="fas fa-arrow-right"></i></i></a>
                              
                        </div>
                        </div>

                        {{ Form::open([ 'url'=>route('requestNewUserkey'), 'class'=>'my-2 row', 'method'=>'post' ]) }}
                        <div class="col-6 sub-title">
                            Userkey (Nodig voor discordbot):
                        </div>
                        <div class="col-6 ">
                            <?php echo $AccountViewModel->userKey ?>
                        </div>
                        <div class="form-group">
                            <input name="userKey" type="hidden" class="form-control hidden" value='<?php echo $AccountViewModel->userKey ?>'>

                        </div>
                        <div class="col-6">
                            <button type="update" class="btn btn-light">Refresh Key</button>
                        </div>
                        {{ Form::close() }}
                        

                    </div>
                    <div class="tab-pane fade" id="AccountEdit">
                        <form method="post" action="{{ route('update') }}">
                            <input type="hidden" name="id" value="<?php echo $AccountViewModel->id ?>">
                            <input type="hidden" name="_token" value="{{ csrf_token() }}">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Voornaam </label>
                                        <input name="voornaam" class="form-control" value='<?php echo $AccountViewModel->voornaam ?>'>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Familienaam </label>
                                        <input name="familienaam" class="form-control" value='<?php echo $AccountViewModel->familienaam ?>'>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Email: </label>
                                        <input name="email" class="form-control" value='<?php echo $AccountViewModel->email ?>'>
                                    </div>
                                </div>
                            </div>
                            <input type="hidden" name="userKey" value='<?php echo $AccountViewModel->userKey ?>'>
                            <button type="update" class="btn btn-dark">Update</button>
                        </form>
                        {{ Form::open([ 'url'=>route('requestNewUserkey'), 'class'=>'my-2', 'method'=>'post' ]) }}
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Userkey (Belangerijk voor discord bot): </label>
                                    <input name="userKey" class="form-control" value='<?php echo $AccountViewModel->userKey ?>'>

                                </div>
                            </div>

                        </div>
                        <button type="update" class="btn btn-dark">Refresh Key</button>
                        {{ Form::close() }}
                    </div>
                    <div class="tab-pane fade" id="AccountCourses">
                        <div class="row">
                            @foreach($AccountViewModel->vakken as $vak)
                              @include('AccountBeheer/Gegevens/partials._vak')
                            @endforeach
                        </div>
                    </div>
                    <div class="tab-pane fade" id="VakDetail">
                        <div class="row">
                            @foreach($AccountViewModel->vakken as $vak)
                              @include('AccountBeheer/Gegevens/partials._vak')
                            @endforeach
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>