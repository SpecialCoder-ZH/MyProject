function serchByKeys() {
    var keywords = $("#input_keywords").val();
    var pagesize = document.getElementById("page_size_").value.trim();
    keywords = encodeURI(keywords);
    location.href="/oes/QuestionManager?forwardId=searchByKeyWord&pagesize="+pagesize+"&keywords="+keywords;
}

function changeIncrese() {
    var element = document.getElementById("decrese");
    var pagesize = $("#page_size_").val();
    var keywords = $("#id_keyword").val();
    var pagenum = $("#page_current_num").val();
    element.src = "/oes/static/style/images/ICN_Increase_10x15.png";
    location.href = "/oes/QuestionManager?pagenum="+pagenum+"&keywords="+keywords+"&forwardId=decrese&pagesize="+pagesize;
}

function changeDecrese() {
    var element = document.getElementById("decrese");
    var pagesize = $("#page_size_").val();
    var keywords = $("#id_keyword").val();
    var pagenum = $("#page_current_num").val();
    element.src = "/oes/static/style/images/ICN_Decrese_10x15.png";
    location.href = "/oes/QuestionManager?pagenum="+pagenum+"&keywords="+keywords+"&forwardId=1&pagesize="+pagesize;
}

function changePerPage() {
    var str = document.getElementsByName("page_option"); 
    var keywords = $("#id_keyword").val();
    var objarray=str.length; 
    var size = $("select option:selected").html();
    location.href="/oes/QuestionManager?forwardId=1&pagesize="+size+"&keywords="+keywords;
}

function deleteOperation() {
    var question_checkId = document.getElementsByName("q_list_display_id");
    var strCheck = "";
    var flag = true;
    $('input:checkbox[name=checkbox_option]').each(function(index, domEle){
        if (this.checked) {
            strCheck += question_checkId[index].value;
            strCheck += ",";
            flag = false;
        }
    });

    if (flag) {
        $("#error_flash_message").css('display', 'inline');
        $("#error_flash_message").html("please make your choice!");
        setTimeout(function(){
            $("#error_flash_message").css('display', 'none');
         },3000);
        return;
    } else {
        $("#tip_message_delete").css('display', 'inline');
  }
}

function deleteAllQuestion () {
    var dele_but = document.getElementById("form_check_img_id");
    if (dele_but.src.match("ICN_Unselected_15x15.png")) {
        dele_but.src="/oes/static/style/images/ICN_Selected_15x15.png";
        var str = document.getElementsByName("checkbox_option");
        for (var i = 0; i < str.length; i++) {
            str[i].checked = true;
        }
    } else {
        dele_but.src="/oes/static/style/images/ICN_Unselected_15x15.png";
        var str = document.getElementsByName("checkbox_option");
        for (var i = 0; i<str.length; i++) {
            str[i].checked = false;
        }
    }
    
}

function deleteQuestion() {
    $("#tip_message_delete").css('display', 'none');
}

function yesButtonOk() {
    var str = document.getElementsByName("page_option"); 
    var keywords = $("#id_keyword").val();
    var objarray=str.length; 
    var size;
    for (var i = 0; i < objarray; i++) {
        if (str[i].selected == true) { 
            size = str[i].innerHTML; 
        } 
    }

    var str = document.getElementsByName("checkbox_option");
    var question_checkId = document.getElementsByName("q_list_display_id");
    var pagenum = $("#page_current_num").val();
    var strCheck = "\'";
    var strlength = str.length;
    for (var i = 0; i<strlength; i++) {
        if (str[i].checked==true) {
            strCheck +=  question_checkId[i].value;
            strCheck += "','";
        }
    }
   window.location.href = "/oes/DeleteQuestion?pagesize="+size+"&pagenum="+pagenum+"&keywords="+keywords+"&ids="+strCheck;
}

function noButtonNo () {
    $("#tip_message_delete").css('display', 'none');
}

function changStatus() {
    var str = document.getElementsByName("checkbox_option");
    var flag = true;
    for (var i = 0; i<str.length; i++) {
        if(str[i].checked != true) {
            flag = false;
            var dele_but = document.getElementById("form_check_img_id");
            dele_but.src = "/oes/static/style/images/ICN_Unselected_15x15.png";
        } 
    }
    if (flag) {
        document.getElementById("form_check_img_id").src = "/oes/static/style/images/ICN_Selected_15x15.png"
    }
}

window.onload = function() {
    var totalPage = $("#totalPage").val();
    if (totalPage == 0) {
        document.getElementById("form_footer").style.display = "none";
        var ele = document.getElementById("error_flash_message");
        ele.style.display="inline";
        ele.innerHTML = "No Records!";
        setTimeout(function(){
            $("#error_flash_message").css('display', 'none');
         },3000);
        return;
    }
    var eleValue = $("#success_flash_message_list").text(); 
    if (eleValue) {
        setTimeout(function() {
            $("#success_flash_message_list").css('display', 'none');
            $("#success_flash_message_list").html(""); 
         }, 3000);
    }
    var deleteMessage = $("#success_delete").html();
    if (deleteMessage) {
        setTimeout(function(){
            $("#success_delete").css('display', 'none');
         },3000);
        return;
    }
}
