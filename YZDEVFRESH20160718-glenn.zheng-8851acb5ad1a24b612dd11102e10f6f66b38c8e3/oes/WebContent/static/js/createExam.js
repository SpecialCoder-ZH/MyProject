function createExamSubmit() {
    var isFormSubmit = true;
    var quantity = $('#exam_question_quantity').val()
    var points = $("#text_question_points").val();
    var passCriteria = $("#text_pass_criteria").val();
    $("form :input").each(function(index, domEle) {
        if (!$(this).val()) {
            isFormSubmit = false;
            $(this).css('border', '1px dashed #EB340A');
            $(this).next(".error_message").css('display','inline');
        }
    });

    var examId = $("#exam_id").val();
    if (examId) {
         var startChar = examId.substring(0,1);
         if ( startChar != 'E' ) {
             $("#exam_id").next(".error_message").css('display','none');
             $("#success_flash_message").html("The Question ID must startwith 'E' ");
             $("#success_flash_message").css('display', 'inline');
             $("#success_flash_message").css('width', '500px');
             $("#exam_id").val("");
             setTimeout(function() {
                 $("#success_flash_message").css('display', 'none');
             }, 3000);
             isFormSubmit = false;
             return;
         }
    }

    var requestURI = "/oes/getQuestionCount";
    if (quantity) {
        var examObject = {
                questionQuantity: quantity
        };
        var jsonString = JSON.stringify(examObject);
        jQuery.ajax({
            type: 'POST',
            contentType: 'application/json; charset=utf-8',
            data: jsonString,
            url: requestURI,
            success: function(data){
                if (!data.msg) {
                    if (parseInt(quantity) > parseInt(data)) {
                        $("#tip_message_delete_exam").css('display', 'inline');
                        isFormSubmit = false;
                    }
                }
            }
        });
    }
    if (isFormSubmit) {
        $("#exam_form").submit();
    }
}

function caclScore() {
    if ($("#exam_question_points").val() && $("#exam_question_quantity").val()) {
            var fullScore = parseInt($("#exam_question_points").val()) * parseInt($("#exam_question_quantity").val());
            $("#exam_total_score").val(fullScore);
    }

    if ($("#exam_question_points").val()) {
        $("#exam_question_points").css('border', '1px solid #9D9D9D');
        $("#exam_question_points").next(".error_message").css('display','none');
    }

    if ($("#exam_question_quantity").val()) {
        $("#exam_question_quantity").css('border', '1px solid #9D9D9D');
        $("#exam_question_quantity").next(".error_message").css('display','none');
    }
}

$(document).ready(function() {
    setTimeout(function() {
        $("#success_flash_message").css('display', 'none');
    }, 0);

    $("#delete_img_span").click(function(){
        $("#tip_message_delete_exam").css('display', 'none');
    });

    $("#exam_question_quantity").change(function(){
        var quantity = $('#exam_question_quantity').val();
        if (!isInteger(quantity)) {
            $("#success_flash_message").html("Please input correct num ");
            $("#success_flash_message").css('display', 'inline');
            $("#success_flash_message").css('width', '300px');
            $('#exam_question_quantity').val("");
            setTimeout(function() {
                $("#success_flash_message").css('display', 'none');
            }, 3000);
            return;
        }
    });

    $("#exam_effective_hour").change(function() {
        var hour = $("#exam_effective_hour").val();
        if (!isInteger(hour)) {
            $("#success_flash_message").html("Please input correct num ");
            $("#success_flash_message").css('display', 'inline');
            $("#success_flash_message").css('width', '300px');
            $('#exam_effective_hour').val("");
            setTimeout(function() {
                $("#success_flash_message").css('display', 'none');
            }, 3000);
        } else {
            if (parseInt(hour) > 24 || parseInt(hour) < 0) {
                $("#success_flash_message").html("Please input hour between 0 and 24 ");
                $("#success_flash_message").css('display', 'inline');
                $("#success_flash_message").css('width', '500px');
                $('#exam_effective_hour').val("");
                setTimeout(function() {
                    $("#success_flash_message").css('display', 'none');
                }, 3000);
            }
        }
    });

    $("#exam_effective_min").change(function() {
        var min = $("#exam_effective_min").val();
        if (!isInteger(min)) {
            $("#success_flash_message").html("Please input correct num ");
            $("#success_flash_message").css('display', 'inline');
            $("#success_flash_message").css('width', '300px');
            $('#exam_effective_min').val("");
            setTimeout(function() {
                $("#success_flash_message").css('display', 'none');
            }, 3000);
        } else {
            if (parseInt(min) > 60 || parseInt(min) < 0) {
                $("#success_flash_message").html("Please input hour between 0 and 60 ");
                $("#success_flash_message").css('display', 'inline');
                $("#success_flash_message").css('width', '500px');
                $('#exam_effective_min').val("");
                setTimeout(function() {
                    $("#success_flash_message").css('display', 'none');
                }, 3000);
            }
        }
    });

    $("#exam_question_points").change(function() {
        var points = $("#exam_question_points").val();
        if (!isInteger(points)) {
            $("#success_flash_message").html("Please input correct num ");
            $("#success_flash_message").css('display', 'inline');
            $("#success_flash_message").css('width', '300px');
            $('#exam_question_points').val("");
            setTimeout(function() {
                $("#success_flash_message").css('display', 'none');
            }, 3000);
        } else {
             if (parseInt(points) <= 0) {
                 $("#success_flash_message").html("Question Points must greater than 0 ");
                 $("#success_flash_message").css('display', 'inline');
                 $("#success_flash_message").css('width', '300px');
                 $('#exam_question_points').val("");
                 setTimeout(function() {
                     $("#success_flash_message").css('display', 'none');
                 }, 3000);
             }
        }
    });

    $("#exam_pass_criteria").change(function() {
        var passCriteria = $("#exam_pass_criteria").val();
        if (!isInteger(passCriteria)) {
            $("#success_flash_message").html("Please input correct num ");
            $("#success_flash_message").css('display', 'inline');
            $("#success_flash_message").css('width', '300px');
            $('#exam_pass_criteria').val("");
            setTimeout(function() {
                $("#success_flash_message").css('display', 'none');
            }, 3000);
        }
    });
});

function editExamSubmit() {
    var isFormSubmit = true;
    var quantity = $('#exam_question_quantity').val()
    var points = $("#text_question_points").val();
    var passCriteria = $("#text_pass_criteria").val();
    $("form :input[type!='hidden']").each(function(index, domEle){
        if(!$(this).val()){
            isFormSubmit = false;
            $(this).css('border', '1px dashed #EB340A');
            $(this).next(".error_message").css('display','inline');
        }
    });

    var examId = $("#exam_id").val();
    if (examId) {
         var startChar = examId.substring(0,1);
         if ( startChar != 'E' ) {
             $("#exam_id").next(".error_message").css('display','none');
             $("#success_flash_message").html("The Question ID must startwith 'E' ");
             $("#success_flash_message").css('display', 'inline');
             $("#success_flash_message").css('width', '500px');
             $("#exam_id").val("");
             setTimeout(function() {
                 $("#success_flash_message").css('display', 'none');
             }, 3000);
             isFormSubmit = false;
             return;
         }
    }

    var requestURI = "/oes/getQuestionCount";
    if (quantity) {
        var examObject = {
                questionQuantity: quantity
        };
        var jsonString = JSON.stringify(examObject);
        jQuery.ajax({
            type: 'POST',
            contentType: 'application/json; charset=utf-8',
            data: jsonString,
            url: requestURI,
            success: function(data){
                if (!data.msg) {
                    if (parseInt(quantity) > parseInt(data)) {
                        $("#tip_message_delete_exam").css('display', 'inline');
                        var isFormSubmit = false;
                    }
                }
            }
        });
    }
    if (isFormSubmit) {
        $("#exam_edit_form").submit();
    }
}

$(document).ready(function() {
    $("form :input").each(function(index, domEle) {
        $(domEle).change(function(){
            if ($(this).val()) {
                isFormSubmit = false;
                $(this).css('border', '1px solid #9D9D9D');
                $(this).next(".error_message").css('display','none');
            }
        });
    });
});

function changeStyle(data) {
    if ($(data).val()) {
         $(data).css('border', '1px solid #9D9D9D');
         $(data).next(".error_message").css('display','none');
    }
}