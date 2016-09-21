<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.augmentum.oes.util.PropertyUtil" %>
<%@ taglib uri="/WEB-INF/block.tld" prefix="block" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Create &nbsp;Question</title>
    <link rel="stylesheet" type="text/css" href="<%= PropertyUtil.getStaticUrl() %>/style/header.css" />
    <link rel="stylesheet" type="text/css" href="<%= PropertyUtil.getStaticUrl() %>/style/createquestion.css" />
    <script type="text/javascript" src="<%= PropertyUtil.getStaticUrl() %>/lib/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="<%= PropertyUtil.getStaticUrl() %>/js/createQuestion.js"></script>
    <script type="text/javascript" src="<%= PropertyUtil.getStaticUrl() %>/js/editQuestion.js"></script>
    <script type="text/javascript" src="<%= PropertyUtil.getStaticUrl() %>/js/detailQuestion.js"></script>
  </head>
<body>
  <block:display name="headerBlock"/>
  <div id="main">
    <div id="Navigation_title">
      <a href="${pageContext.request.contextPath}/QuestionManager?forwardId=1" id="Navigation_title_question_manager">Question Management</a>&nbsp;&nbsp;&gt;&nbsp;&nbsp;
      <a href="${pageContext.request.contextPath}/QuestionManager?forwardId=1" id="Navigation_title_question_manager">Question&nbsp;List</a>&nbsp;&nbsp;&gt;&nbsp;&nbsp;
       ${question.display_id}
      </div>
    <div id="wight_area">
    <form id="question_form" action="${pageContext.request.contextPath}/EditQuestion?pagenum=${pagenum}&forwardId=questionList&id=${question.id}" method="POST" >
      <div id="wight_area_question_id">Question&nbsp;&nbsp;ID:</div>
      <div id="main_question_id_detail" name="display_id" style="border:none;" >${question.display_id }</div><span class="error_message" id="display_id__error_message">Question Id can't be null</span>
      <input type="hidden" value="${question.displayId }" id="display_id_detail" />
      <input type="hidden" value="${question.id }" id="id_id_detail" />
      <div id="wight_area_question_content_detail">Question:</div>
      <div id="textarea_question_content_detail"  name="question_content" style="border:none;" >${question.questionContent}</div><span class="error_message" id="question_content__error_message">Question content can't be null</span>
      <div id="wight_area_answer_detail">Answer:</div>
      <div id="optionA_detail">
        <input type="radio" name="option"  value="A" id="img_option_a" ${question.correctOption=="A"?"checked='checked'":""} style="display:None" /><label for="img_option_a" id="img_option_a_detail" >&nbsp;A</label> 
        <div name = "optionOne" id="option_one_input_detail" placeholder="please input the answer" >${question.optionOne }</div>
        <span class="error_message" id="option_one__error_message">Option one can't be null</span>
      </div>
      <div id="optionB_detail">
        <input type="radio" name="option"  value="B" id="img_option_b" ${question.correctOption=="B"?"checked='checked'":""} style="display:None" /><label for="img_option_b" id="img_option_b_detail" >&nbsp;B</label>
        <div name="optionTwo" id="option_two_input_detail" placeholder="please input the answer" >${question.optionTwo }</div>
        <span class="error_message" id="option_two__error_message">Option two can't be null</span>
      </div>
      <div id="optionC_detail">
        <input type="radio" name="option"  value="C" id="img_option_c" ${question.correctOption=="C"?"checked='checked'":""} style="display:None"  /><label for="img_option_c" id="img_option_c_detail" >&nbsp;C</label> 
        <div name="optionThree" id="option_three_input_detail" placeholder="please input the answer" >${question.optionThree }</div>
        <span class="error_message" id="option_three__error_message">Option three can't be null</span>
     </div>
     <div id="optionD_detail">
       <input type="radio" name="option" value="D" id="img_option_d" ${question.correctOption=="D"?"checked='checked'":""} style="display:None" /><label for="img_option_d" id="img_option_d_detail" >&nbsp;D</label> 
       <div name="optionFour" id="option_four_input_detail" placeholder="please input the answer" >${question.optionFour }</div>
       <span class="error_message" id="option_four__error_message">Option four can't be null</span>
     </div>
     <div ><input  id="question_create" type="button" value="Edit" onclick="editQuestionSubmit()" /></div>
     <div ><input id="question_cancle"  type="reset" value="Delete" onclick="deleteQuestionSubmit()" /></div>
     <input type="hidden" name="correctOption" id="id_correct_option" />
  </form>
  </div>
  </div>
  <div id="q_foot_create">Copyright &copy; 2016 Augmentum,Inc. All Right Reserved.</div>
</body>
</html>