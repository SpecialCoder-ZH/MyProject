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
    <title><fmt:message key='create.question'/></title>
    <link rel="stylesheet" type="text/css" href="<%= PropertyUtil.getStaticUrl() %>/style/header.css" />
    <link rel="stylesheet" type="text/css" href="<%= PropertyUtil.getStaticUrl() %>/style/createquestion.css" />
     <script type="text/javascript" src="<%= PropertyUtil.getStaticUrl() %>/lib/jquery-1.10.2.min.js"></script>
     <script type="text/javascript" src="<%= PropertyUtil.getStaticUrl() %>/js/createQuestion.js"></script>
     <script type="text/javascript" src="<%= PropertyUtil.getStaticUrl() %>/js/common.js"></script>
  </head>
<body>
  <block:display name="headerBlock"/>

  <div id="main">
    <div id="navigation_title"><a href="${pageContext.request.contextPath}/QuestionManager?forwardId=1" id="navigation_title_question_manager"><fmt:message key='question.manager'/></a>&nbsp;&nbsp;&gt;&nbsp;&nbsp;<fmt:message key='create.question'/></div>
    <div id="wight_area">

    <div id="success_flash_message"  ${empty sessionScope.SUCCESS_FLASH_MESSAGE ? "style=display:none" : "style=display:inline;" }>${sessionScope.SUCCESS_FLASH_MESSAGE}${questionError.tooLong}</div>
    <% 
        session.removeAttribute("SUCCESS_FLASH_MESSAGE");
    %>
    <form id="question_form" action="${pageContext.request.contextPath}/CreateQuestion" method="POST">
      <div id="wight_area_question_id"><fmt:message key='create.question.question.id'/></div>
      <input type="text" id="main_question_id" name="displayId"/><span class="error_message" id="display_id_error_message">Question Id can't be null ${questionError.displayId }</span>
      <div id="wight_area_question_content"><fmt:message key='create.question.question.question.name'/></div>
      <textarea id="textarea_question_content" rows="5" cols="7" name="questionContent" style="resize:none;"></textarea><span class="error_message" id="question_content_error_message">Question content can't be null ${questionError.questionContent }</span>
      <div id="wight_area_answer"><fmt:message key='create.question.question.answer'/></div>
      <div id="optionA">
        <!-- <img src="<%= PropertyUtil.getStaticUrl() %>/style/images/BTN_Radio_Unselected_16x16.png" "/>&nbsp;A -->
        <input type="radio" name="option"  value="A" id="img_option_a" /><label for="img_option_a">&nbsp;A</label> 
        <input type="text" name="optionOne" class="answer" id="option_one_input" placeholder="<fmt:message key='create.question.question.tip'/>" onchange="checkAnswer(this);" />
        <span class="error_message" id="question_one_error_message">Option one can't be null ${questionError.optionOne }</span>
      </div>
      <div id="optionB">
        <!-- <img src="<%= PropertyUtil.getStaticUrl() %>/style/images/BTN_Radio_Unselected_16x16.png" id="img_option_b"/>&nbsp;B -->
        <input type="radio" name="option"  value="B" id="img_option_b" /><label for="img_option_b">&nbsp;B</label>
        <input type="text" name="optionTwo" class="answer" id="option_two_input" placeholder="<fmt:message key='create.question.question.tip'/>" onchange="checkAnswer(this);" />
          <span class="error_message" id="option_two_error_message">Option two can't be null ${questionError.optionTwo }</span>
      </div>
      <div id="optionC">
        <!-- <img src="<%= PropertyUtil.getStaticUrl() %>/style/images/BTN_Radio_Unselected_16x16.png" id="img_option_c"/>&nbsp;C checked="checked" -->
        <input type="radio" name="option"  value="C" id="img_option_c" /><label for="img_option_c">&nbsp;C</label> 
        <input type="text" name="optionThree" class="answer" id="option_three_input" placeholder="<fmt:message key='create.question.question.tip'/>" onchange="checkAnswer(this);" />
        <span class="error_message" id="option_three_error_message">Option three can't be null ${questionError.optionThree }</span>
      </div>
      <div id="optionD">
        <!-- <img src="<%= PropertyUtil.getStaticUrl() %>/style/images/BTN_Radio_Unselected_16x16.png" id="img_option_d"/>&nbsp;D -->
        <input type="radio" name="option" value="D" id="img_option_d" /><label for="img_option_d">&nbsp;D</label> 
        <input type="text" name="optionFour" class="answer" id="option_four_input" placeholder="<fmt:message key='create.question.question.tip'/>" onchange="checkAnswer(this);" />
        <span class="error_message" id="option_four_error_message">Option four can't be null ${questionError.optionFour }</span>
      </div>
      <div >
        <input  id="question_edit" type="button" value="Create" onclick="createQuestionSubmit()" /></div>
      <div >
        <input id="question_cancle"  type="reset" value="Cancle" />
      </div>
        <input type="hidden" name="correctOption" id="id_correct_option" />
      </form>
    </div>
  </div>
  <div id="q_foot_create">Copyright &copy; 2016 Augmentum,Inc. All Right Reserved.</div>
</body>
</html>