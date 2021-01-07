<div class="tab-pane fade in form-add-question" id="NewQuestion{{$quiz['id']}}">
    <div class="row">
        <div class="col-12">
            {{ Form::open([ 'url'=>route('addNewQuestion'), 'method'=>'post' ]) }}
            <input type="hidden" name="quizId" id="quizId" value="{{$quiz['id']}}">
            <div class="row">
                <div class="col-12">
                    <div class="form-group">
                        <label>Vraag: </label>
                        <input name="questionName" id="questionName" class="form-control">
                    </div>
                    <label for="simpelQuestion">Kan de vraag maar één juist antwoord hebben?</label>
                    <input type="checkbox" name="simpelQuestion[]" id="simpleQuestionChkbx">

                    <div class="form-simple">
                        <div class="form-group">
                            <label>Het juiste antwoord weergeven?: </label>
                            <select name="showCorrectAnswer[]">
                                <option value="true">Ja</option>
                                <option value="false">Neen</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-complex">
                        <div class="form-group">
                            <label>Minimum aantal juiste antwoorden: </label>
                            <select name="minAnswers[]">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Aantal juiste antwoorden nodig om te slagen: </label>
                            <select name="correctAnswers[]">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                            </select>
                        </div>
                        <!-- antwoorden -->

                    </div>
                    <div class="form-group">
                        <label>Antwoorden:</label>

                        <ul class="answersWrapper">
                             
                        </ul>
                        <a type="button" role="button" class="btnAddQuestion button-cst text-white m-0"><i class="fas fa-plus"></i>Vraag toevoegen</a>
                    </div>
                </div>
            </div>

            <button type="submit" class="btn btn-dark button-cst">Vraag toevoegen</button>
            {{ Form::close() }}
        </div>
    </div>
</div>