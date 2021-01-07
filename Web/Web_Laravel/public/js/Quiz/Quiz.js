

$(document).ready(function () {

    $('#simpleQuestionChkbx').change(
        function () {
            if ($(this).hasClass("toggleon")) {
                $(this).removeAttr("checked"); //uncheck
                $(this).removeClass("toggleon"); //toggle off            
                $('.form-simple').addClass('d-none');
                $('.form-complex').removeClass('d-none');
            } else {
                $(this).attr("checked", "checked");
                $(this).addClass("toggleon");
                $('.form-complex').addClass('d-none');
                $('.form-simple').removeClass('d-none');
                // do other stuff
            }
        }
    );
    
    // add question     
    $('.btnAddQuestion').click(function(){
        var formBody = '<li><div class="form-group"><label></label><input name="answers[]" id="" value=""><label>Juist?</label><input type="hidden" name="correctAnswersQuestion[]" value="0" class="txtDefault"><input type="checkbox" onclick="if($(this).is(\':checked\')){$(this).siblings(\'.txtDefault\').val(1)}else{$(this).siblings(\'.txtDefault\').val(0)}" class="checkboxCorrectAnswer" ></div></li>';
        // add script
        $(this).siblings('.answersWrapper').append(formBody);     
    });
   
});

