$(document).ready(function(){
    $("option").each(function(index, domEle){
        if ($(domEle).attr("checked")) {
            if (index == 0) {
                $("#option_one_input_detail").css("backgroundColor","#D2DAE3");
            } else if (index == 1) {
                $("#option_two_input_detail").css("backgroundColor","#D2DAE3");
            } else if (index == 2) {
                $("#option_three_input_detail").css("backgroundColor","#D2DAE3");
            } else {
                $("#option_four_input_detail").css("backgroundColor","#D2DAE3");
            }
        }
    })
});

$(document).ready(function(){
    $("input[name='option']").each(function(index, domEle){
        if (this.checked) {
            if (index == 0) {
                $("#option_one_input").css('background-color','#D2DAE3');
            } else if (index == 1) {
                $("#option_two_input").css('background-color','#D2DAE3');
            } else if (index == 2) {
                $("#option_three_input").css('background-color','#D2DAE3');
            } else {
                $("#option_four_input").css('background-color','#D2DAE3');
            }
        }
    })
});

function editQuestionSubmit() {
    var id = $("#id_id_detail").val();
    location.href = "/oes/EditQuestion?id="+id;
}
function deleteQuestionSubmit() {
    var ids = $("#display_id_detail").val();
    location.href = "/oes/DeleteQuestion?ids="+ids;
}