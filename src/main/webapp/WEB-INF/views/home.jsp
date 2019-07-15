<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<form method="get">
		<input type="text" name="id">
		<button>전송</button>
	</form>
	<h1>${user}</h1> 
</body>
</html>
