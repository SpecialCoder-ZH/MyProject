<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.augmentum.oes.util.PropertyUtil" %>
<%@ taglib uri="/WEB-INF/block.tld" prefix="block" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     <title><fmt:message key='edit.question'/></title>
     <link rel="stylesheet" type="text/css" href="<%= PropertyUtil.getStaticUrl() %>/style/header.css" />
     <link rel="stylesheet" type="text/css" href="<%= PropertyUtil.getStaticUrl() %>/style/createquestion.css" />
      <script type="text/javascript" src="<%= PropertyUtil.getStaticUrl() %>/lib/jquery-1.10.2.min.js"></script>
      <script type="text/javascript" src="<%= PropertyUtil.getStaticUrl() %>/lib/fckeditor/fckeditor/fckeditor.js" charset="utf-8"></script>
      <script type="text/javascript" src="<%= PropertyUtil.getStaticUrl() %>/js/createQuestion.js"></script>
      <script type="text/javascript" src="<%= PropertyUtil.getStaticUrl() %>/js/editQuestion.js"></script>
  </head>
<body>
  <block:display name="headerBlock"/>
  <div id="main">
    <div id="navigation_title">
      <a href="${pageContext.request.contextPath}/QuestionManager?forwardId=1" id="navigation_title_question_manager"><fmt:message key='question.manager'/></a>&nbsp;&nbsp;&gt;&nbsp;&nbsp;
      <a href="${pageContext.request.contextPath}/QuestionManager?forwardId=1&pagenum=${pagenum}&pagesize=${page.pagesize}&keywords=${keywords}" id="navigation_title_question_manager"><fmt:message key='question.list'/></a>&nbsp;&nbsp;&gt;&nbsp;&nbsp;
       ${question.displayId}
      </div>
    <div id="wight_area">
    <div id="success_flash_message" ${SUCCESS_EDIT_FLASH_MESSAGE==null ?"style=display:none":"style=display:inline" }>${ SUCCESS_EDIT_FLASH_MESSAGE}</div>
    <form id="question_form" action="${pageContext.request.contextPath}/EditQuestion?pagenum=${pagenum}&forwardId=questionList&id=${question.id}" method="POST">
        <div id="wight_area_question_id">Question&nbsp;&nbsp;ID:</div>
        <input type="text" id="main_question_id" name="displayId" readonly value="${question.displayId }"/><span class="error_message" id="display_id__error_message">Question Id can't be null</span>
        <div id="wight_area_question_content">Question:</div>
        <%-- <div id="fckeditor">
          <textarea id="textarea_question_content" rows="5" cols="7" name="questionContent" style="resize:none;" >${question.questionContent}</textarea><span class="error_message" id="question_content__error_message">Question content can't be null</span>
        </div> --%>
          <textarea id="textarea_question_content" rows="5" cols="7" name="questionContent" style="resize:none;" >${question.questionContent}</textarea><span class="error_message" id="question_content__error_message">Question content can't be null</span>
            <div id="wight_area_answer">Answer:</div>
            <div id="optionA">
              <!-- <img src="<%= PropertyUtil.getStaticUrl() %>/style/images/BTN_Radio_Unselected_16x16.png" "/>&nbsp;A -->
              <input type="radio" name="option"  value="A" id="img_option_a" ${question.correctOption == "A" ? "checked = 'checked' " : ""} onclick="changeBgcolor()" /><label for="img_option_a">&nbsp;A</label> 
              <input type="text" class="question_answer" name="optionOne" id="option_one_input" placeholder="please input the answer" value="${question.optionOne }"/>
              <span class="error_message" id="option_one__error_message">Option one can't be null</span>
            </div>
            <div id="optionB">
              <!-- <img src="<%= PropertyUtil.getStaticUrl() %>/style/images/BTN_Radio_Unselected_16x16.png" id="img_option_b"/>&nbsp;B -->
              <input type="radio" name="option"  value="B" id="img_option_b" ${question.correctOption=="B"?"checked='checked'":""} onclick="changeBgcolor()" /><label for="img_option_b">&nbsp;B</label>
              <input type="text" class="question_answer" name="optionTwo" id="option_two_input" placeholder="please input the answer" value="${question.optionTwo }" />
              <span class="error_message" id="option_two__error_message">Option two can't be null</span>
            </div>
            <div id="optionC">
              <!-- <img src="<%= PropertyUtil.getStaticUrl() %>/style/images/BTN_Radio_Unselected_16x16.png" id="img_option_c"/>&nbsp;C checked="checked" -->
              <input type="radio" name="option"  value="C" id="img_option_c" ${question.correctOption=="C"?"checked='checked'":""} onclick="changeBgcolor()" /><label for="img_option_c">&nbsp;C</label> 
              <input type="text" class="question_answer" name="optionThree" id="option_three_input" placeholder="please input the answer" value="${question.optionThree }" />
              <span class="error_message" id="option_three__error_message">Option three can't be null</span>
            </div>
            <div id="optionD">
              <!-- <img src="<%= PropertyUtil.getStaticUrl() %>/style/images/BTN_Radio_Unselected_16x16.png" id="img_option_d"/>&nbsp;D -->
              <input type="radio" name="option" value="D" id="img_option_d" ${question.correctOption=="D"?"checked='checked'":""} onclick="changeBgcolor()" /><label for="img_option_d">&nbsp;D</label> 
              <input type="text" class="question_answer" name="optionFour" id="option_four_input" placeholder="please input the answer" value="${question.optionFour }" />
              <span class="error_message" id="option_four__error_message">Option four can't be null</span>
            </div>
            <div ><input id="question_create" type="button" value="Save" onclick="editQuesSubmit()" /></div>
            <div ><input id="question_cancle"  type="reset" value="Cancle" /></div>
                  <input type="hidden" name="correctOption" id="id_correct_option" />
          </form>
        </div>
    </div>
    <div id="q_foot_create">Copyright &copy; 2016 Augmentum,Inc. All Right Reserved.</div>
</body>
</html>