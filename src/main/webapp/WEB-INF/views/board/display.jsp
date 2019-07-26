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
		  <textarea rows="5" cols="" class="form-control">${board.contents }</textarea>
		</div>
		<a href="<%=request.getContextPath()%>/board/list?page=${cri.page}&perPageNum=${cri.perPageNum}&type=${cri.type}&search=${cri.search}" class="float-left">
			<button class="btn btn-outline-dark">목록</button>
		</a>
		<a href="<%=request.getContextPath()%>/board/register" class="float-left ml-2">
			<button class="btn btn-outline-dark ">등록</button>
		</a>
		<c:if test="${user ne null && user.id eq board.writer}">
			<a href="<%=request.getContextPath()%>/board/modify?num=${board.num}" class="float-left ml-2">
				<button class="btn btn-outline-dark ">수정</button>
			</a>
			<a href="<%=request.getContextPath()%>/board/delete?num=${board.num}" class="float-left ml-2">
				<button class="btn btn-outline-dark ">삭제</button>
			</a>
		</c:if>
	</div>
	
</body>
</html>