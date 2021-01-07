<div class="row">
    <div class="col-md-6">
        <div class="my-3">
            <h5 class="title">
                <?php echo $quiz['naam'] ?>
            </h5>
            <p class="description">
                <?php echo $quiz['omschrijving'] ?>
            </p>
        </div>
    </div>
    <div class="col-md-6">
        <div class="my-3 d-flex align-items-center justify-content-center justify-content-md-end">


        <ul class="nav nav-tabs border-0">
            @if(Session::has('rol'))
            <?php
            $currentRol = Session::get("rol");
            $currentRol = strtolower($currentRol);
            ?>
            @if($currentRol == "docent")
           
                <li class="list-unstyled ml-0">
                <a data-toggle="tab" class="d-flex align-items-center" href="#NewQuestion{{ $quiz['id']}}"><span>Bewerk Quiz<i class="far fa-edit"></i></span></a>
                </li>
           
            @endif
            @endif
            <!-- route om quizz te kunnen starten -->
            <!-- route('startQuiz', ['quizId' => quiz['id']] ) -->
                <li class="list-unstyled">
                    <a class="d-flex" href="{{route('getQuiz', ['quizId' =>  $quiz['id'], 'naam' => $quiz['naam'] ])}}"><span>Start Quiz</span><i class="fas fa-arrow-right"></i></a>
                </li>
            </ul>
        </div>
    </div>
</div>
 
 