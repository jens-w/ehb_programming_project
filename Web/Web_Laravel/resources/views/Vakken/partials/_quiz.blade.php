<div class="row">
    <div class="col-md-6">
        <div class="my-3">
            <h5 class="title">
                <?php echo $quiz['quiznaam'] ?>
            </h5>
            <p class="description">
                <?php echo $quiz['quizomschrijving'] ?>
            </p>
        </div>
    </div>
    <div class="col-md-6">
        <div class="my-3 d-flex align-items-center justify-content-center justify-content-md-end">
            <!-- route om quizz te kunnen starten -->
            <!-- route('startQuiz', ['quizId' => quiz['id']] ) -->
            <ul class="nav nav-tabs border-0">
                <li class="list-unstyled">
                <a class="d-flex" href="{{route('getQuiz', ['quizId' =>  $quiz['id'], 'naam' => $quiz['quiznaam'] ])}}"><span>Start quiz</span><i class="fas fa-arrow-right"></i></i></a>
                </li>
            </ul>
        </div>
    </div>
</div>