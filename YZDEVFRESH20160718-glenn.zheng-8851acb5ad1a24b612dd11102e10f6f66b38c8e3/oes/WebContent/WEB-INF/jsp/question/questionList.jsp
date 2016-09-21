<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.augmentum.oes.util.PropertyUtil" %>
<%@ taglib uri="/WEB-INF/block.tld" prefix="block" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     <title><fmt:message key='question.list'/></title>
     <link rel="stylesheet" type="text/css" href="<%= PropertyUtil.getStaticUrl() %>/style/header.css" />
     <script type="text/javascript" src="<%= PropertyUtil.getStaticUrl() %>/lib/jquery-1.10.2.min.js"></script>
     <script type="text/javascript" src="<%= PropertyUtil.getStaticUrl() %>/js/question.js"></script>
  </head>

  <body>

    <block:display name="headerBlock"/>

    <div id="main">
      <div id="wight_area">
      <div id="error_flash_message"></div>
      <div id="question_search"> 
        <img id="search_image" src="<%= PropertyUtil.getStaticUrl() %>/style/images/ICN_Search_15x20.png" onclick="serchByKeys()" />
        <input type="text" id="input_keywords" name="keywords" placeholder="<fmt:message key='question.list.keywords'/>" value="${keywords}" />
      </div>

      <block:display name="leftMenuBlock"/>

      <div id="content">
      <div id="success_flash_message_list" ${empty sessionScope.SUCCESS_FLASH_MESSAGE ? "style=display:none" : "style=display:inline;" }>${sessionScope.SUCCESS_FLASH_MESSAGE}${questionError.tooLong}</div>
      <% 
         session.removeAttribute("SUCCESS_FLASH_MESSAGE");
      %>
     <div id="success_delete" ${SUCCESS_DELETE == null ? "style=display:none" : "style=display:inline" }>${SUCCESS_DELETE}</div>
     <% 
        session.removeAttribute("SUCCESS_DELETE");
     %>
     <div id="tip_message_delete" >
        <span id="delete_img_span"><img id="delete_img_span_id" src="<%= PropertyUtil.getStaticUrl() %>/style/images/BTN_Close_16x16.png" onclick="deleteQuestion()" /></span>
        <span id="tip_message_span" ><fmt:message key='delete.confirm'/></span>
        <span><input type="button" id="span_yes_button" value="<fmt:message key='yes'/>" onclick="yesButtonOk()"/></span>
        <span><input type="button" id="span_no_button" value="<fmt:message key='no'/>" onclick="noButtonNo()" /></span>
     </div>

      <block:display name="questionListBlock"/>

      </div>
      </div>
      <div id="q_foot">Copyright &copy; 2016 Augmentum,Inc. All Right Reserved.</div>
    </div>
  </body>
</html>
