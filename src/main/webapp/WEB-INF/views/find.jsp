<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<script type="text/javascript" src="//code.jquery.com/jquery-3.4.1.js"></script>
	<script type="text/javascript">
		
		$(document).ready(function(){
				$('#pwFind').click(function(){
					var id = $('input[name=id]').val();
					var email = $('input[name=email]').val();
					if(id.length == 0 || email.length == 0){
						alert('아이디와 이메일을 입력해주세요.');
						return false;
					}
					var send = false;
					$.ajax({
		        async:false,
		        type:'POST',
		        data: {'id':id, 'email':email},
		        url:"<%=request.getContextPath()%>/checkemail",
		        dataType:"json",
		        contentType:"application/json; charset=UTF-8",
		        success : function(data){
		        	send = data.isMember;
	            if(!data.isMember){
	            	alert('회원 정보가 일치하지 않습니다.');
	            }else{
	            	alert('새비밀번호를 해당 메일로 전송했습니다.');
	            }
		        }
			    });
					return send;
				});
		})
	</script>
	<title>비밀번호 찾기</title>
</head>
<body>

<div class="offset-4 col-4 mt-5">
	<h1>	비밀번호 찾기	</h1>
	<form method="post" action="<%=request.getContextPath()%>/password/send" >
		<div class="form-group">
		  <label>ID:</label>
		  <input type="text" class="form-control" name="id">
		</div>
		<div class="form-group">
		  <label >EMAIL:</label>
		  <input type="email" class="form-control" name="email">
		</div>
		<button class="btn btn-outline-dark float-right" id="pwFind">비밀번호 찾기</button>
	</form>
</div>
</body>
</html>