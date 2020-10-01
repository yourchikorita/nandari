<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="css/style.css"/>
<script src="js/jquery-1.11.0.min.js"></script>
<script src="12_login.js"></script>
<div id="topLogin">
<c:if test="${empty sessionScope.id}">
  <div id="lStatus">
     <ul>
        <li><label for="cid">아이디</label>
            <input id="cid" name="cid" type="email" size="20" 
              maxlength="50" placeholder="example@kings.com">
            <label for="cpasswd">비밀번호</label>
            <input id="cpasswd" name="cpasswd" type="password" 
              size="20" placeholder="비밀번호 입력" maxlength="16">
            <button id="uLogin">로그인</button>
            <button id="uRes">회원가입</button>
     </ul>
  </div>
</c:if>
<c:if test="${!empty sessionScope.id}">
  <div id="lStatus">
     <ul>
        <li style="margin:10px; list-style:none;"><span style="font-size:15px;">${sessionScope.id}님이 로그인 하셨습니다.</span></li>
        <li style=list-style:none;">   <div id="info">
             <table>
               <tr >
                 <td><input id="uLogout" type="button" value="로그아웃"></td>
                 <td><input id="uUpdate" type="button" value="회원 정보 변경"></td>
                
                 <td>
                   <form style="margin:0;" id="cartForm" method="post" action="cartList.do">
	                   <input type="hidden" name="buyer" value="${sessionScope.id}">
	                   <input type="submit" name="cart" value="장바구니가기">
                   </form>
                 </td>
                 <td>
	                 <form style="margin:0;" id="buyForm" method="post" action="/stylenandari/buyList.do">
		                   <input type="hidden" name="buyer" value="${sessionScope.id}">
		                   <input type="submit" name="buy" value="구매내역">
	                 </form>
                 </td>
                 </tr>
             </table>
        </div>     
            </li>
     </ul>
  </div>
</c:if> 
</div>