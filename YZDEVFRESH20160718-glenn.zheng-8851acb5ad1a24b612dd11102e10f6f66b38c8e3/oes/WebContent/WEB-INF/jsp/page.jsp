    <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
    <%@ page import="com.augmentum.oes.util.PropertyUtil" %>
    <a href="${pageContext.request.contextPath}/${page.servleturl}?pagenum=${page.pagenum-1==0?page.pagenum:page.pagenum-1}"><img  id="pageLeft" src="<%= PropertyUtil.getStaticUrl() %>/style/images/BTN_PageLeft_20x15.png" onclick="pageLeft"/></a>
    
    <c:forEach begin="${page.startpage}" end="${page.endpage}" var="num" >
      <a href="${pageContext.request.contextPath}/${page.servleturl}?pagenum=${num}">${num}</a>
    </c:forEach>
    
     <a href="${pageContext.request.contextPath}/${page.servleturl}?pagenum=${page.pagenum+1>page.totalpage?page.totalpage:page.pagenum+1}"><img  id="pageRight" src="<%= PropertyUtil.getStaticUrl() %>/style/images/BTN_PageRight_20x15.png" onclick="pageRight" /></a>
    <select id="s1">
       <c:forEach begin="1" end="${page.totalpage}" var="num">
           <option val="${num}" ${page.pagenum==num?"selected='selected'":"" }>${num}</option>
       </c:forEach>
    </select>
    <a href ="javascript:jump()">G0</a>
    <script type="text/javascript">
        function jump(){
            var num = document.getElementById("s1").value;
            window.location.href="${pageContext.request.contextPath}/${page.servleturl}?pagenum="+num;
        }
    </script>
