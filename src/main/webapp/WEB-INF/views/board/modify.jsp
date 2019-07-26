<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>게시글 상세</title>
</head>
<body>
	<div class="container">
		<h1 class="mt-5">게시판 상세</h1>
		<form method="post" action="<%=request.getContextPath()%>/board/modify">
			<input type="hidden" name="num" value="${board.num}">
			<div class="form-group">
			  <label>제목:</label>
			  <input type="text" class="form-control" name="title" value="${board.title }">
			</div>
			<div class="form-group">
			  <label>작성자:</label>
			  <input type="text" class="form-control" name="writer" value="${board.writer }">
			</div>
			<div class="form-group">
			  <label>작성일</label>
			  <input type="text" class="form-control" name="registered" value="${board.registered }">
			</div>
			<div class="form-group">
			  <label>조회수:</label>
			  <input type="text" class="form-control" name="views" value="${board.views}">
			</div>
			<div class="form-group">
			  <label>내용:</label>
			  <textarea rows="5" cols="" class="form-control" name="contents">${board.contents }</textarea>
			</div>
			<a href="<%=request.getContextPath()%>/board/display?num=${board.num}" class="float-right">
				<button type="button" class="btn btn-outline-dark ">취소</button>
			</a>
			<button class="btn btn-outline-dark float-right mr-2">수정하기</button>
		</form>
	</div>
	
</body>
</html>