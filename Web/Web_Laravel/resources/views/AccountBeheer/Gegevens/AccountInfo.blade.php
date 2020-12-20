<div class="account-info-wrapper">
    <div class="container">
        <div class="row">
            <!-- tab links -->
            <div class="col-sm-3">
                <ul class="nav nav-tabs border-0">
                    <li class="d-flex align-items-center">
                        <a data-toggle="tab" class="d-flex" href="#AccountInfoTab"><i
                                class="far fa-user"></i><span>Account Gegegevens</span></a>
                    </li>
                    <li class="d-flex align-items-center">
                        <a data-toggle="tab" class="d-flex" href="#AccountCourses"><i
                                class="fas fa-book-open"></i><span>Vakken</span></a>
                    </li>
                </ul>
            </div>
            <!-- tab effective -->
            <div class="col-sm-9">
                <div class="tab-content border-0">
                    <div class="tab-pane fade in active" id="AccountInfoTab">
                        <div class="row">
                            <div class="col-6 sub-title">
                                Voornaam:
                            </div>
                            <div class="col-6 ">
                                <?php echo $AccountViewModel->voornaam ?>
                            </div>
                            <div class="col-6">
                                Familienaam:
                            </div>
                            <div class="col-6 sub-title">
                                <?php echo $AccountViewModel->familienaam ?>
                            </div>
                            <div class="col-6">
                                Email:
                            </div>
                            <div class="col-6 sub-title">
                                <?php echo $AccountViewModel->email ?>
                            </div>

                        </div>

                        {{ Form::open([ 'url'=>route('requestNewUserkey'), 'class'=>'my-2 row', 'method'=>'post' ]) }}
                        <div class="col-6">
                            Userkey (Nodig voor discordbot):
                        </div>
                        <div class="col-6 sub-title">
                            <?php echo $AccountViewModel->userKey ?>
                        </div>
                        <div class="form-group">
                            <input name="userKey" type="hidden" class="form-control hidden"
                                value='<?php echo $AccountViewModel->userKey ?>'>

                        </div>
                        <div class="col-6">
                            <button type="update" class="btn btn-dark">Refresh Key</button>
                        </div>
                        {{ Form::close() }}
                        <div class="col-12 d-flex justify-content-end">
                            <ul class="nav nav-tabs border-0">
                                <li class="list-unstyled">
                                    <a data-toggle="tab" class="d-flex" href="#AccountEdit"><span>Bewerken</span><i
                                            class="fas fa-arrow-right"></i></i></a>
                                </li>
                            </ul>
                        </div>

                    </div>
                    <div class="tab-pane fade" id="AccountEdit">
                        <form method="post" action="{{ route('update') }}">
                            <input type="hidden" name="id" value="<?php echo $AccountViewModel->id ?>">
                            <input type="hidden" name="_token" value="{{ csrf_token() }}">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Voornaam </label>
                                        <input name="voornaam" class="form-control"
                                            value='<?php echo $AccountViewModel->voornaam ?>'>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Familienaam </label>
                                        <input name="familienaam" class="form-control"
                                            value='<?php echo $AccountViewModel->familienaam ?>'>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Email: </label>
                                        <input name="email" class="form-control"
                                            value='<?php echo $AccountViewModel->email ?>'>
                                    </div>
                                </div>
                            </div>

                            <button type="update" class="btn btn-dark">Update</button>
                        </form>
                        {{ Form::open([ 'url'=>route('requestNewUserkey'), 'class'=>'my-2', 'method'=>'post' ]) }}
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Userkey (Belangerijk voor discord bot): </label>
                                    <input name="userKey" class="form-control"
                                        value='<?php echo $AccountViewModel->userKey ?>'>

                                </div>
                            </div>

                        </div>
                        <button type="update" class="btn btn-dark">Refresh Key</button>
                        {{ Form::close() }}
                    </div>
                    <div class="tab-pane fade" id="AccountCourses">
                        <div class="row">
                            @foreach($AccountViewModel->vakken as $vak)
                            <div class="col-6 sub-title">
                                <?php echo $vak->naam ?>
                            </div>
                            <div class="col-6 ">
                                <ul class="nav nav-tabs border-0">
                                    <li class="list-unstyled">
                                        <a data-toggle="tab" class="d-flex" href="#AccountEdit"><span>Quiz
                                                aanvragen</span><i class="fas fa-arrow-right"></i></i></a>
                                    </li>
                                </ul>
                            </div>
                            @endforeach

                        </div>


                    </div>
                </div>
            </div>
        </div>
    </div>
</div>