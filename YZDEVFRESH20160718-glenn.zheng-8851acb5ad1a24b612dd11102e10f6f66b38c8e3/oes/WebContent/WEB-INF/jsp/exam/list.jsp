<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.augmentum.oes.util.PropertyUtil" %>
<%@ page import="java.util.Date" %>
<%@ taglib uri="/WEB-INF/block.tld" prefix="block" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     <title><fmt:message key='exam.list'/></title>
     <link rel="stylesheet" type="text/css" href="<%= PropertyUtil.getStaticUrl() %>/style/header.css" />
     <script type="text/javascript" src="<%= PropertyUtil.getStaticUrl() %>/lib/jquery-1.10.2.min.js"></script>
     <script type="text/javascript" src="<%= PropertyUtil.getStaticUrl() %>/js/exam.js"></script>
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
    <div id="userPhoto" ><img src="<%= PropertyUtil.getStaticUrl() %>/style/images/ICN_Web_PersonalInformation_20x20.png" id="user" />&nbsp;
      <a id="username" href="${pageContext.request.contextPath}/showUser">${user.userName}</a>
    </div>
    <div ><a id="logout" href="${pageContext.request.contextPath }/Logout"><fmt:message key='logout'/></a></div>
  </div>

  <div id="menu">
    <div id="question_M"><a href="${pageContext.request.contextPath}/QuestionManager?forwardId=reset" id="question_manager"><fmt:message key='question.manager'/></a></div>
    <div id="exam_M" class="title_style" ><a href="${pageContext.request.contextPath}/examReset" id="exam_manager"><fmt:message key='exam.manager'/></a></div>
  </div>
  
  <div id="success_delete" ${SUCCESS_DELETE == null ? "style=display:none" : "style=display:inline" }>${SUCCESS_DELETE}</div>
  <% 
      session.removeAttribute("SUCCESS_DELETE");
  %>

    <div id="main">
      <div id="wight_area">
      <div id="error_flash_message"></div>
      <div id="question_search"> 
        <img id="exam_search_image" src="<%= PropertyUtil.getStaticUrl() %>/style/images/ICN_Search_15x20.png" onclick="serchByKeys()" />
        <input type="text" id="exam_input_keywords" name="keywords" placeholder="<fmt:message key='question.list.keywords'/>" value="${keywords}" />
      </div>

      <div id="search_by_date"> 
        <input type="date" id="begin_date" value="<fmt:formatDate value="<%= new Date() %>" pattern="yyyy-MM-dd" />" />
        <span id="conn_line">-</span>
        <input type="date" id="end_date" name="endDate" value="${endDate}" />
        <span id="date_button" >Date</span>
      </div>

    <div id="left_menu">
      <div id="question_list"><a href="#" id="a_question_list" ><fmt:message key='exam.list'/></a></div>
      <div id="create_question"><a href="${pageContext.request.contextPath}/ToCreateExam" id="a_create_question" ><fmt:message key='create.exam'/></a></div>
    </div>

      <div id="content">

      <div id="tip_message_delete" >
        <span id="delete_img_span"><img id="delete_img_span_id" src="<%= PropertyUtil.getStaticUrl() %>/style/images/BTN_Close_16x16.png" onclick="deleteQuestion()" /></span>
        <span id="tip_message_span" ><fmt:message key='delete.confirm'/></span>
        <span><input type="button" id="span_yes_button" value="<fmt:message key='yes'/>" onclick="yesButtonOk()"/></span>
        <span><input type="button" id="span_no_button" value="<fmt:message key='no'/>" onclick="noButtonNo()" /></span>
      </div>

     <div class="form_tilte">
      <span class="form_blank"></span >
      <input type="hidden" id="id_nameOrder" value="${nameOrder}" />
      <input type="hidden" id="id_timeOrder" value="${timeOrder}" />
      <span class="form_id"><fmt:message key='exam.list.id'/>
        <c:choose>
          <c:when test="${direction == 'DESC' }">
            <img src="<%= PropertyUtil.getStaticUrl() %>/style/images/ICN_Increase_10x15.png" id="id_decrese" onclick="changeDecrese()"/>
          </c:when>
          <c:otherwise>
            <img src="<%= PropertyUtil.getStaticUrl() %>/style/images/ICN_Decrese_10x15.png" id="id_increse" onclick="changeIncrese()"/>
          </c:otherwise>
        </c:choose>
      </span>
      <span class="name"><fmt:message key='exam.list.name'/>
        <c:choose>
          <c:when test="${nameOrder == 'DESC' }">
            <img src="<%= PropertyUtil.getStaticUrl() %>/style/images/ICN_Increase_10x15.png" id="name_decrese" onclick="changeNameDecrese()"/>
          </c:when>
          <c:otherwise>
            <img src="<%= PropertyUtil.getStaticUrl() %>/style/images/ICN_Decrese_10x15.png" id="name_increse" onclick="changeNameIncrese()"/>
          </c:otherwise>
        </c:choose>
      </span>
      <span class="effective_time"><fmt:message key='exam.list.effective.time'/>
        <c:choose>
          <c:when test="${timeOrder == 'DESC' }">
            <img src="<%= PropertyUtil.getStaticUrl() %>/style/images/ICN_Increase_10x15.png" id="time_decrese" onclick="changeEffTimeDecrese()"/>
          </c:when>
          <c:otherwise>
            <img src="<%= PropertyUtil.getStaticUrl() %>/style/images/ICN_Decrese_10x15.png" id="time_increse" onclick="changeEffTimeIncrese()"/>
          </c:otherwise>
        </c:choose>
      </span>
      <span class="duration"><fmt:message key='exam.list.duration'/></span>
      <span class="creator"><fmt:message key='exam.list.creator'/></span>
      <span class="form_edit"><fmt:message key='exam.list.edit'/></span>
      <span class="form_check"><img class="form_check_img" id="form_check_img_id"  src="<%= PropertyUtil.getStaticUrl() %>/style/images/ICN_Unselected_15x15.png" onclick="deleteAllQuestion()"/></span>
    </div>

    <c:forEach items="${page.records}" var="q_list" varStatus="vs" >
      <div class="form_result">
        <span class="form_result_blank">${vs.count + page.startIndex}</span >
        <span class="form_result_id"><c:out value="${q_list.examId }"> </c:out></span>
        <span class="from_result_exam_name"><a id ="link_exam_name" href="#" title="${q_list.examName }" ><c:out value="${q_list.examName }"></c:out></a></span>
        <span class="from_result_effective_time"><fmt:formatDate value="${q_list.effectiveTime }" pattern="yyyy-MM-dd HH:mm" /></span>
        <span class="from_result_duration"><c:out value="${q_list.duration }"> </c:out></span>
        <span class="from_result_creator"><c:out value="${q_list.creator }"> </c:out></span>
        <span class="form_result_edit"><a href="${pageContext.request.contextPath}/ToEditExam?pagenum=${page.pagenum }&direction=${direction}&id=${q_list.id}&pagesize=${pageSize}&keywords=${keywords}"><img src="<%= PropertyUtil.getStaticUrl() %>/style/images/ICN_Edit_15x15.png" class ="form_result_image_edit"/></a></span>
        <span class="form_result_check">
          <div class="checkbox">
            <input type="checkbox" class="custom_checkbox" name="checkbox_option" onclick="changStatus()" >
            <input type="hidden" value="${q_list.examId}" name="q_list_display_id" />
          </div>
        </span>
      </div>
    </c:forEach>

  <div id="form_footer">
    <div >
      <input type="button" id="delete_button" value="<fmt:message key='exam.list.delete'/>" onclick="deleteOperation()" />
    </div>
    <a  id="pageLeft"  href="${pageContext.request.contextPath}/${page.servleturl}?direction=${direction}&timeOrder=${timeOrder}&nameOrder=${nameOrder}&pagenum=${page.pagenum-1==0?page.pagenum:page.pagenum-1}&pagesize=${pageSize}&keywords=${keywords}"><img src="<%= PropertyUtil.getStaticUrl() %>/style/images/BTN_PageLeft_20x15.png" onclick="pageLeft"/></a> 

    <c:if test="${page.totalpage eq 1 }">
      <a style="color:#FE9901" class="pageNum_bottom" href="${pageContext.request.contextPath}/${page.servleturl}?direction=${direction}&timeOrder=${timeOrder}&nameOrder=${nameOrder}&pagenum=${page.startpage}&pagesize=${pageSize}&keywords=${keywords} ">1</a>
    </c:if>

    <c:if test="${page.totalpage gt 4 }" >
      <c:if test="${page.pagenum eq 1 }">
        <a style="color:#FE9901" class="pageNum_bottom" href="${pageContext.request.contextPath}/${page.servleturl}?direction=${direction}&timeOrder=${timeOrder}&nameOrder=${nameOrder}&pagenum=${page.startpage}&pagesize=${pageSize}&keywords=${keywords} ">${page.startpage}</a>
        <a class="pageNum_bottom" href="${pageContext.request.contextPath}/${page.servleturl}?direction=${direction}&timeOrder=${timeOrder}&nameOrder=${nameOrder}&pagenum=${page.startpage+1}&pagesize=${pageSize}&direction=${direction}&keywords=${keywords}">${page.startpage+1}</a>
        <a class="pageNum_bottom" href="${pageContext.request.contextPath}/${page.servleturl}?direction=${direction}&timeOrder=${timeOrder}&nameOrder=${nameOrder}&pagenum=${page.startpage+2}&pagesize=${pageSize}&keywords=${keywords}">${page.startpage+2}</a>
        <a id="pageNum_bottom_end" href="${pageContext.request.contextPath}/${page.servleturl}?direction=${direction}&timeOrder=${timeOrder}&nameOrder=${nameOrder}&pagenum=${page.totalpage}&pagesize=${pageSize}&keywords=${keywords}">${page.totalpage}</a>
        <input type="hidden" value="${page.totalpage }" id="page_total_id" />
        <div id="hidden_page">...</div>
      </c:if>

      <c:if test="${page.pagenum eq 2 }">
        <a  class="pageNum_bottom" href="${pageContext.request.contextPath}/${page.servleturl}?direction=${direction}&timeOrder=${timeOrder}&nameOrder=${nameOrder}&pagenum=${page.startpage}&pagesize=${pageSize}&keywords=${keywords} ">${page.startpage}</a>
        <a style="color:#FE9901" class="pageNum_bottom" href="${pageContext.request.contextPath}/${page.servleturl}?direction=${direction}&timeOrder=${timeOrder}&nameOrder=${nameOrder}&pagenum=${page.startpage+1}&pagesize=${pageSize}&direction=${direction}&keywords=${keywords}">${page.startpage+1}</a>
        <a class="pageNum_bottom" href="${pageContext.request.contextPath}/${page.servleturl}?direction=${direction}&timeOrder=${timeOrder}&nameOrder=${nameOrder}&pagenum=${page.startpage+2}&pagesize=${pageSize}&keywords=${keywords}">${page.startpage+2}</a>
        <a id="pageNum_bottom_end" href="${pageContext.request.contextPath}/${page.servleturl}?direction=${direction}&timeOrder=${timeOrder}&nameOrder=${nameOrder}&pagenum=${page.totalpage}&pagesize=${pageSize}&keywords=${keywords}">${page.totalpage}</a>
        <input type="hidden" value="${page.totalpage }" id="page_total_id" />
        <div id="hidden_page">...</div>
      </c:if>

      <c:if test="${page.pagenum gt 2 }">
        <c:if test="${page.pagenum le page.totalpage - 3}">
          <a  class="pageNum_bottom" href="${pageContext.request.contextPath}/${page.servleturl}?direction=${direction}&timeOrder=${timeOrder}&nameOrder=${nameOrder}&pagenum=1&pagesize=${pageSize}&keywords=${keywords} ">1</a>
          <div id="hidden_page_middle">...</div>
          <a style="color:#FE9901" class="pageNum_bottom_middle" href="${pageContext.request.contextPath}/${page.servleturl}?direction=${direction}&timeOrder=${timeOrder}&nameOrder=${nameOrder}&pagenum=${page.startpage+1}&pagesize=${pageSize}&direction=${direction}&keywords=${keywords}">${page.startpage+1}</a>
          <a class="pageNum_bottom_middle" href="${pageContext.request.contextPath}/${page.servleturl}?direction=${direction}&timeOrder=${timeOrder}&nameOrder=${nameOrder}&pagenum=${page.startpage+2}&pagesize=${pageSize}&keywords=${keywords}">${page.startpage+2}</a>
          <a id="pageNum_bottom_end" href="${pageContext.request.contextPath}/${page.servleturl}?direction=${direction}&timeOrder=${timeOrder}&nameOrder=${nameOrder}&pagenum=${page.totalpage}&pagesize=${pageSize}&keywords=${keywords}">${page.totalpage}</a>
          <input type="hidden" value="${page.totalpage }" id="page_total_id" />
          <div id="hidden_page">...</div>
          </c:if>
    </c:if>

      <c:if test="${page.pagenum eq page.totalpage }">
          <a  class="pageNum_bottom" href="${pageContext.request.contextPath}/${page.servleturl}?direction=${direction}&timeOrder=${timeOrder}&nameOrder=${nameOrder}&pagenum=1&pagesize=${pageSize}&keywords=${keywords} ">1</a>
          <div id="hidden_page_middle">...</div>
          <a class="pageNum_bottom_middle_last" href="${pageContext.request.contextPath}/${page.servleturl}?direction=${direction}&timeOrder=${timeOrder}&nameOrder=${nameOrder}&pagenum=${page.startpage+2}&pagesize=${pageSize}&keywords=${keywords}">${page.totalpage-2}</a>
          <a style="color:#FE9901" id="pageNum_bottom_end" href="${pageContext.request.contextPath}/${page.servleturl}?direction=${direction}&timeOrder=${timeOrder}&nameOrder=${nameOrder}&pagenum=${page.totalpage}&pagesize=${pageSize}&keywords=${keywords}">${page.totalpage}</a>
          <input type="hidden" value="${page.totalpage }" id="page_total_id" />
          <a class="pageNum_bottom_middle_last" href="${pageContext.request.contextPath}/${page.servleturl}?direction=${direction}&timeOrder=${timeOrder}&nameOrder=${nameOrder}&pagenum=${page.startpage+2}&pagesize=${pageSize}&keywords=${keywords}">${page.totalpage-1}</a>
      </c:if>

      <c:if test="${page.pagenum eq page.totalpage-1 }">
        <a  class="pageNum_bottom" href="${pageContext.request.contextPath}/${page.servleturl}?direction=${direction}&timeOrder=${timeOrder}&nameOrder=${nameOrder}&pagenum=1&pagesize=${pageSize}&keywords=${keywords} ">1</a>
        <div id="hidden_page_middle">...</div>
        <a class="pageNum_bottom_middle_last" href="${pageContext.request.contextPath}/${page.servleturl}?direction=${direction}&timeOrder=${timeOrder}&nameOrder=${nameOrder}&pagenum=${page.startpage+2}&pagesize=${pageSize}&keywords=${keywords}">${page.totalpage-2}</a>
        <a  id="pageNum_bottom_end" href="${pageContext.request.contextPath}/${page.servleturl}?direction=${direction}&timeOrder=${timeOrder}&nameOrder=${nameOrder}&pagenum=${page.totalpage}&pagesize=${pageSize}&keywords=${keywords}">${page.totalpage}</a>
        <input type="hidden" value="${page.totalpage }" id="page_total_id" />
        <a style="color:#FE9901" class="pageNum_bottom_middle_last" href="${pageContext.request.contextPath}/${page.servleturl}?direction=${direction}&timeOrder=${timeOrder}&nameOrder=${nameOrder}&pagenum=${page.startpage+2}&pagesize=${pageSize}&keywords=${keywords}">${page.totalpage-1}</a>
      </c:if>

      <c:if test="${page.pagenum eq page.totalpage-2 }">
        <a  class="pageNum_bottom" href="${pageContext.request.contextPath}/${page.servleturl}?direction=${direction}&timeOrder=${timeOrder}&nameOrder=${nameOrder}&pagenum=1&pagesize=${pageSize}&keywords=${keywords} ">1</a>
        <div id="hidden_page_middle">...</div>
        <a style="color:#FE9901" class="pageNum_bottom_middle_last" href="${pageContext.request.contextPath}/${page.servleturl}?direction=${direction}&timeOrder=${timeOrder}&nameOrder=${nameOrder}&pagenum=${page.startpage+2}&pagesize=${pageSize}&keywords=${keywords}">${page.totalpage-2}</a>
        <a  id="pageNum_bottom_end" href="${pageContext.request.contextPath}/${page.servleturl}?direction=${direction}&timeOrder=${timeOrder}&nameOrder=${nameOrder}&pagenum=${page.totalpage}&pagesize=${pageSize}&keywords=${keywords}">${page.totalpage}</a>
        <input type="hidden" value="${page.totalpage }" id="page_total_id" />
        <a class="pageNum_bottom_middle_last" href="${pageContext.request.contextPath}/${page.servleturl}?direction=${direction}&timeOrder=${timeOrder}&nameOrder=${nameOrder}&pagenum=${page.startpage+2}&pagesize=${pageSize}&keywords=${keywords}">${page.totalpage-1}</a>
      </c:if>
    </c:if>

    <a id="pageRight" href="${pageContext.request.contextPath}/${page.servleturl}?direction=${direction}&timeOrder=${timeOrder}&nameOrder=${nameOrder}&pagenum=${page.pagenum+1>page.totalpage?page.totalpage:page.pagenum+1}&pagesize=${pageSize}&keywords=${keywords}"><img src="<%= PropertyUtil.getStaticUrl() %>/style/images/BTN_PageRight_20x15.png" onclick="pageRight" /></a>

    <select id="s1" onchange="changePerPage()">
      <c:forEach begin="1" end="10" var="num">  <%--  ${page.pagesize==num?"selected='selected'":""}--%>
        <option id="id_page_size_option" val ="${page.pagesize}" ${page.pagesize == num ? "selected = 'selected'" : ""}  name="page_option" >${num}</option>
      </c:forEach>
    </select>

    <form id="hidden_form" action="${pageContext.request.contextPath}/examList" method="get" >
      <input type="hidden" name="pagesize" id="page_size_" value="${page.pagesize } "/>
      <input type="hidden" name="keywords" id="id_keyword" value="${keywords}" />
      <input type="hidden" name="pagenum" id="page_current_num" value="${page.pagenum}" />
      <input type="hidden" name="totalPage" id="totalPage" value="${page.totalpage }" />
      <input type="hidden" name="direction" id="id_direction" value="${direction}" />
      <input type="hidden" name="timeOrder" id="id_timeOrder" value="${timeOrder}" />
      <input type="hidden" name="nameOrder" id="id_nameOrder" value="${nameOrder}" />
    </form>

    <a href ="javascript:jump()" id="go_id">G0</a>
    <div id="per_page"> Per &nbsp;Page</div>
    <form action="#">
        <input id="input_num" type="text" name="num" />
    </form>
  </div>
  </div>
</div>
<div id="q_foot">Copyright &copy; 2016 Augmentum,Inc. All Right Reserved.</div>
</div>
  </body>
</html>
