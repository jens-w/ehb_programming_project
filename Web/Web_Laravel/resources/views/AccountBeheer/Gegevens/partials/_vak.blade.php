<div class="col-md-6 sub-title d-flex align-items-center border">
    <i class="fas fa-book-open"></i>
    <span class="vak-title mx-2">
        <?php echo $vak->naam ?>
    </span>
</div>
<div class="col-md-6 d-flex align-items-center justify-content-md-end">
    <ul class="nav nav-tabs border-0">
        <li class="list-unstyled">
            <a class="d-flex" href="{{route('detailCourse', ['vakId' =>  $vak['id'], 'naam' => $vak['naam'] ])}}"><span>Details & Quizzen</span><i class="fas fa-arrow-right"></i></i></a>
        </li>
    </ul>
</div>