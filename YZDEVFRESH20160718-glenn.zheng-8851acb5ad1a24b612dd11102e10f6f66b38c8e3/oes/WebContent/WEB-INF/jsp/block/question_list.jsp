<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.augmentum.oes.util.PropertyUtil" %>
<%@ taglib uri="/WEB-INF/block.tld" prefix="block" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="form_tilte">
  <span class="form_blank"></span >
  <span class="form_id"><fmt:message key='question.list.id'/>
  <c:if test="${direction == 'ASC' }">
      <img src="<%= PropertyUtil.getStaticUrl() %>/style/images/ICN_Decrese_10x15.png" id="decrese" onclick="changeIncrese()"/>
  </c:if>
  <c:if test="${direction == 'DESC' }">
      <img src="<%= PropertyUtil.getStaticUrl() %>/style/images/ICN_Increase_10x15.png" id="decrese" onclick="changeDecrese()"/>
  </c:if>
  </span>
  <span class="description"><fmt:message key='question.list.description'/></span>
  <span class="form_edit"><fmt:message key='question.list.edit'/></span>
  <span class="form_check"><img class="form_check_img" id="form_check_img_id"  src="<%= PropertyUtil.getStaticUrl() %>/style/images/ICN_Unselected_15x15.png" onclick="deleteAllQuestion()"/></span>
</div>

<c:forEach items="${page.records}" var="q_list" varStatus="vs" >
  <div class="form_result">
    <span class="form_result_blank">${vs.count + page.startIndex}</span >
    <span class="form_result_id"><c:out value="${q_list.displayId }"> </c:out></span>
    <span class="from_result_description"><c:out value="${q_list.questionContent }"> </c:out></span>
    <span class="form_result_edit"><a href="${pageContext.request.contextPath}/ToEditQuestion?pagenum=${page.pagenum }&id=${q_list.id}&pagesize=${pageSize}&keywords=${keywords}"><img src="<%= PropertyUtil.getStaticUrl() %>/style/images/ICN_Edit_15x15.png" class ="form_result_image_edit"/></a></span>
    <span class="form_result_check">
      <div class="checkbox">
        <input type="checkbox" class="custom_checkbox" name="checkbox_option" onclick="changStatus()" >
        <input type="hidden" value="${q_list.displayId}" name="q_list_display_id" />
      </div>
    </span>
  </div>
</c:forEach>

<div id="form_footer">
<div >
  <input type="button" id="delete_button" value="<fmt:message key='question.list.delete'/>" onclick="deleteOperation()" />
</div>
<block:display name="pagnationBlock"/>
</div>