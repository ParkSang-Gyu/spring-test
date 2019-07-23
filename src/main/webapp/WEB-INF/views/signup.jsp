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
	<title>회원가입</title>
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
	<script type="text/javascript">
		function checkSame(sel1,sel2){
			var val1 = $(sel1).val();
			var val2 = $(sel2).val();
			if(val1 == val2)
				return true;
			return false;
		}
		function checkLength(sel,min,max){
			var val = $(sel).val();
			if(val.length >= min && val.length <= max)
				return true;
			return false;
		}
		
		$(document).ready(function(){
			$('signup').submit(function(){
				if(!checkLength('#signup input[name=id]',8,13)){
					alert('아이디는 8~13자리입니다.')
					return false;
				}
				if(!checkLength('#signup input[name=pw]',8,13)){
					alert('비밀번호는 8~13자리입니다.')
					return false;
				}
				if(!checkSame('#signup input[name=pw]','#signup input[name=pw2]')){
					alert('비밀번호가 맞지 않습니다.')
					return false;
				}
				if($('signup input[type=email]').val().length == 0){
					alert('이메일을 입력해주세요.')
					return false;
				}
				alert('회원가입에 성공했습니다.')
				return true;
			})
		})
	</script>
</head>
<body>
	<div>
		<div class="offset-4 col-4 border border-dark mt-5">
			<h1 class="text-center">회원가입</h1>
			<form method="post" action="<%=request.getContextPath()%>/signup" id="signup">
				<div class="row">
					<label class="col-4">아이디</label>
					<input name="id" type="text"class="form-control col-7" placeholder="아이디">
				</div>
				<div>
					<button type="button"class="btn btn-outline-success offset-4 col-7" id="dup">아이디 중복확인</button>
				</div>
				<div class="row">
					<label class="col-4">비밀번호</label>
					<input name="pw" type="password"class="form-control col-7" placeholder="비밀번호">
				</div>
				<div class="row">
					<label class="col-4">비밀번호확인</label>
					<input name="pw2" type="password"class="form-control col-7" placeholder="비밀번호확인">
				</div>
				<div class="row">
					<label class="col-4">성별</label>
					<div class="col-8">
						<label class="form-check-label col-5">
							<input name="gender" value="M" type="radio" class="form-check-input" checked>남성
						</label>
						<label class="form-check-label">
							<input name="gender" value="F" type="radio" class="form-check-input" >여성
						</label>
					</div>
				</div>
				<div class="row">
					<label class="col-4">이메일</label>
					<input name="email" type="email"class="form-control col-7" placeholder="이메일">
				</div>
				<div class="row">
					<label class="col-4">이름</label>
					<input name="name" type="text"class="form-control col-7" placeholder="이름">
				</div>
				<div class="offset-8 col-3 clearfix p-0">
					<button class="btn btn-primary float-right" id="register">가입</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>