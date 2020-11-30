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
                        <a data-toggle="tab" class="d-flex" href="#AccountStats"><i
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
                                <?php echo $AccountViewModel ->email ?>
                            </div>
                            <div class="col-6">
                                Type:
                            </div>
                            <div class="col-6 sub-title">
                                <?php echo $AccountViewModel->type ?>
                            </div>
                            <div class="col-12 d-flex justify-content-end">
                                <ul class="nav nav-tabs border-0">
                                    <li class="list-unstyled">
                                        <a data-toggle="tab" class="d-flex" href="#AccountEdit"><span>Bewerken</span><i
                                                class="fas fa-arrow-right"></i></i></a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="AccountEdit">
                        <form method="post" action="{{ route('update') }}">

                            <input type="hidden" name="_token" value="{{ csrf_token() }}">
                            <div class="form-group">
                                <input type="hidden" name="id" value="<?php echo $AccountViewModel->id ?>">

                                <label>Voornaam </label>
                                <input name="voornaam" class="form-control"
                                    value='<?php echo $AccountViewModel->voornaam ?>'>
                            </div>
                            <div class="form-group">
                                <label>Email: </label>
                                <input name="email" class="form-control"
                                    value='<?php echo $AccountViewModel ->email ?>'>
                            </div>


                            <button type="update" class="btn btn-default">Update</button>
                        </form>
                    </div>
                    <div class="tab-pane fade" id="AccountStats">
                        baihjdpazejfefeqfffe
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>


// Javascript to enable link to tab and open the correct one on load
var hash = document.location.hash;
var prefix = "tab_";
if (hash) {
    $('.nav-tabs a[href="'+hash.replace(prefix,"")+'"]').tab('show');
}
else {
    $('a[href="#AccountInfoTab"]').tab('show');
}

// Change hash for page-reload
$('.nav-tabs a').on('shown', function (e) {
    window.location.hash = e.target.hash.replace("#", "#" + prefix);
});
</script>