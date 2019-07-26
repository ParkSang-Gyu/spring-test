<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">
	<script type="text/javascript" src="//code.jquery.com/jquery-3.4.1.js"></script>	
	<title>회원정보 수정</title>
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
	function checkSame(sel1, sel2){
		var val1 = $(sel1).val();
		var val2 = $(sel2).val();
		if(val1 == val2)
			return true;
		return false;
	}
	function checkLength(sel,min,max){
		var val = $(sel).val();
		if(val.length >= min && val.length <= max )
			return true;
		return false;
	}
	$(document).ready(function(){
		$('#modify').submit(function(){
			if(!checkLength('input[name=oldPw]',8,13)){
				alert('기존 비밀번호를 알맞게 입력하세요.');
				return false;
			}
			//checkLength('input[name=pw]',8,13) : 새비밀번호를 입력했다면 8~13자리이면 회원 정보 수정해야함
			//$('input[name=pw]').val().length==0 : 새비밀번호를 입력하지 않았다면 회원 정보 수정해야함
			if(!(checkLength('input[name=pw]',8,13)||$('input[name=pw]').val().length==0)){
				alert('새비밀번호로 수정하려면 8~13자리를 입력하거나 비밀번호를 수정하지 않으려면 새비밀번호를 입력하지 마세요.')
				return false;
			}
			if(!checkSame('input[name=pw]','input[name=pw2]')){
				alert('비밀번호와 일치하지 않습니다.')
				return false;
			}
			if($('input[type=email]').val().length == 0){
				alert('이메일을 입력해주세요.')
				return false;
			}
			alert('회원정보 수정을 요청중입니다.');
			return true;
		})	
		if(${success ne null}){
			if(${success eq true}){
				alert('수정이 완료되었습니다.');
			}
			else{
				alert('기존 비밀번호를 잘못 입력하였습니다.');
			}
		}
		$('body').keydown(function(e){
			// f5의 키 값이 116
			if(e.which == 116){
				location.href = "<%=request.getContextPath()%>/member/modify";
			}
		})
	})
		
	</script>
</head>
<body>
	<div>
		<div class="offset-4 col-4 border border-dark mt-5">
			<h1 class="text-center">회원정보 수정</h1>
			<form method="post" action="<%=request.getContextPath()%>/member/modify" id="modify">
				<div class="row">
					<label class="col-4">아이디</label>
					<input type="text"class="form-control col-7" placeholder="아이디" name="id" value="${user.id}" readonly>
				</div>
				<div class="row">
					<label class="col-4">기존비밀번호</label>
					<input type="password"class="form-control col-7" placeholder="기존비밀번호" name="oldPw">
				</div>
				<div class="row">
					<label class="col-4">새비밀번호</label>
					<input type="password"class="form-control col-7" placeholder="새비밀번호" name="pw">
				</div>
				<div class="row">
					<label class="col-4">새비밀번호확인</label>
					<input type="password"class="form-control col-7" placeholder="새비밀번호확인" name="pw2">
				</div>
				<div class="row">
					<label class="col-4">성별</label>
					<div class="col-8">
						<label class="form-check-label col-5">
							<input type="radio" class="form-check-input" name="gender" value="M" <c:if test="${user.gender == 'M'}">checked</c:if> >남성
						</label>
						<label class="form-check-label">
							<input type="radio" class="form-check-input" name="gender" value="F" <c:if test="${user.gender == 'F'}">checked</c:if> >여성
						</label>
					</div>
				</div>
				<div class="row">
					<label class="col-4">이메일</label>
					<input type="email"class="form-control col-7" placeholder="이메일" name="email" value="${user.email}">
				</div>
				<div class="row">
					<label class="col-4">이름</label>
					<input type="text"class="form-control col-7" placeholder="이름" name="name" value="${user.name}">
				</div>
				<div class="offset-8 col-3 clearfix p-0">
					<button class="btn btn-primary float-right">가입</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>