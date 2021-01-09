

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
    var counter = 0;
    // add question     
    $('.btnAddQuestion').click(function(){
        counter += 1;
        var formBody = '<div class="form-group"><label class="count-answ">'+ counter +'.</label><input class="inpt-cst ml-2 w-100"  name="answers[]" id="" value=""><label>Juist?</label><input type="hidden" name="correctAnswersQuestion[]" value="0" class="txtDefault"><input type="checkbox" onclick="if($(this).is(\':checked\')){$(this).siblings(\'.txtDefault\').val(1)}else{$(this).siblings(\'.txtDefault\').val(0)}" class="checkboxCorrectAnswer" ><i class="fas fa-expand-alt changeTxtInpt" role="button" onclick="changeInput(this)"></i></div>';
        // add script
        $(this).siblings('.answersWrapper').append(formBody);     
    });
   
    

   

   
});

function changeInput(elem){
    
    
    if(!($(".inpt-cst").is("textarea"))){ // check if its a textbox
        // if not create a textbox with same prop
        // replace the input with the textfield
        var className = $(".inpt-cst").attr('class');
        textbox = $(document.createElement('textarea')).attr('name', 'answers[]').attr('class', className);
        $(elem).siblings(".inpt-cst").replaceWith(textbox);
    } else {
        // reverse logic
            var className = $(".inpt-cst").attr('class');
            input = $(document.createElement('input')).attr('name', 'answers[]').attr('class', className);
            $(elem).siblings(".inpt-cst").replaceWith(input);
        }
    }

