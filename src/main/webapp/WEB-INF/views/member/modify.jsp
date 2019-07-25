<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<!-- 
		여러개의 css를 가져올 때 link 순서 
		일반적인 css(전체) >> 현재 페이지와 관련된 css(일부)순으로 나열하면 된다.
	 -->
	<title>회원정보수정</title>
	<style>
	*{
		margin: 0;
		padding : 0;
	}
	.main{
		margin-top:50px;
	}
	.row{
		margin: 5px 0px;
	}
	.fab.fa-amazon{
		font-size: 100px;
		color: red;
	}
	</style>
	
</head>
<body>
	<div>
		<div class="offset-4 col-4 border border-dark mt-5">
			<h1 class="text-center">회원정보수정</h1>
			<form method="post" action="">
				<div class="row">
					<label class="col-4">아이디</label>
					<input name="id" type="text"class="form-control col-7" value="${user.id }" placeholder="아이디">
				</div>
				<div class="row">
					<label class="col-4">비밀번호</label>
					<input name="oldPw" type="password"class="form-control col-7" value="${user.pw }" placeholder="비밀번호">
				</div>
				<div class="row">
					<label class="col-4">새 비밀번호</label>
					<input name="pw" type="password"class="form-control col-7" placeholder="새 비밀번호">
				</div>
				<div class="row">
					<label class="col-4">새 비밀번호확인</label>
					<input name="pw3" type="password"class="form-control col-7" placeholder="새 비밀번호확인">
				</div>
				<div class="row">
					<label class="col-4">성별</label>
					<div class="col-8">
						<label class="form-check-label col-5">
							<input name="gender" value="M" type="radio" class="form-check-input" >남성
						</label>
						<label class="form-check-label">
							<input name="gender" value="F" type="radio" class="form-check-input" >여성
						</label>
					</div>
				</div>
				<div class="row">
					<label class="col-4">이메일</label>
					<input name="email" type="email"class="form-control col-7" value="${user.email }" placeholder="이메일">
				</div>
				<div class="offset-8 col-3 clearfix p-0">
					<button class="btn btn-primary float-right">수정</button>	
				</div>
			</form>
		</div>
	</div>
</body>
</html>