<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="/shoppingmall/css/style.css"/>
<script src="js/jquery-1.11.0.min.js"></script>
<script src="23_itemcontent.js"></script>


<div id="showItem">
<h4>상품 상세 페이지</h4>
<br/>
  <table > 
   <tr> 
     <td rowspan="7">
        <img style="padding-right:30px;" src="images/${item.getItem_image()}" ></td> 
     <td >
       <b>${item.getItem_name()}</b></td>
   </tr> 
   <tr> 
     <td >
       <span>${item.getItem_summary()}</span>
     </td>
   </tr>
   <tr> 
     <td >
       남은 수량 :<span> ${item.getItem_count()}개</span>
     </td>
    </tr> 
    <tr>
     <td >
        <c:if test="${!empty sessionScope.id}">
	        <c:if test="${item.getItem_count()==0}">
	         <p>일시품절
	        </c:if>
		    <c:if test="${item.getItem_count()>=1}">
		         수량 선택 : <input type="text" size="5" id="buy_count" value="1"> 개
		    </c:if>
  	   </c:if>
     </td>
    </tr> 
   
  <tr>
  	<td>
  		 <c:set var="price" value="${item.getItem_price()}"/>
         <c:set var="rate" value="${item.getDiscount_rate()}"/>
         정가 : <fmt:formatNumber value="${price}" type="number" pattern="#,##0"/>원<br>
    	할인 : ${rate}% <br/>    
         <strong style="color:red;font-size:20px;" class="bred">판매가 :<c:set var="rPrice" value="${price*((100.0-rate)/100)}"/>
         <fmt:formatNumber value="${rPrice}" type="number" pattern="#,##0"/>원</strong>
    </td>
  </tr>
  <tr>
  	<td>
  	
  	 <c:if test="${!empty sessionScope.id}">
       <input type="hidden" id="item_id" value="${item_id}">
       <input type="hidden" id="item_image" value="${item.getItem_image()}">
       <input type="hidden" id="item_name" value="${item.getItem_name()}">
       <input type="hidden" id="buy_price" value="${rPrice}">
       <input type="hidden" id="item_kind" value="${item_kind}">
       <input type="hidden" id="buyer" value="${sessionScope.id}">              
       <button class="btn btn-outline-secondary" id="insertCart"  class="addingCart" class="btn btn-outline-secondary" id="insertCart">장바구니에 담기</button>
     </c:if>
     
     <c:if test="${empty sessionScope.id}">
       <c:if test="${item.getItem_count()==0}">
         <p>일시품절
       </c:if>
       <p>제품을 구매하시려면 로그인 하세요.
     </c:if>
   
   </td>
  </tr>  
  <tr>
  	<td>
     <button class="btn btn-outline-secondary"  id="list">목록으로</button>
     <button class="btn btn-outline-secondary"  id="shopMain">메인으로</button>
   </td>
  </tr>  
      
   
</table>
</div>