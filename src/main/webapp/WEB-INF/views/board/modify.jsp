<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/navi.jsp"></jsp:include>
	<form method="post" action="<%=request.getContextPath()%>/board/modify">
		<input type="hidden" name="num" value="${board.num}">
		<div class="container-fluid" style="margin-top:20px;"></div>
	
		<div class="form-group">
		  <label>제목</label>
		  <input type="text" class="form-control" name="title" value="${board.title}">
		</div>
		<div class="form-group">
		  <label>작성자</label>
		  <input type="text" class="form-control" name="writer" value="${board.writer}" readonly>
		</div>
		<div class="form-group">
		  <label>등록일</label>
		  <input type="text" class="form-control" name="registered" value="${board.registered}" readonly>
		</div>
		<div class="form-group">
		  <label>조회수</label>
		  <input type="text" class="form-control" name="views" value="${board.views}" readonly>
		</div>
		<div class="form-group">
		  <label>내용</label>
		  <textarea rows="10" cols="" class="form-control" name="contents">${board.contents}</textarea>
		</div>
		<div class="form-group">
		  <label>첨부파일</label>
		  <input type="text" class="form-control" name="file" value="${board.file}">
		</div>
		<a href="<%=request.getContextPath()%>/board/list">
			<button type="button" class="btn btn-primary">목록</button></a>
		<button type="submit" class="btn btn-primary">확인</button>
	</form>
</body>
</html>