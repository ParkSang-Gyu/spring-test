<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<title>메인</title>
</head>
<body>

<div class="offset-4 col-4 mt-5">
	<h1>	로그인	</h1>
	<form method="post">
		<div class="form-group">
		  <label>ID:</label>
		  <input type="text" class="form-control" name="id">
		</div>
		<div class="form-group">
		  <label >PW:</label>
		  <input type="password" class="form-control" name="pw">
		</div>
		<button class="btn btn-outline-dark float-right">로그인</button>
		<a href="<%=request.getContextPath()%>/password/find">비밀번호 찾기</a>
	</form>
</div>
</body>
</html>