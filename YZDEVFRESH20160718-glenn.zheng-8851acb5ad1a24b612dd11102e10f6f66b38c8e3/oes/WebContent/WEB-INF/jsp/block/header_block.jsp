<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.augmentum.oes.util.PropertyUtil" %>
<script type="text/javascript" src="<%= PropertyUtil.getStaticUrl() %>/lib/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%= PropertyUtil.getStaticUrl() %>/js/header.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" type="text/css" href="<%= PropertyUtil.getStaticUrl() %>/style/header.css" />
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
  <div id="question_M" class="title_style"><a href="${pageContext.request.contextPath}/QuestionManager?forwardId=reset" id="question_manager"><fmt:message key='question.manager'/></a></div>
  <div id="exam_M"><a href="${pageContext.request.contextPath}/examReset" id="exam_manager"><fmt:message key='exam.manager'/></a></div>
</div>