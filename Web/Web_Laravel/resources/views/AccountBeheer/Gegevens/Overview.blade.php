@extends('main')

@section('content')


<!-- Main account beheer paneel -->
<!-- $Account variabele wordt door AccountBeheer controller meegegeven -->

<div class="row">
    <!-- tab links -->
    <div class="col-sm-4 col-md-2">
        <ul class="list-unstyled">
            <li class="active"><i class="far fa-user"></i><a data-toggle="tab" href="#accountGegevens"
                    onclick="RemoveActive();">Account
                    Gegevens</a></li>
            <li><i class="fas fa-book-open"></i><a data-toggle="tab" href="#Vakken" onclick="RemoveActive();">Vakken</a>
            </li>
        </ul>
    </div>
    <!-- content -->
    <div class="col-sm-8 col-md-10">
        <div class="tab-content">
            <div id="accountGegevens" class="tab-pane fade show in active">
                <div class="row">
                    <div class="col-6">
                        Voornaam:
                    </div>
                    <div class="col-6">
                        <?php echo $Account->voornaam ?>
                    </div>
                    <div class="col-6">
                        Familienaam:
                    </div>
                    <div class="col-6">
                        <?php echo $Account->familienaam ?>
                    </div>
                    <div class="col-6">
                        Email:
                    </div>
                    <div class="col-6">
                        <?php echo $Account->email ?>
                    </div>
                    <div class="col-6">
                        Type:
                    </div>
                    <div class="col-6">
                        <?php echo $Account->type ?>
                    </div>
                </div>
            </div>
            <div id="Vakken" class="tab-pane fade">

            </div>
            <div id="AccountInfo">
                
            </div>
        </div>

    </div>
</div>

@endsection

<!-- Temp fix omdat boostrap zijn active klasse niet init. achteraf ?? -->


@section('script')
<script>
function RemoveActive() {
    $('a').each(function() {
        $(this).removeClass('active');
    })
}
</script>
<script src="{{asset('js/Account/Account.js')}}"></script>
@endsection