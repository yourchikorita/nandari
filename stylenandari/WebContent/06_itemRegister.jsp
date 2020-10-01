<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="http://localhost:8080/stylenandari/css/style.css"/>
<script src="http://localhost:8080/stylenandari/js/jquery-1.11.0.min.js"></script>
<script src="http://localhost:8080/stylenandari/js/jquery.form.min.js"></script>
<script src="http://localhost:8080/stylenandari/06_itemregist.js"></script>

<c:if test="${empty sessionScope.id}">
  <meta http-equiv="Refresh" content="0;url=managerMain.do" >
</c:if>

<div id="listHeader">
  <button id="itemMain">관리자 메인으로</button>
  <button id="itemList">목록으로</button>
</div>
<form id="upForm1" action="itemRegisterPro.do" 
          method="post" enctype="multipart/form-data">
<div id="itemRegistForm" class="box">
   <ul>
      <li><label for="item_kind">분류선택</label>
          <select id="item_kind" name="item_kind">
            <option value="100">top</option>
            <option value="200">dress</option>
            <option value="300">acc</option>
          </select></li>
      <li><label for="item_name">이름</label>
          <input id="item_name" name="item_name" type="text" 
           size="50" placeholder="제목" maxlength="50"></li>
      <li><label for="item_price">가격</label>
          <input id="item_price" name="item_price" type="text" 
           size="10" placeholder="가격" maxlength="9">원</li>
      <li><label for="item_count">수량</label>
          <input id="item_count" name="item_count" type="text" 
           size="10" placeholder="수량" maxlength="5">권</li>
     
      <li><label for="item_image">이미지</label>
          <input id="item_image" name="item_image" type="file">  </li>
      <li><label for="item_summary">정보</label>
          <textarea id="item_summary" name="item_summary" rows="13" cols="50"></textarea></li>
      <li><label for="discount_rate">할인율</label>
          <input id="discount_rate" name="discount_rate" type="text" 
           size="5" placeholder="10" maxlength="2">  </li>
      <li class="label2">
          <input type="submit" id="registBook" value="등록"></li>
   </ul>
</div>
</form>
