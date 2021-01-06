<div class="row question-wrapper">
    <div class="col-12">
        <div class="my-3">
            <h5 class="question">
                <?php echo $vraag['vraag'] ?>
            </h5>
            <ul class="list-unstyled">
                @foreach($vraag['antwoorden'] as $antwoord)
                @include('Quizzen/partials/._antwoord')
                @endforeach
            </ul>
        </div>
    </div>
    <div class="col-sm-6">
        <div class="my-3 d-flex align-items-center">
            <!-- route om quizz te kunnen starten -->
            <!-- route('startQuiz', ['quizId' => quiz['id']] ) -->
            <ul class="nav nav-tabs border-0">
                <li class="list-unstyled">
                </li>
            </ul>
        </div>
    </div>
</div>