function createQuestionSubmit() {
    var isFormSubmit = true;

    var questionId = $("#main_question_id").val();
    var startChar = questionId.substring(0,1);

    if (!questionId.trim()) {
        isFormSubmit = false;
        $("#main_question_id").css('border', '1px dashed #EB340A');
        $("#display_id_error_message").css('display', 'inline');
    } else {
        if ( startChar != 'Q' ) {
            $("#success_flash_message").html("The Question ID must startwith 'Q' ");
            $("#success_flash_message").css('display', 'inline');
            $("#success_flash_message").css('width', '500px');
            $("#main_question_id").val("");
            setTimeout(function() {
                $("#success_flash_message").css('display', 'none');
            }, 6000);
            isFormSubmit = false;
            return;
        }
       szMsg = "[,;:=!^#_%&'/<>~@$*()_+-`]";
       for (i = 1; i < szMsg.length + 1; i++) {
           if (questionId.indexOf(szMsg.substring(i-1,i))>-1) {
               $("#success_flash_message").html("Illegal input");
               $("#success_flash_message").css('display', 'inline');
               setTimeout(function() {
                   $("#success_flash_message").css('display', 'none');
               }, 3000);
               $("#main_question_id").val("");
               isFormSubmit = false;
               break;
               return;
           }
       }
    }

    var questionContent = $("#textarea_question_content").val();
    if (!questionContent.trim()) {
        isFormSubmit = false;
        $("#textarea_question_content").css('border', '1px dashed #EB340A');
        $("#question_content_error_message").css('display','inline');
    } 

    var option_one = $("#option_one_input").val();
    if (!option_one.trim()) {
        isFormSubmit = false;
        $("#option_one_input").css('border','1px dashed #EB340A');
        $("#question_one_error_message").css('display', 'inline');
    } 

    var option_two = $("#option_two_input").val();
    if (!option_two.trim()) {
        isFormSubmit = false;
        $("#option_two_input").css('border', '1px dashed #EB340A');
        $("#option_two_error_message").css('display', 'inline');
    } 

    var option_three = $("#option_three_input").val();
    if (! option_three.trim()) {
        isFormSubmit = false;
        $("#option_three_input").css('border', '1px dashed #EB340A');
        $("#option_three_error_message").css('display', 'inline');
    } 

    var option_four = $("#option_four_input").val();
    if (!option_four.trim()) {
        isFormSubmit = false;
        $("#option_four_input").css('border', '1px dashed #EB340A');
        $("#option_four_error_message").css('display', 'inline');
    } 

    var chestr = ""; 
    $("input[name='option']").each(function(index,domEle){
        if (this.checked) {
            var inputEle = $(".answer")[index]; 
            chestr = inputEle.value;
            $("#id_correct_option").val(chestr);
        }
    }); 

    if(chestr == "") { 
        $("#success_flash_message").css('display', 'inline');
        $("#success_flash_message").html("please choice an answer!");
        isFormSubmit = false;
     } 
    setTimeout(function() {
        $("#success_flash_message").css('display', 'none');
     }, 3000);

    if (questionId.length > 45 || questionContent.length > 45) {
        $("#success_flash_message").html("The input length must less than 45");
        $("#success_flash_message").css('display', 'inline');
        $("#success_flash_message").css('width', '500px');
        setTimeout(function() {
            $("#success_flash_message").css('display', 'none');
        }, 3000);
        isFormSubmit = false;
    }

    if (option_one.length > 45 || option_two.length > 45 || option_three.length > 45 || option_four.length > 45 ) {
        $("#success_flash_message").html("The input length must less than 45");
        $("#success_flash_message").css('display', 'inline');
        $("#success_flash_message").css('width', '500px');
        setTimeout(function() {
            $("#success_flash_message").css('display', 'none');
        }, 3000);
        isFormSubmit = false;
    }

    if (isFormSubmit) {
        $("#question_form").submit();
    }
} 

function checkAnswer(data) {
    var values = "";
    $(".answer").each(function(i,item){
        var value = $(this).val();
        values += value; //get all value
    });
    var val = $(data).val(); //get the input value
    var newValue = values.replace(val,""); //clean the input
    if (newValue == "") {
        return false;
    } else {
        if (newValue.indexOf(val) > -1) { // compare the input
            $("#success_flash_message").html("Don't input the same answer! ");
            $("#success_flash_message").css('display', 'inline');
            $("#success_flash_message").css('width', '250px');
            setTimeout(function() {
                $("#success_flash_message").css('display', 'none');
            }, 3000);
            isFormSubmit = false;
            $(data).val("");
        }
    }
 }

window.onload = function() { // THIS PLACE HAS A BUG ! REQEUST HAS VALUE , SO DON'T FLUSH PAGE
    var eleValue = $("#success_flash_message").text(); 
    if (eleValue) {
        setTimeout(function() {
            $("#success_flash_message").css('display', 'none');
            $("#success_flash_message").html(""); 
         }, 3000);
    }
}

