<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.augmentum.oes.util.PropertyUtil" %>

<a  id="pageLeft"  href="${pageContext.request.contextPath}/${page.servleturl}?forwardId=1&pagenum=${page.pagenum-1==0?page.pagenum:page.pagenum-1}&pagesize=${pageSize}&keywords=${keywords}"><img src="<%= PropertyUtil.getStaticUrl() %>/style/images/BTN_PageLeft_20x15.png" onclick="pageLeft"/></a> 

<c:if test="${page.totalpage eq 1 }">
     <a style="color:#FE9901" class="pageNum_bottom" href="${pageContext.request.contextPath}/${page.servleturl}?forwardId=1&pagenum=${page.startpage}&pagesize=${pageSize}&keywords=${keywords} ">1</a>
</c:if>

<c:if test="${page.totalpage gt 4 }" >
  <c:if test="${page.pagenum eq 1 }">
      <a style="color:#FE9901" class="pageNum_bottom" href="${pageContext.request.contextPath}/${page.servleturl}?forwardId=1&pagenum=${page.startpage}&pagesize=${pageSize}&keywords=${keywords} ">${page.startpage}</a>
      <a class="pageNum_bottom" href="${pageContext.request.contextPath}/${page.servleturl}?forwardId=1&pagenum=${page.startpage+1}&pagesize=${pageSize}&direction=${direction}&keywords=${keywords}">${page.startpage+1}</a>
      <a class="pageNum_bottom" href="${pageContext.request.contextPath}/${page.servleturl}?forwardId=1&pagenum=${page.startpage+2}&pagesize=${pageSize}&keywords=${keywords}">${page.startpage+2}</a>
      <a id="pageNum_bottom_end" href="${pageContext.request.contextPath}/${page.servleturl}?forwardId=1&pagenum=${page.totalpage}&pagesize=${pageSize}&keywords=${keywords}">${page.totalpage}</a>
      <input type="hidden" value="${page.totalpage }" id="page_total_id" />
      <div id="hidden_page">...</div>
  </c:if>

  <c:if test="${page.pagenum eq 2 }">
      <a  class="pageNum_bottom" href="${pageContext.request.contextPath}/${page.servleturl}?forwardId=1&pagenum=${page.startpage}&pagesize=${pageSize}&keywords=${keywords} ">${page.startpage}</a>
      <a style="color:#FE9901" class="pageNum_bottom" href="${pageContext.request.contextPath}/${page.servleturl}?forwardId=1&pagenum=${page.startpage+1}&pagesize=${pageSize}&direction=${direction}&keywords=${keywords}">${page.startpage+1}</a>
      <a class="pageNum_bottom" href="${pageContext.request.contextPath}/${page.servleturl}?forwardId=1&pagenum=${page.startpage+2}&pagesize=${pageSize}&keywords=${keywords}">${page.startpage+2}</a>
      <a id="pageNum_bottom_end" href="${pageContext.request.contextPath}/${page.servleturl}?forwardId=1&pagenum=${page.totalpage}&pagesize=${pageSize}&keywords=${keywords}">${page.totalpage}</a>
      <input type="hidden" value="${page.totalpage }" id="page_total_id" />
      <div id="hidden_page">...</div>
  </c:if>

  <c:if test="${page.pagenum gt 2 }">
      <c:if test="${page.pagenum le page.totalpage - 3}">
      <a  class="pageNum_bottom" href="${pageContext.request.contextPath}/${page.servleturl}?forwardId=1&pagenum=1&pagesize=${pageSize}&keywords=${keywords} ">1</a>
      <div id="hidden_page_middle">...</div>
      <a style="color:#FE9901" class="pageNum_bottom_middle" href="${pageContext.request.contextPath}/${page.servleturl}?forwardId=1&pagenum=${page.startpage+1}&pagesize=${pageSize}&direction=${direction}&keywords=${keywords}">${page.startpage+1}</a>
      <a class="pageNum_bottom_middle" href="${pageContext.request.contextPath}/${page.servleturl}?forwardId=1&pagenum=${page.startpage+2}&pagesize=${pageSize}&keywords=${keywords}">${page.startpage+2}</a>
      <a id="pageNum_bottom_end" href="${pageContext.request.contextPath}/${page.servleturl}?forwardId=1&pagenum=${page.totalpage}&pagesize=${pageSize}&keywords=${keywords}">${page.totalpage}</a>
      <input type="hidden" value="${page.totalpage }" id="page_total_id" />
      <div id="hidden_page">...</div>
      </c:if>
  </c:if>

  <%-- <c:if test="${page.pagenum eq page.totalpage-3 }">
      <a  class="pageNum_bottom" href="${pageContext.request.contextPath}/${page.servleturl}?forwardId=1&pagenum=1&pagesize=${pageSize}&keywords=${keywords} ">1</a>
      <div id="hidden_page_middle">...</div>
      <a style="color:#FE9901" class="pageNum_bottom_middle" href="${pageContext.request.contextPath}/${page.servleturl}?forwardId=1&pagenum=${page.startpage+1}&pagesize=${pageSize}&direction=${direction}&keywords=${keywords}">${page.totalpage-3}</a>
      <a class="pageNum_bottom_middle" href="${pageContext.request.contextPath}/${page.servleturl}?forwardId=1&pagenum=${page.startpage+2}&pagesize=${pageSize}&keywords=${keywords}">${page.totalpage-2}</a>
      <a id="pageNum_bottom_end" href="${pageContext.request.contextPath}/${page.servleturl}?forwardId=1&pagenum=${page.totalpage}&pagesize=${pageSize}&keywords=${keywords}">${page.totalpage}</a>
      <input type="hidden" value="${page.totalpage }" id="page_total_id" />
      <a class="pageNum_bottom_middle" href="${pageContext.request.contextPath}/${page.servleturl}?forwardId=1&pagenum=${page.startpage+2}&pagesize=${pageSize}&keywords=${keywords}">${page.startpage-1}</a>
  </c:if> --%>

  <c:if test="${page.pagenum eq page.totalpage }">
      <a  class="pageNum_bottom" href="${pageContext.request.contextPath}/${page.servleturl}?forwardId=1&pagenum=1&pagesize=${pageSize}&keywords=${keywords} ">1</a>
      <div id="hidden_page_middle">...</div>
      <a class="pageNum_bottom_middle_last" href="${pageContext.request.contextPath}/${page.servleturl}?forwardId=1&pagenum=${page.startpage+2}&pagesize=${pageSize}&keywords=${keywords}">${page.totalpage-2}</a>
      <a style="color:#FE9901" id="pageNum_bottom_end" href="${pageContext.request.contextPath}/${page.servleturl}?forwardId=1&pagenum=${page.totalpage}&pagesize=${pageSize}&keywords=${keywords}">${page.totalpage}</a>
      <input type="hidden" value="${page.totalpage }" id="page_total_id" />
      <a class="pageNum_bottom_middle_last" href="${pageContext.request.contextPath}/${page.servleturl}?forwardId=1&pagenum=${page.startpage+2}&pagesize=${pageSize}&keywords=${keywords}">${page.totalpage-1}</a>
  </c:if>

  <c:if test="${page.pagenum eq page.totalpage - 1 }">
      <a  class="pageNum_bottom" href="${pageContext.request.contextPath}/${page.servleturl}?forwardId=1&pagenum=1&pagesize=${pageSize}&keywords=${keywords} ">1</a>
      <div id="hidden_page_middle">...</div>
      <a class="pageNum_bottom_middle_last" href="${pageContext.request.contextPath}/${page.servleturl}?forwardId=1&pagenum=${page.startpage+2}&pagesize=${pageSize}&keywords=${keywords}">${page.totalpage-2}</a>
      <a  id="pageNum_bottom_end" href="${pageContext.request.contextPath}/${page.servleturl}?forwardId=1&pagenum=${page.totalpage}&pagesize=${pageSize}&keywords=${keywords}">${page.totalpage}</a>
      <input type="hidden" value="${page.totalpage }" id="page_total_id" />
      <a style="color:#FE9901" class="pageNum_bottom_middle_last" href="${pageContext.request.contextPath}/${page.servleturl}?forwardId=1&pagenum=${page.startpage+2}&pagesize=${pageSize}&keywords=${keywords}">${page.totalpage-1}</a>
  </c:if>

  <c:if test="${page.pagenum eq page.totalpage - 2 }">
      <a  class="pageNum_bottom" href="${pageContext.request.contextPath}/${page.servleturl}?forwardId=1&pagenum=1&pagesize=${pageSize}&keywords=${keywords} ">1</a>
      <div id="hidden_page_middle">...</div>
      <a style="color:#FE9901" class="pageNum_bottom_middle_last" href="${pageContext.request.contextPath}/${page.servleturl}?forwardId=1&pagenum=${page.startpage+2}&pagesize=${pageSize}&keywords=${keywords}">${page.totalpage-2}</a>
      <a  id="pageNum_bottom_end" href="${pageContext.request.contextPath}/${page.servleturl}?forwardId=1&pagenum=${page.totalpage}&pagesize=${pageSize}&keywords=${keywords}">${page.totalpage}</a>
      <input type="hidden" value="${page.totalpage }" id="page_total_id" />
      <a class="pageNum_bottom_middle_last" href="${pageContext.request.contextPath}/${page.servleturl}?forwardId=1&pagenum=${page.startpage+2}&pagesize=${pageSize}&keywords=${keywords}">${page.totalpage-1}</a>
  </c:if>
</c:if>

<a id="pageRight" href="${pageContext.request.contextPath}/${page.servleturl}?forwardId=1&pagenum=${page.pagenum+1>page.totalpage?page.totalpage:page.pagenum+1}&pagesize=${pageSize}&keywords=${keywords}"><img   src="<%= PropertyUtil.getStaticUrl() %>/style/images/BTN_PageRight_20x15.png" onclick="pageRight" /></a>

<select id="s1" onchange="changePerPage()">
  <c:forEach begin="1" end="10" var="num">  <%--  ${page.pagesize==num?"selected='selected'":""}--%>
    <option id="id_page_size_option" val ="${page.pagesize}" ${page.pagesize==num?"selected='selected'":""}  name="page_option" >${num}</option>
  </c:forEach>
</select>

<input type="hidden" name="pagesize" id="page_size_" value="${page.pagesize } "/>
<input type="hidden" name="keywords" id="id_keyword" value="${keywords}" />
<input type="hidden" name="pagenum" id="page_current_num" value="${page.pagenum}" />
<input type="hidden" name="totalPage" id="totalPage" value="${page.totalpage }" />
<%-- <input type="hidden" id="direction" value="${direction}" /> --%>
<a href ="javascript:jump()" id="go_id">G0</a>
<form action="#">
  <input id="input_num" type="text" name="num"/>
</form>
<script type="text/javascript">
function jump(){
        var num = document.getElementById("input_num").value;
        var reg = /^[-]{0,1}[0-9]{1,}$/;
        if(!reg.test(num)){
            document.getElementById("input_num").style.border = "1px dashed #EB340A";
            var ele = document.getElementById("error_flash_message");
            ele.style.display="inline";
            ele.innerHTML = "please input correct num!";
            setTimeout(function(){
                document.getElementById("error_flash_message").style.display = "none";
            },3000);
            document.getElementById("input_num").value = "";
            return;
            }
        if (num < 1) {
          num = 1;
        }
        var total_page = document.getElementById("totalPage").value;
        if (parseInt(num) > parseInt(total_page)) {
            num = total_page;
        }
        window.location.href="${pageContext.request.contextPath}/QuestionManager?forwardId=1&pagesize=${pageSize}&keywords=${keywords}&pagenum="+num;
    }
</script>
<div id="per_page"> Per &nbsp;Page</div>

