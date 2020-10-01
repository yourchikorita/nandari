<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="css/style.css"/>
<h3>최신 상품</h3>
<div id="shop" class="box2" >
<c:forEach var="itemList" items="${itemLists }">
	<c:set var="item_kind" value="${itemList[0].getItem_kind() }"/>
	<c:if test="${item_kind==100 }">
		<c:set var="item_kindName" value="top"/>
	</c:if>
	<c:if test="${item_kind==200 }">
		<c:set var="item_kindName" value="dress"/>
	</c:if>
	<c:if test="${item_kind==300 }">
		<c:set var="item_kindName" value="acc"/>
	</c:if>
	<br/>
	<br/>
	<h5 class="b">${item_kindName}. <a  href="/stylenandari/list.do?item_kind=${item_kind}">더보기</a></h5>
	
	<div id="show"> 
	<c:forEach var="itemImg" items="${itemList}">
   		<a href="itemContent.do?item_id=${itemImg.getItem_id()}&item_kind=${itemImg.getItem_kind()}"><img style="height:270px;margin:20px" src="images/${itemImg.getItem_image() }" ></a>
	</c:forEach>
	<br/>
	<c:forEach var="itemn" items="${itemList}">
   		<span style="margin:55px;">${itemn.getItem_name() }</span>
   	</c:forEach>
   	<br/>
   	<c:forEach var="itemn" items="${itemList}">
   		<span style="margin:115px;">${itemn.getItem_price() }원</span>
   	</c:forEach>
   	<br/>
   
   	</div> 
</c:forEach>

</div>