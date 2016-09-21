function serchByKeys() {
    var keywords = $("#exam_input_keywords").val();
    var pagesize = document.getElementById("page_size_").value.trim();
    keywords = encodeURI(keywords);
    location.href = "/oes/examList?&pagesize="+pagesize+"&keywords="+keywords;
}

function changeIncrese() {
    var element = document.getElementById("id_increse");
    var pagesize = $("#page_size_").val();
    var keywords = $("#id_keyword").val();
    var pagenum = $("#page_current_num").val();
    element.src = "/oes/static/style/images/ICN_Increase_10x15.png";
    location.href = "/oes/examList?pagenum="+pagenum+"&keywords="+keywords+"&direction=DESC&pagesize="+pagesize;
}

function changeDecrese() {
    var element = document.getElementById("id_decrese");
    var pagesize = $("#page_size_").val();
    var keywords = $("#id_keyword").val();
    var pagenum = $("#page_current_num").val();
    element.src = "/oes/static/style/images/ICN_Decrese_10x15.png";
    location.href = "/oes/examList?direction=ASC&pagenum="+pagenum+"&keywords="+keywords+"&pagesize="+pagesize;
}

function changeNameIncrese() {
    var element = document.getElementById("name_increse");
    var pagesize = $("#page_size_").val();
    var keywords = $("#id_keyword").val();
    var pagenum = $("#page_current_num").val();
    element.src = "/oes/static/style/images/ICN_Increase_10x15.png";
    location.href = "/oes/examList?nameOrder=DESC&pagenum="+pagenum+"&keywords="+keywords+"&pagesize="+pagesize;
}

function changeNameDecrese() {
    var element = document.getElementById("name_decrese");
    var pagesize = $("#page_size_").val();
    var keywords = $("#id_keyword").val();
    var pagenum = $("#page_current_num").val();
    element.src = "/oes/static/style/images/ICN_Decrese_10x15.png";
    location.href = "/oes/examList?nameOrder=ASC&pagenum="+pagenum+"&keywords="+keywords+"&pagesize="+pagesize;
}

function changeEffTimeIncrese() {
    var element = document.getElementById("time_increse");
    var pagesize = $("#page_size_").val();
    var keywords = $("#id_keyword").val();
    var pagenum = $("#page_current_num").val();
    element.src = "/oes/static/style/images/ICN_Increase_10x15.png";
    location.href = "/oes/examList?timeOrder=DESC&pagenum="+pagenum+"&keywords="+keywords+"&pagesize="+pagesize;
}

function changeEffTimeDecrese() {
    var element = document.getElementById("time_decrese");
    var pagesize = $("#page_size_").val();
    var keywords = $("#id_keyword").val();
    var pagenum = $("#page_current_num").val();
    element.src = "/oes/static/style/images/ICN_Decrese_10x15.png";
    location.href = "/oes/examList?timeOrder=ASC&pagenum="+pagenum+"&keywords="+keywords+"&pagesize="+pagesize;
}

function jump(){
    var num = document.getElementById("input_num").value;
    var reg = /^[-]{0,1}[0-9]{1,}$/;
    if(!reg.test(num)){
        document.getElementById("input_num").style.border = "1px dashed #EB340A";
        var ele = document.getElementById("error_flash_message");
        ele.style.display="inline";
        ele.innerHTML = "please input correct num!";
        setTimeout(function(){
            document.getElementById("error_flash_message").style.display = "none";
        },3000);
        document.getElementById("input_num").value = "";
        return;
        }
    if (num < 1) {
      num=1;
    }
    var total_page = document.getElementById("page_total_id").value;
    if (parseInt(num) > parseInt(total_page)) {
        num = total_page;
    } 
        window.location.href="${pageContext.request.contextPath}/examList?pagesize=${pageSize}&keywords=${keywords}&direction=${direction}&timeOrder=${timeOrder}&nameOrder=${nameOrder}&pagenum="+num;
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
        for (var i = 0; i<str.length; i++) {
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
    var nameOrder = $("#id_nameOrder").val();
    var timeOrder = $("#id_timeOrder").val();
    var direction = $("#id_direction").val();
    var objarray=str.length; 
    var size;
    for (var i=0;i<objarray;i++) {
        if(str[i].selected == true) { 
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
   window.location.href = "/oes/deleteExam?pagesize="+size+"&pagenum="+pagenum+"&keywords="+keywords+"&ids="+strCheck;
}

function noButtonNo () {
    $("#tip_message_delete").css('display', 'none');
    $(":checkbox").each(function(){
        $(this).attr("checked",false);
    });
    var deleteButton = document.getElementById("form_check_img_id");
    deleteButton.src="/oes/static/style/images/ICN_Unselected_15x15.png";
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
}

function changePerPage() {
    var str = document.getElementsByName("page_option"); 
    var keywords = $("#id_keyword").val();
    var nameOrder = $("#id_nameOrder").val();
    var timeOrder = $("#id_timeOrder").val();
    var direction = $("#id_direction").val();
    var objarray=str.length; 
    var pagesize = $("select option:selected").html();
    location.href = "/oes/examList?pagesize="+pagesize+"&keywords="+keywords+"&direction="+direction+"&timeOrder="+timeOrder+"&nameOrder="+nameOrder
}

$(document).ready(function(){
    $('#date_button').click(function(){
        var flag = true;
        var beginDate = $('#begin_date').val();
        var endDate = $('#end_date').val();
        var begin = new Date(beginDate).getTime();
        var end = new Date(endDate).getTime();
        if (begin > end) {
            flag = false;
            $("#error_flash_message").css('display', 'inline');
            $("#error_flash_message").html("the end date must after begin date!");
            setTimeout(function(){
                $("#error_flash_message").css('display', 'none');
             },3000);
            return;
        }

        if (flag) {
            var pagesize = document.getElementById("page_size_").value.trim();
            var keywords = $("#exam_input_keywords").val();
            var pagesize = document.getElementById("page_size_").value.trim();
            keywords = encodeURI(keywords);
            if (!beginDate || !endDate) {
                flag = false;
                $("#error_flash_message").css('display', 'inline');
                 $("#error_flash_message").html("please input correct date!");
                 setTimeout(function(){
                     $("#error_flash_message").css('display', 'none');
                  },3000);
                 return;
            }

            if (flag) {
                location.href = "/oes/examList?&pagesize="+pagesize+"&beginDate="+beginDate+"&endDate="+endDate+"&keywords="+keywords;
            }
    }
    });
});

$(document).ready(function(){
    $('#change_password_submit').click(function(){
        var flag = true;
        var password = $('#password').val();
        var passwordConfirm = $('#password_confirm').val();
        if (!password || !passwordConfirm) {
            flag = false;
        }

        if (password != passwordConfirm) {
            flag = false;
            $("#success_flash_message").html("Your confirmed password and new password do not match");
            $("#success_flash_message").css('display', 'inline');
            setTimeout(function() {
                $("#success_flash_message").css('display', 'none');
            }, 3000);
            $("#password").val("");
            $("#password_confirm").val("");
        }
        if (flag) {
            $('#user_form').submit();
        }
    });
});

$(document).ready(function(index, domEle){
    $(":checkbox").click(function(){
        $(':checkbox').each(function(index, domEle){
            if ($(this).checked == false) {
                $('#form_check_img_id').src = "/oes/static/style/images/ICN_Unselected_15x15.png";
            } else {
                $('#form_check_img_id').src = "/oes/static/style/images/ICN_Selected_15x15.png"
            }
        });
    });

    var deleteMessage = $("#success_delete").html();
    if (deleteMessage) {
        setTimeout(function(){
            $("#success_delete").css('display', 'none');
         },3000);
        return;
    }
});