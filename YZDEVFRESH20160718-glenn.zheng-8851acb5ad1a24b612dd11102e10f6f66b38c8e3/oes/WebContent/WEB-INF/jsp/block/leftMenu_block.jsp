<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div id="left_menu">
  <div id="question_list"><a href="#" id="a_question_list" ><fmt:message key='question.list'/></a></div>
  <div id="create_question"><a href="${pageContext.request.contextPath}/QuestionManager?forwardId=2" id="a_create_question" ><fmt:message key='create.question'/></a></div>
</div>