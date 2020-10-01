<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="http://localhost:8080/stylenandari/css/style.css"/>
<script src="http://localhost:8080/stylenandari/js/jquery-1.11.0.min.js"></script>
<script src="http://localhost:8080/stylenandari/05_itemlist.js"></script>

<c:if test="${empty sessionScope.id}">
  <meta http-equiv="Refresh" content="0;url=managerMain.do" >
</c:if>

<div id="listHeader">
  <p>등록된 상품 목록(전체 상품:${count})
  <button id="regist">등록</button>
  <button id="itemMain">관리자 메인으로</button>
</div>
<div id="items">
  <c:if test="${count == 0}">
    <ul>
      <li>등록된 상품이 없습니다.
    </ul>
  </c:if>
  <c:if test="${count > 0}">
  <table> 
    <tr style="background-color:#d7d1d1;" class="title"> 
      <td align="center" >번호</td> 
      <td align="center" >분류</td> 
      <td align="center" >제목</td>
      <td align="center" >가격</td> 
      <td align="center" >수량</td> 
      <td align="center" >이미지</td>
      <td align="center" >할인율</td>
      <td align="center" width="140" >등록일</td>
      <td align="center"  width="50">수정</td>
      <td align="center"  width="50">삭제</td>           
    </tr>

   <c:set var="number" value="${0}"/>
   <c:forEach var="item" items="${itemList}">
   <tr>
    <td align="center"   >
      <c:set var="number" value="${number+1}"/>
	  <c:out value="${number}"/>
	</td>
    <td width="30">${item.getItem_kind()}</td> 
      <td align="left">
           ${item.getItem_name()}</td>
      <td width="50" align="right">${item.getItem_price()}</td> 
      <td width="50" align="right">
      <c:if test="${item.getItem_count() == 0}">
         <font color="red">일시품절</font>
      </c:if>
      <c:if test="${item.getItem_count() > 0}">
         ${item.getItem_count()}개 &nbsp;
      </c:if>
      </td> 
     
      <td width="50">${item.getItem_image()}</td>
      <td width="50">${item.getDiscount_rate()}</td>
      <td width="50">${item.getReg_date()}</td>
      <%--
       <td width="50"><fmt:formatDate pattern="yyyy-MM-dd" value="${item.getReg_date()}"/></td>
       --%>
     
      <td width="50">
      <button id="edit" name="${item.getItem_id()},${item.getItem_kind()}" onclick="edit(this)">수정</button></td>
	  <td width="50">
	  <button id="delete" name="${item.getItem_id()},${item.getItem_kind()}" onclick="del(this)">삭제</button></td> 
   </tr>
  </c:forEach>
</table>
</c:if>
</div>