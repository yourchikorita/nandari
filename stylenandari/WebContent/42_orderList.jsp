<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="http://localhost:8080/stylenandari/css/style.css"/>
<script src="http://localhost:8080/stylenandari/js/jquery-1.11.0.min.js"></script>
<script src="http://localhost:8080/stylenandari/42_orderlist.js"></script>

<c:if test="${empty sessionScope.id}">
  <meta http-equiv="Refresh" content="0;url=managerMain.do" >
</c:if>

<div id="listHeader">
  <p>주문 목록(전체 주문:${count})
  <button id="itemMain">관리자 메인으로</button>
</div>

<div id="orders">
  <c:if test="${count == 0}">
    <ul>
      <li>주문 목록이 없습니다.
    </ul>
  </c:if>
  
  <c:if test="${count > 0}">
  <table style="text-align:center;"> 
    <tr style="background-color:pink;" class="title">  
      <td>주문번호</td> 
      <td>주문자</td> 
      <td>상품명</td>
      <td>가격</td> 
      <td>주문수량</td>
      <td>금액</td>
      <td>주문일</td>
      <td>결제계좌</td>
      <td>배송명</td>
      <td>배송지전화</td>
      <td>배송지주소</td>
      <td>배송지상황</td>     
    </tr>
   <c:set var="total" value="0"/>
   <c:forEach var="i" begin="0" end="${buyLists.size()-1}">
    <c:set var="buyList" value="${buyLists.get(i)}"/>
    <c:set var="pid" value="${buyList.getBuy_id()}"/>
    <c:if test="${i+1 > buyLists.size()-1 }">
      <c:set var="nid" value="0"/>
    </c:if>
    <c:if test="${i+1 <= buyLists.size()-1 }">
      <c:set var="nid" value="${buyLists.get(i+1).getBuy_id()}"/>
    </c:if>
   <tr style="background-color:#80808029;"> 
      <td style=" border:solid pink;">${buyList.getBuy_id()}</td> 
      <td  style=" border:solid pink;" >${buyList.getBuyer()}</td> 
      <td style=" border:solid pink;" >${buyList.getItem_name()}</td>
      <td style=" border:solid pink;"  ><fmt:formatNumber value="${buyList.getBuy_price()}" type="number" pattern="#,##0"/>원</td> 
      <td style=" border:solid pink;"  >${buyList.getBuy_count()}</td>
      <td style=" border:solid pink;"  ><c:set var="amount" value="${buyList.getBuy_count()*buyList.getBuy_price()}"/>
         <c:set var="total" value="${total+amount}"/>
         <fmt:formatNumber value="${amount}" type="number" pattern="#,##0"/>원</td>
      <td style=" border:solid pink;"  >${buyList.getBuy_date().toString()}</td>
      <td style=" border:solid pink;" >${buyList.getAccount()}</td>
      <td style=" border:solid pink;" width="120" >${buyList.getDeliveryName()}</td>
      <td style=" border:solid pink;" >${buyList.getDeliveryTel()}</td>
      <td style=" border:solid pink;" >${buyList.getDeliveryAddress()}</td>
      <td style=" border:solid pink;" >${buyList.getSanction()}</td>
    </tr>
    
   <c:if test="${pid != nid}">
    <tr><td style="text-align:right;font-size:20px;color:red;" colspan="12" class="b">주문금액 : 
      <fmt:formatNumber value="${total}" type="number" pattern="#,##0"/>원</td></tr>
    <c:set var="total" value="0"/>
    <c:set var="pid" value="${nid}"/>
  </c:if>
  </c:forEach>
</table>
</c:if>
</div>