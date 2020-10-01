<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="http://localhost:8080/stylenandari/css/style.css" />
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<div id="header">
		<c:if test="${type == 0}">
			<div id="logo" >
			<a href="managerMain.do"><img id="logo" src="../images/styleNandari2.png" /></a>
			</div>
		</c:if>
		<c:if test="${type == 1}">
			<div id="logo" >
			<a href="index.do"><img id="logo" src="images/styleNandari2.png" /></a>
			</div>
		</c:if>
	
	<div id="auth" >
		<c:if test="${type == 0}">
			<jsp:include page="02_managerLogin.jsp" />
		</c:if>
		<c:if test="${type == 1}">
			
			<jsp:include page="12_login.jsp"/>
		</c:if>
	</div>
	
	
	
	<nav class="navbar navbar-expand-lg navbar-light " style="background-color: #f1b9c752; ">
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="/stylenandari/list.do?item_kind=100">top <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/stylenandari/list.do?item_kind=200">dress</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/stylenandari/list.do?item_kind=300">acc</a>
      </li>
      <li class="nav-item">
        <a class="nav-link " href="/stylenandari/list.do?item_kind=all">전체보기</a>
      </li>
    </ul>
  </div>
</nav>
	
</div>


<div id="content" class="box2">
	<jsp:include page="${cont}" />
</div>

<div id="footer">
	<jsp:include page="43_footer.jsp"/>
</div>
<!--  <script src="http://code.jquery.com/jquery-1.11.1.min.js" type="text/javascript"></script> -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>


<!-- 
	
		<Resource auth="Container"
			driverClassName="com.mysql.cj.jdbc.Driver" loginTimeout="10"
			maxWait="5000" name="jdbc/pool" password="root"
			type="javax.sql.DataSource"
			url="jdbc:mysql://localhost:3306/stylenandari?serverTimezone=UTC"
			username="root" /> -->