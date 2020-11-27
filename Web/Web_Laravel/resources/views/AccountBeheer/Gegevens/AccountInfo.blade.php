<div class="account-info-wrapper">
    <div class="container">
        <div class="row">
            <!-- tab links -->
            <div class="col-sm-3">
                <ul class="nav nav-tabs border-0">
                    <li class="d-flex align-items-center">
                        <a data-toggle="tab" href="#AccountInfoTab"><i class="far fa-user"></i>Account Gegegevens</a>
                    </li>
                    <li class="d-flex align-items-center">
                        <a data-toggle="tab" href="#AccountStats"><i class="fas fa-book-open"></i>Statistieken</a>
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
                        </div>
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
// on first load events
$(document).ready(function() {
    // init first click on load
    $('a[href="#AccountInfoTab"]').click();
})
</script>