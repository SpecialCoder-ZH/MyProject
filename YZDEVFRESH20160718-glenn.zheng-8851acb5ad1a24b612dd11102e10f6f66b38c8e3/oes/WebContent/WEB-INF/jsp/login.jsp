<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.augmentum.oes.util.PropertyUtil" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>login</title>
      <link rel="stylesheet" type="text/css" href="<%= PropertyUtil.getStaticUrl() %>/style/login.css" />
      <script type="text/javascript" src="<%= PropertyUtil.getStaticUrl() %>/lib/jquery-1.10.2.min.js"></script>
      <script type="text/javascript" src="<%= PropertyUtil.getStaticUrl() %>/js/login.js"></script>
      <script type="text/javascript" src="<%= PropertyUtil.getStaticUrl() %>/js/common.js"></script>
  </head>
  <body>

    <div>
      <img src="<%= PropertyUtil.getStaticUrl() %>/style/images/LOGO_Web_Login_90x180.png" id="logo_image" />
      <div id="project_name"><fmt:message key='title'/></div>
    </div>
    <div id="welcome"><fmt:message key='welcome'/></div>

    <div id="login_form">
      <form method="post" action="${pageContext.request.contextPath }/SaveLogin" id="loginForm" >

        <div>
          <label id="errorUserName" class="errorMessage" >${requestScope.errorMessage.userNameError } ${message}</label>
         <% 
            String message = (String)request.getAttribute("message"); 
            String visibility = "hidden";
            if (message != null) {
                visibility = "visible";
            }
         %>
        <label id="errorPassword" class="errorMessage" >${requestScope.errorMessage.passwordError } ${message}</label>
      </div>

      <div id="login_form_name"> 
        <div><img src="<%= PropertyUtil.getStaticUrl() %>/style/images/ICN_Usename_20x20.png" id="user_image" /></div>
        <input type="text" name="userName" id="user_name" placeholder="Username" />
        <input type="hidden" name="go" value="${go}" />
        <input type="hidden" name="queryString" id="queryString" />
        <span id="login_wrong"><img src="<%= PropertyUtil.getStaticUrl() %>/style/images/ICN_Client_Login_Wrong_20X20.png" id="img1" /></span>
      </div>
      <br/>

      <div id="login_form_password"> 
        <div><img src="<%= PropertyUtil.getStaticUrl() %>/style/images/ICN_Password_20x15.png" id="user_password" /></div>
        <input type="password" name="password" id="password" placeholder="Password" />
         <span id="user_wrong"><img src="<%= PropertyUtil.getStaticUrl() %>/style/images/ICN_Client_Login_Wrong_20X20.png" id="img2" /></span>
      </div>
      <input type="button" value="Login" onclick="login()" id="login_button" />
      <br/>
      <div id="form_checkbox"> 
        &nbsp;
        <img id="re_image" src="<%= PropertyUtil.getStaticUrl() %>/style/images/ICN_Unselected_15x15.png" onclick="changepic()" />
        <input type="hidden" name="rememberMe" id="login_remember_me" />
        <span id="remember_me"><fmt:message key='remember.me'/></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <span ><a href="" id="forget_password"><fmt:message key='forget.password'/></a></span>
      </div>
    </form>

  </div>
    <div id="footer">
       <hr/>
       Copyright &copy; 2016 Augmentum,Inc. All Right Reserved.
    </div>
  </body>
</html>
<script>
    var querystring = location.hash;
    document.getElementById("queryString").value = querystring;
</script>