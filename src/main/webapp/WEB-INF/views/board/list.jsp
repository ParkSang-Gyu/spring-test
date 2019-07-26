<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script type="text/javascript" src="//code.jquery.com/jquery-3.4.1.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#perPageNum').change(function(){
			var search = $('input[name=search]').val();
			var type = $('select[name=type]').val();
			location.href 
				= '<%=request.getContextPath()%>/board/list?perPageNum='+$(this).val()+'&type='+ type +'&search='+ search;
		})
	})
</script>
<title>게시판 리스트</title>
</head>
<body>
	<div class="container">
		<c:if test="${user ne null}">
			<a href="<%=request.getContextPath()%>/member/modify" class="float-right">회원정보수정</a>
		</c:if>
		<h1 class="mt-5 mb-4">게시판 리스트</h1>
		
		<div class="form-group col-3 float-right">
		  <select class="form-control" id="perPageNum">
		    <option <c:if test="${pageMaker.criteria.perPageNum == 5}">selected</c:if> >5</option>
		    <option <c:if test="${pageMaker.criteria.perPageNum == 10}">selected</c:if> >10</option>
		    <option <c:if test="${pageMaker.criteria.perPageNum == 15}">selected</c:if> >15</option>
		    <option <c:if test="${pageMaker.criteria.perPageNum == 20}">selected</c:if> >20</option>
		  </select>
		</div>
		
		<table class="table table-hover">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			<c:forEach var="tmp" items="${list}">
			<tr>
				<th>${tmp.num }</th>
				<th><a href="<%=request.getContextPath()%>/board/display?num=${tmp.num}&page=${pageMaker.criteria.page}&perPageNum=${pageMaker.criteria.perPageNum}&type=${pageMaker.criteria.type}&search=${pageMaker.criteria.search}">${tmp.title }</a></th>
				<th>${tmp.writer }</th>
				<th>${tmp.registered }</th>
				<th>${tmp.views }</th>
			</tr>
			</c:forEach>
		</table>
		
		<ul class="pagination" style="justify-content: center;">
	    <c:if test="${pageMaker.prev}">
	        <li class="page-item">
	            <a class="page-link" href="<%=request.getContextPath()%>/board/list?page=${pageMaker.startPage-1}&perPageNum=${pageMaker.criteria.perPageNum}&type=${pageMaker.criteria.type}&search=${pageMaker.criteria.search}">Previous</a>
	        </li>
	    </c:if>
	    <c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage}" var="index">
	    	<c:if test="${pageMaker.criteria.page == index }">
	        <li class="page-item active">
	            <a class="page-link " href="<%=request.getContextPath()%>/board/list?page=${index}&perPageNum=${pageMaker.criteria.perPageNum}&type=${pageMaker.criteria.type}&search=${pageMaker.criteria.search}">${index}</a>
	        </li>
        </c:if>
        <c:if test="${pageMaker.criteria.page != index }">
	        <li class="page-item">
	            <a class="page-link " href="<%=request.getContextPath()%>/board/list?page=${index}&perPageNum=${pageMaker.criteria.perPageNum}&type=${pageMaker.criteria.type}&search=${pageMaker.criteria.search}">${index}</a>
	        </li>
        </c:if>
	    </c:forEach>
	    <c:if test="${pageMaker.next}">
	        <li class="page-item">
	            <a class="page-link" href="<%=request.getContextPath()%>/board/list?page=${pageMaker.endPage+1}&perPageNum=${pageMaker.criteria.perPageNum}&type=${pageMaker.criteria.type}&search=${pageMaker.criteria.search}">Next</a>
	        </li>
	    </c:if>
		</ul>
		
		<form action="<%=request.getContextPath()%>/board/list" method="get">
			<select name="type">
				<option value="1" <c:if test="${pageMaker.criteria.type == 1}">selected</c:if> >제목</option>
				<option value="2" <c:if test="${pageMaker.criteria.type == 2}">selected</c:if>>내용</option>
				<option value="3" <c:if test="${pageMaker.criteria.type == 3}">selected</c:if>>작성자</option>
			</select>
			<input name="search" type="text" value="${pageMaker.criteria.search}">
			<button>검색</button>
		</form>
		
		<a href="<%=request.getContextPath()%>/board/register" class="float-left ml-2">
			<button class="btn btn-outline-dark ">등록</button>
		</a>
	</div>
</body>
</html>