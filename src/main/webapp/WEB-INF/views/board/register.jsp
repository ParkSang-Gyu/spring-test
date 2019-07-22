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
	<form action="<%=request.getContextPath() %>/board/register" method="post">
		<div class="container-fluid" style="margin-top:20px;"></div>
		<div class="form-group">
		  <label>제목</label>
		  <input type="text" class="form-control" name="title" value="">
		</div>
		<div class="form-group">
		  <label>작성자</label>
		  <input type="text" class="form-control" name="writer" value="${user.id}" readonly>
		</div>
		<div class="form-group">
		  <label>내용</label>
		  <textarea rows="10" cols="" class="form-control" name="contents"></textarea>
		</div>
		<div class="form-group">
		  <label>첨부파일</label>
		  <input type="text" class="form-control" name="file" value="">
		</div>
		<a href="<%=request.getContextPath()%>/board/list">
			<button type="button" class="btn btn-primary">목록</button></a>
		<a href="<%=request.getContextPath()%>/board/list">
			<button type="submit" class="btn btn-primary">등록</button></a>
	</form>
</body>
</html>