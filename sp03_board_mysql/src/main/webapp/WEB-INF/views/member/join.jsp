<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script>
	function joinCheck() {
		var userid = joinForm.userid.value;
		var passwd = joinForm.passwd.value;
		var email = joinForm.email.value;
		if (userid == ''){
			alert('아이디를 입력해 주세요');
			joinForm.userid.focus();
		}else if (passwd == ''){
			alert('패스워드를 입력해 주세요');
			joinForm.passwd.focus();
		}else if (email == ''){
			alert('이메일를 입력해 주세요');
			joinForm.email.focus();
		}else{
			joinForm.submit();	
		}		
	}

	$(function() {
		$('#gomain').on('click', function(e){
			e.preventDefault;
			$(location).attr('href', '${path}/login/');
		});
	});
	
</script>
</head>
	<h2 class="alert alert-info">회원가입</h2>
	<div class="container">
	<form name="joinForm" action="${path}/member/join" method="post" enctype="multipart/form-data">
		<label>아이디 : </label>
		<input type="text" class="form-control" style="width: 50%" name="userid"><br>
		<label>비밀번호 : </label>
		<input type="password" class="form-control" style="width: 50%" name="passwd"><br>
		<label>이름 : </label>
		<input type="text" class="form-control" style="width: 50%" name="name"><br>
		<label>이메일 : </label>
		<input type="email" class="form-control" style="width: 50%" name="email"><br>
		<label>프로필사진 : </label>
		<input type="file" class="form-control-file border" style="width: 50%"  name="photofile"><br>
		<input type="hidden" name="joinflag" value="0">
		<input type="button" class="btn btn-success" value="가입" onclick="joinCheck()">
		<input type="button" class="btn btn-success" value="취소" id="gomain">
	</form>	
	</div>
	
</body>
</html>
