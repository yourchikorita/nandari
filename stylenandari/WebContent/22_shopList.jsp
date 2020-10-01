<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="css/style.css"/>
<script src="js/jquery-1.11.0.min.js"></script>


<div id="shop" class="box2">
  <c:if test="${item_kind=='100'}">
    <c:set var="item_kindName" value="탑"/>
  </c:if>
  <c:if test="${item_kind=='200'}">
    <c:set var="item_kindName" value="드레스"/>
  </c:if>
  <c:if test="${item_kind=='300'}">
    <c:set var="item_kindName" value="악세사리"/>
  </c:if>
  
  <c:if test="${item_kind=='all'}">
    <c:set var="item_kindName" value="전체"/>
    <c:set var="display" value="전체 목록"/>
  </c:if>
  <c:if test="${item_kind!='all'}">
    <c:set var="display" value="[${item_kindName}] 분류의 목록"/>
  </c:if>
  
  <p class="b">${display} : (${count}개)</p>

   	
   	
   	<c:set var="i" value="0" />
	<c:set var="j" value="3" />
	<table >
	  <c:forEach items="${itemList }" var="list">
	    <c:if test="${i%j == 0 }">
	    <tr>
	    </c:if>
	       <td>
   				<a href="itemContent.do?item_id=${list.getItem_id()}&item_kind=${list.getItem_kind()}">
   				<img style="height:270px;margin:20px" src="images/${list.getItem_image() }" ></a>
   				<br/><span style="margin:55px;">${list.getItem_name() }</span>
   				<br/><span style="margin:115px;">${list.getItem_price() }원</span>
   				
	       </td>
	      
	    <c:if test="${i%j == j-1 }">
	    </tr>
	    </c:if>
	    <c:set var="i" value="${i+1 }" />
	  </c:forEach>
	</table>


</div>
