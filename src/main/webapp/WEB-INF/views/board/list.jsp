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
	<table>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>내용</th>
			<th>작성자</th>
			<th>등록일</th>
			<th>파일</th>
			<th>조회수</th>
		</tr>
	<c:forEach var="board" items="${list}">
		<tr>
			<th>${board.num }</th>
			<th><a href="<%=request.getContextPath()%>/board/display?num=${board.num}">${board.title }</a></th>
			<th>${board.contents }</th>
			<th>${board.writer }</th>
			<th>${board.registered }</th>
			<th>${board.file }</th>
			<th>${board.views }</th>
		</tr>
	</c:forEach>
	</table>
</body>
</html>