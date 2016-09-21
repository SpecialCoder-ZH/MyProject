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

    <div id="menu">
      <div id="question_M"><a href="${pageContext.request.contextPath}/QuestionManager?forwardId=reset" id="question_manager"><fmt:message key='question.manager'/></a></div>
      <div id="exam_M" class="title_style" ><a href="${pageContext.request.contextPath}/examReset" id="exam_manager"><fmt:message key='exam.manager'/></a></div>
    </div>

    <div id="main">
      <div id="navigation_title">
        <a href="${pageContext.request.contextPath}/examList" id="navigation_title_question_manager"><fmt:message key='exam.manager'/></a>&nbsp;&nbsp;&gt;&nbsp;&nbsp;
        <a href="${pageContext.request.contextPath}/examList?pagenum=${pagenum}&pagesize=${page.pagesize}&keywords=${keywords}" id="navigation_title_question_manager"><fmt:message key='exam.list'/></a>&nbsp;&nbsp;&gt;&nbsp;&nbsp;
        ${exam.examId}
      </div>
      <div id="wight_area">

      <%-- <div id="success_flash_message"  ${empty sessionScope.SUCCESS_FLASH_MESSAGE ? "style=display:none" : "style=display:inline;" }>${sessionScope.SUCCESS_FLASH_MESSAGE}</div> --%>

      <form id="exam_edit_form" action="${pageContext.request.contextPath}/EditExam" method="POST">
        <input type="hidden" name="id" value="${id}" />
        <input type="hidden" name="isUsed" value="${exam.isUsed}" />
        <input type="hidden" name="isDeleted" value="${exam.isDeleted}" />
        <input type="hidden" name="pagenum" value="${pagenum}" />
        <input type="hidden" name="pagesize" value="${pagesize}" />
        <input type="hidden" name="keywords" value="${keywords}" />
        <input type="hidden" name="direction" value="${direction}" />
        <input type="hidden" name="nameOrder" value="${nameOrder}" />
        <input type="hidden" name="timeOrder" value="${timeOrder}" />
        <input type="hidden" name="endDate" value="${endDate}" />
        <div id="text_exam_id">Exam Id:<span class="is_need" >*</span></div>
        <input type="text" readonly id="exam_id" name="examId" value="${exam.examId}" /><span class="error_message" id="error_exam_id" >Exam Id can't be null ${exam_error.examId }</span>

        <div id="text_exam_name">Exam Name:<span class="is_need" >*</span></div>
        <input type="text" id="exam_name" name="examName" value="${exam.examName}" /><span class="error_message" id="error_exam_name" >Exam Name can't be null ${exam_error.examId }</span>

        <div id="text_description">Description:</div>
        <textarea id="exam_description" rows="3" cols="47" name="description" style="resize:none;">${exam.description}</textarea>

        <div id="text_effective_time" >Effective Time:<span class="is_need" >*</span></div>
        <input type="text" id="exam_effective_time" name="effTime" value="<fmt:formatDate value="${exam.effectiveTime }" pattern="yyyy-MM-dd" />" placeHolder="Select the date" /><span class="error_message" id="error_effective_time" >Effective time can't be null ${exam_error.effectiveTime }</span>
        <input type="text" id="exam_effective_hour" name="effHour" value="<fmt:formatDate value="${exam.effectiveTime }" pattern="HH" />" placeHolder="Hour" />
        <input type="text" id="exam_effective_min" name="effMin" value="<fmt:formatDate value="${exam.effectiveTime }" pattern="mm" />" placeHolder="Min" />

        <div id="text_duration" >Duration:<span class="is_need" >*</span></div>
        <span class="error_message" >Duration time can't be null ${examError.duration }</span>

        <span id="duration_img"><img id="img_duration" src="<%= PropertyUtil.getStaticUrl() %>/style/images/ICN_Keep_12x20.png"" /></span>
        <span>
          <select id="exam_duration" name="duration">
            <option ${exam.duration == 20 ? "selected='selected'" : "" }>20</option>
            <option ${exam.duration == 40 ? "selected='selected'" : "" }>40</option>
            <option ${exam.duration == 60 ? "selected='selected'" : "" }>60</option>
          </select>
        </span><span id="min" >min</span>

        <div id="text_question_setting" >Question Setting:<span class="is_need" >*</span></div>
        <div id="text_question_quantity">Question Quantity:</div>
        <input type="text" id="exam_question_quantity" onchange="caclScore()" value="${exam.questionQuantity}" name="questionQuantity"/><span class="error_message" id="error_question_quantity" >QuestionQuantity Id can't be null ${exam_error.questionQuantity }</span>
        <div id="text_question_points">Question Points:</div>
        <input type="text" id="exam_question_points" name="questionPoints" onchange="caclScore()" value="${exam.questionPoints}"/><span class="error_message" id="error_question_points" >QuestionPoints Id can't be null ${exam_error.questionPoints }</span>
        <div id="text_total_score">Total Score:</div>
        <input type="text" id="exam_total_score" readonly name="fullScore" value="${exam.fullScore}" /><span class="error_message" id="error_full_score" >Total Score can't be null ${exam_error.fullScore }</span>
        <div id="text_pass_criteria">Pass Criteria:</div>
        <input type="text" id="exam_pass_criteria" name="passCriteria" value="${exam.passCriteria}" /><span class="error_message" id="error_pass_criteria" >Exam Id can't be null ${exam_error.examId }</span>

        <div >
          <input  id="exam_edit" type="button" value="Save" onclick="editExamSubmit()" /></div>
        <div >
          <input id="exam_cancle" type="reset" value="Cancle" />
        </div>
      </form>
    </div>
  </div>
  <div id="q_foot_create">Copyright &copy; 2016 Augmentum,Inc. All Right Reserved.</div>
</body>
</html>