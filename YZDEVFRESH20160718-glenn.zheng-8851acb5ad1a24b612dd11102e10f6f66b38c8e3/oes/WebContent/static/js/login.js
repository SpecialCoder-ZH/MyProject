window.onload = function() {
    $("#login_wrong").css('visibility', 'hidden');
    $("#user_wrong").css('visibility', 'hidden');
    var arr = document.cookie.split(";"); 
    for(var i = 0; i < arr.length; i++) { 
        if (arr[i].split("=")[0].trim() == "userNameAndPassword".trim()) { 
            var userName = arr[i].split("=")[1].substring(1);
            var password = arr[i].split("=")[2];
            password = password.substring(0,password.length-1);
            $("#user_name").val(userName); 
            $("#password").val(password); 
        }
    }

    $('#login_button').keydown(function(e){
        if (e.keyCode == 13) {
            login();
        }
    });
}

function changepic() {
    var element = document.getElementById("re_image");
    if (element.src.match("ICN_Unselected_15x15.png")) {
        element.src="/oes/static/style/images/ICN_Selected_15x15.png";
        $("#login_remember_me").val("remember");
    } else {
        element.src="/oes/static/style/images/ICN_Unselected_15x15.png";
        $("#login_remember_me").val("unRemember");
    }
}

function login() {
    var userNameValue = $("#user_name").val();
    var isFormSubmit = true;
    if (userNameValue == null || "" == userNameValue) {
        $("#login_wrong").css('visibility', 'visible');
        $("#errorUserName").html("userName is required");
        isFormSubmit = false;
    } else {
        $("#errorUserName").html("");
        $("#login_wrong").css('visibility', 'hidden');
    }

      var passwordValue = $("#password").val();
      if (!passwordValue) {
          $("#user_wrong").css('visibility', 'visible');
          $("#errorPassword").html("password is required");
          isFormSubmit = false;
      } else {
          $("#errorPassword").html("");
          $("#user_wrong").css('visibility', 'hidden');
      }    
      if (isFormSubmit) {
          $("#loginForm").submit();
      }
  }
