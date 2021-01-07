<div class="col-lg-6 d-flex justify-content-center justify-content-lg-start align-items-center">
    <a href="{{route('detailCourse', ['vakId' =>  $vak['id'], 'naam' => $vak['naam'] ])}}">
        <div class="vak-outer">
            <div class="vak">
                <div class="front">
                    <div class="cover">                  
                        <p class=author> <span class="vak-title mx-2 button-cst">
                                <?php echo $vak->naam ?>
                            </span></p>
                    </div>
                </div>
                <div class="left-side">
                    <h2>
                        <span><?php echo $AccountViewModel->voornaam ?></span>
                        <span><?php echo $AccountViewModel->familienaam ?></span>
                    </h2>
                </div>
            </div>
        </div>
    </a>
</div>