<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<meta charset="UTF-8">
	<title>게시판</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/navi.jsp"></jsp:include>
	<h1>게시판</h1>
	<select name="count" class="float-right">
		<option value="10">10개씩</option>
		<option value="15">15개씩</option>
		<option value="20">20개씩</option>
		<option value="25">25개씩</option>
	</select>
	<table class="table">
		<tr>
			<th width="5%">번호</th>
			<th width="55%">제목</th>
			<th width="10%">작성자</th>
			<th width="20%">등록일</th>
			<th width="10%">조회수</th>
		</tr>
		<c:forEach var="board" items="${list}">
			<tr>
				<th>${board.num}</th>
				<th><a href="<%=request.getContextPath()%>/board/display?num=${board.num}">${board.title}</a></th>
				<th>${board.writer}</th>
				<th>${board.registered}</th>
				<th>${board.views}</th>
			</tr>
		</c:forEach>
	</table>
	<a href="<%=request.getContextPath()%>/board/register">
		<button>글쓰기</button></a>
</body>
</html>