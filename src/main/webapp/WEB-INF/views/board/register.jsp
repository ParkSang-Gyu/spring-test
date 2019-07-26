<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>게시글 등록</title>
</head>
<body>
	<div class="container">
		<h1 class="mt-5">게시글 등록</h1>
		<form method="post" action="<%=request.getContextPath()%>/board/register">
			<div class="form-group">
			  <label>제목:</label>
			  <input type="text" class="form-control" name="title" value="">
			</div>
			<div class="form-group">
			  <label>작성자:</label>
			  <input type="text" class="form-control" name="writer" value="${user.id}">
			</div>
			<div class="form-group">
			  <label>내용:</label>
			  <textarea rows="5" cols="" class="form-control" name="contents">${board.contents }</textarea>
			</div>
			<a href="<%=request.getContextPath()%>/board/list" class="float-left">
				<button class="btn btn-outline-dark">목록</button>
			</a>
			
			<button class="btn btn-outline-dark float-right">등록 하기</button>
			
		</form>
	</div>
	
</body>
</html>