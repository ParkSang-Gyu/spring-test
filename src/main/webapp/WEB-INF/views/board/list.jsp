<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<meta charset="UTF-8">
	<script type="text/javascript">
		$(document).ready(function () {
			$('#perPageNum').change(function () {
				var search = $('input[name=search]').val();
				var type = $('select[name=type]').val(); 
				location.href = '<%=request.getContextPath()%>/board/list?perPageNum=' + $(this).val()
			})
		})
	</script>
	<title>게시판</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/navi.jsp"></jsp:include>
	<h1>게시판</h1>
	<select id="perPageNum" class="float-right"> 		
		<option <c:if test="${pageMaker.criteria.perPageNum == 5}">selected</c:if>>5</option>
		<option <c:if test="${pageMaker.criteria.perPageNum == 10}">selected</c:if>>10</option>
		<option <c:if test="${pageMaker.criteria.perPageNum == 15}">selected</c:if>>15</option>
		<option <c:if test="${pageMaker.criteria.perPageNum == 20}">selected</c:if>>20</option>
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
	<ul class="pagination" style="justify-content: center;">
	    <c:if test="${pageMaker.prev}">
	        <li class="page-item">
	            <a class="page-link" href="<%=request.getContextPath()%>/board/list?page=${pageMaker.startPage-1}&type=${pageMaker.criteria.type}&search=${pageMaker.criteria.search}&perPageNum=${pageMaker.criteria.perPageNum}">Previous</a>
	        </li>
	    </c:if>
	    <c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage}" var="index">
	        <c:if test="${pageMaker.criteria.page == index}">
		        <li class="page-item active">
		            <a class="page-link" href="<%=request.getContextPath()%>/board/list?page=${index}&type=${pageMaker.criteria.type}&search=${pageMaker.criteria.search}&perPageNum=${pageMaker.criteria.perPageNum}">${index}</a>
		        </li>
	        </c:if>
	        <c:if test="${pageMaker.criteria.page != index}">
		        <li class="page-item">
		            <a class="page-link" href="<%=request.getContextPath()%>/board/list?page=${index}&type=${pageMaker.criteria.type}&search=${pageMaker.criteria.search}&perPageNum=${pageMaker.criteria.perPageNum}">${index}</a>
		        </li>
	        </c:if>
	    </c:forEach>
	    <c:if test="${pageMaker.next}">
	        <li class="page-item">
	            <a class="page-link" href="<%=request.getContextPath()%>/board/list?page=${pageMaker.endPage+1}&type=${pageMaker.criteria.type}&search=${pageMaker.criteria.search}&perPageNum=${pageMaker.criteria.perPageNum}">Next</a>
	        </li>
	    </c:if>
	</ul>
        <form class="float-right" method="get" action="<%=request.getContextPath()%>/board/list">
			<select name="type" class="float-left">
				<option value="0">선택</option>
				<option value="1" <c:if test="${pageMaker.criteria.type eq 1}">selected</c:if>>제목</option>
				<option value="2" <c:if test="${pageMaker.criteria.type eq 2}">selected</c:if>>내용</option>
				<option value="3" <c:if test="${pageMaker.criteria.type eq 3}">selected</c:if>>작성자</option>
			</select>
			<input type="text" name="search" class="float-left" value="${pageMaker.criteria.search}">
			<button type="submit" class="btn btn-outline-success float-left">검색</button>
		</form>
	<a href="<%=request.getContextPath()%>/board/register">
		<button>글쓰기</button>
	</a>
	<c:if test="${user ne null}">
		<a href="<%=request.getContextPath()%>/member/modify">
			<button>회원정보수정</button>
		</a>
	</c:if>
</body>
</html>