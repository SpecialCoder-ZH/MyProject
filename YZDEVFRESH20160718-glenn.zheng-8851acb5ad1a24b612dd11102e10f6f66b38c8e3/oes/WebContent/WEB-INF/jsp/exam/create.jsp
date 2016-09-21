<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.augmentum.oes.util.PropertyUtil" %>
<%@ page import="java.util.Date" %>
<%@ taglib uri="/WEB-INF/block.tld" prefix="block" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     <title><fmt:message key='create.exam'/></title>
     <link rel="stylesheet" type="text/css" href="<%= PropertyUtil.getStaticUrl() %>/style/header.css" />
     <link rel="stylesheet" type="text/css" href="<%= PropertyUtil.getStaticUrl() %>/style/exam.css" />
     <script type="text/javascript" src="<%= PropertyUtil.getStaticUrl() %>/lib/jquery-1.10.2.min.js"></script>
     <script type="text/javascript" src="<%= PropertyUtil.getStaticUrl() %>/js/exam.js"></script>
     <script type="text/javascript" src="<%= PropertyUtil.getStaticUrl() %>/js/createExam.js"></script>
     <script type="text/javascript" src="<%= PropertyUtil.getStaticUrl() %>/js/common.js"></script>
  </head>

  <body>
    <div id="header">
      <img src="<%= PropertyUtil.getStaticUrl() %>/style/images/LOGO_Web_40x240.png"  id="logo" />
      <div id="project_name"><fmt:message key='title'/></div>
      <div id="language">
        <c:choose>
        <c:when test="${sessionScope.langType == 'english' }">
          <a id="a_language" href="${pageContext.request.contextPath }/changeLangType?langType=chinese">中文</a>
        </c:when>
        <c:otherwise>
          <a id="a_language" href="${pageContext.request.contextPath }/changeLangType?langType=english">English</a>
        </c:otherwise>
       </c:choose>
     </div>
      <div id="userPhoto"><img src="<%= PropertyUtil.getStaticUrl() %>/style/images/ICN_Web_PersonalInformation_20x20.png" id="user" />&nbsp;
        <a id="username" href="${pageContext.request.contextPath}/showUser">${user.userName}</a>
      </div>
      <div ><a id="logout" href="${pageContext.request.contextPath }/Logout"><fmt:message key='logout'/></a></div>
    </div>

    <div id="tip_message_delete_exam" >
      <span id="delete_img_span"><img id="delete_img_span_id" src="<%= PropertyUtil.getStaticUrl() %>/style/images/BTN_Close_16x16.png" /></span>
      <span id="tip_message_span" >Sorry, the question is not enough in the system !</span>
      <span><input type="button" id="span_yes_button" value="Save" onclick="yesButton()"/></span>
      <span><input type="button" id="span_no_button" value="Cancle" onclick="noButton()" /></span>
    </div>

    <div id="menu">
      <div id="question_M"><a href="${pageContext.request.contextPath}/QuestionManager?forwardId=reset" id="question_manager"><fmt:message key='question.manager'/></a></div>
      <div id="exam_M" class="title_style" ><a href="${pageContext.request.contextPath}/examReset" id="exam_manager"><fmt:message key='exam.manager'/></a></div>
    </div>

    <div id="main">
      <div id="navigation_title"><a href="${pageContext.request.contextPath}/examList" id="navigation_title_question_manager"><fmt:message key='exam.manager'/></a>&nbsp;&nbsp;&gt;&nbsp;&nbsp;<fmt:message key='create.exam'/></div>
      <div id="wight_area">

      <div id="success_flash_message"  ${empty sessionScope.SUCCESS_FLASH_MESSAGE ? "style=display:none" : "style=display:inline;" }>${sessionScope.SUCCESS_FLASH_MESSAGE}</div>
      <form id="exam_form" action="${pageContext.request.contextPath}/CreateExam" method="POST">
        <div id="text_exam_id"><fmt:message key='create.exam.exam.id'/><span class="is_need" >*</span></div>
        <input type="text" id="exam_id" name="examId" onchange="changeStyle(data)" /><span class="error_message" id="error_exam_id" >Exam Id can't be null ${examError.examId }</span>

        <div id="text_exam_name"><fmt:message key='create.exam.exam.name'/><span class="is_need" >*</span></div>
        <input type="text" id="exam_name" name="examName" onchange="changeStyle(data);" /><span class="error_message" id="error_exam_name" >Exam Name can't be null ${examError.examName }</span>

        <div id="text_description"><fmt:message key='create.exam.exam.description'/></div>
        <textarea id="exam_description" rows="3" cols="47" name="description" onchange="changeStyle(data);" style="resize:none;"></textarea>

        <div id="text_effective_time" ><fmt:message key='create.exam.exam.effective.time'/><span class="is_need" >*</span></div>
        <input type="date" id="exam_effective_time" name="effTime" placeHolder="Select the date" onchange="changeStyle(data);" /><span class="error_message" id="error_effective_time" >Effective time can't be null ${examError.effectiveTime }</span>
        <input type="text" id="exam_effective_hour" name="effHour" placeHolder="Hour" onchange="changeStyle(data);" />
        <input type="text" id="exam_effective_min" name="effMin" placeHolder="Min" onchange="changeStyle(data);" />

        <div id="text_duration" ><fmt:message key='create.exam.exam.duration'/><span class="is_need" >*</span></div>
        <span class="error_message" id="error_duration" >Duration time can't be null ${examError.duration }</span>
        <span ><span id="duration_img"><img id="img_duration" src="<%= PropertyUtil.getStaticUrl() %>/style/images/ICN_Keep_12x20.png" /></span>
          <select id="exam_duration" name="duration">
            <option>20</option>
            <option>40</option>
            <option>60</option>
          </select>
        </span><span id="min" >min</span>

        <div id="text_question_setting" ><fmt:message key='create.exam.exam.question.setting'/><span class="is_need" >*</span></div>
        <div id="text_question_quantity"><fmt:message key='create.exam.exam.question.quantity'/></div>
        <input type="text" id="exam_question_quantity" name="questionQuantity" onchange="caclScore()" /><span class="error_message" id="error_question_quantity" ><img src="<%= PropertyUtil.getStaticUrl() %>/style/images/ICN_Client_Login_Wrong_20X20.png" /></span>

        <div id="text_question_points"><fmt:message key='create.exam.exam.question.points'/></div>
        <input type="text" id="exam_question_points" name="questionPoints" onchange="caclScore()" /><span class="error_message" id="error_question_points" ><img src="<%= PropertyUtil.getStaticUrl() %>/style/images/ICN_Client_Login_Wrong_20X20.png" /></span>

        <div id="text_total_score"><fmt:message key='create.exam.exam.question.score'/></div>
        <input type="text" id="exam_total_score" readonly name="fullScore" onchange="caclScore()"/><span class="error_message" id="error_full_score" ><img src="<%= PropertyUtil.getStaticUrl() %>/style/images/ICN_Client_Login_Wrong_20X20.png" /></span>

        <div id="text_pass_criteria"><fmt:message key='create.exam.exam.question.criteria'/></div>
        <input type="text" id="exam_pass_criteria" name="passCriteria" onchange="changeStyle(data)" /><span class="error_message" id="error_pass_criteria" ><img src="<%= PropertyUtil.getStaticUrl() %>/style/images/ICN_Client_Login_Wrong_20X20.png" /></span>

        <div >
          <input  id="exam_edit" type="button" value="Create" onclick="createExamSubmit()" /></div>
        <div >
          <input id="exam_cancle" type="reset" value="Cancle" />
        </div>
    </form>
    </div>
  </div>
  <div id="q_foot_create">Copyright &copy; 2016 Augmentum,Inc. All Right Reserved.</div>
</body>
</html>