<%-- 02_managerMain --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<script src="http://localhost:8080/stylenandari/js/jquery-1.11.0.min.js"></script>
<script src="http://localhost:8080/stylenandari/01_managermain.js?v=<%=System.currentTimeMillis() %>"></script>
<h2>매니저 전용 페이지 입니다.</h2> <hr/>
<% System.out.println("5"); %>
<c:if test="${empty sessionScope.id}">
  <div id="mList"><p>매니저 로그인 하세요.
  </div>
</c:if>
<c:if test="${!empty sessionScope.id}">
  <div id="mList">
  	<h3>상품관련 작업</h3>
  	<button id="registProduct">상품등록</button>
  	<button id="updateProduct">상품수정/삭제</button>
    <hr/>
    <h3>구매된 상품관련 작업</h3>
    <button id="orderedProduct">전체구매목록 확인</button>
    
    <!--  <ul>
        <li>상품 QnA 작업
        <li><button id="qna" >상품 QnA답변</button>
     </ul> -->
    
  </div>
</c:if>    