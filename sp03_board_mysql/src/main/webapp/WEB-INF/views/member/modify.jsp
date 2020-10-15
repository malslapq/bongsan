<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보</title>
<script type="text/javascript">
//메시지 처리
//패스워드변경 결과 처리
//수정시 체크
function modifyCheck() {
	var userid = modifyForm.userid.value;
	var oldpasswd = modifyForm.oldpasswd.value;
	var email = modifyForm.email.value;
	if (userid == ''){
		alert('아이디를 입력해 주세요!');
		modifyForm.userid.focus();
	}else if (oldpasswd == ''){
		alert('패스워드를 입력해 주세요!');
		modifyForm.oldpasswd.focus();
	}else if (email == ''){
		alert('이메일를 입력해 주세요!');
		modifyForm.email.focus();
	}else modifyForm.submit();
	
	
}

//삭제시 체크
function deleteCheck() {
	var userid = modifyForm.userid.value;
	var oldpasswd = modifyForm.oldpasswd.value;
	var email = modifyForm.email.value;
	if (userid == ''){
		alert('아이디를 입력해 주세요!');
		modifyForm.userid.focus();
	}else if (oldpasswd == ''){
		alert('패스워드를 입력해 주세요!');
		modifyForm.oldpasswd.focus();
	}else{
		modifyForm.action = "/mylogin/member/delete";
		modifyForm.submit();	
	}		
	
}


//패스워드 변경
function pwchange() {
	var oldpasswd = modifyForm.oldpasswd.value;
	var passwd = modifyForm.passwd.value;
	if (oldpasswd ==''){
		alert("현재 패스워드를 입력해 주세요!");
		modifyForm.oldpasswd.focus();
	}else if (passwd == ''){
		alert("변경할 패스워드를 입력해 주세요!");
		modifyForm.passwd.focus();
	} else{
		modifyForm.action = "/mylogin/member/pwUpdate";
		modifyForm.submit();
	}
		
}

	$(function(){
		$('#mainbtn').on('click', function(e){
			e.preventDefault();
			$(location).attr('href', '${path}/board/');
		});
	});

</script>
</head>
<body>
	<h2 class="alert alert-success">회원정보수정</h2>
	<div class="container">
	<%@ include file="../board/maininclude.jsp" %>
		<form name="modifyForm" action="${path}/member/modify" 
				method="post" enctype="multipart/form-data">
			<br>
			<label>아이디 : </label>
			<input type="text" class="form-control" style="width: 50%" name="userid" value="${dto.userid}"> <br>
			<c:if test="${dto.joinflag != 1}">
			<label>비밀번호 : </label> 
			<input type="password" class="form-control" style="width: 50%" name="oldpasswd" size="4"><br>
			<label>변경할비밀번호 : </label>
			<input type="password" class="form-control" style="width: 50%" name="passwd" size="4" >		
			<input type="button" class="btn btn-dark" value="비밀번호변경" onclick="pwchange()"><br><br>
			</c:if>
			<label>이메일 : </label>
			<input type="email" class="form-control" style="width: 50%" name="email" value="${dto.email}"><br>
			<!-- /images 경로 설정은 server.xml에서 -->
			<label>프로필 사진 : </label> 
			<div><img src="<spring:url value='/localimg/${dto.filename}'/>"  class="img-thumbnail" width="100px">
			<input type="text" class="form-control" style="width: 50%" name="filename" value="${dto.filename}" readonly>
			<input type="file" class="form-control-file border" style="width: 50%" name="photofile">
			</div>	
			<input type="button" class="btn btn-success" value="수정" onclick="modifyCheck()">
			<input type="button" class="btn btn-danger" value="탈퇴" onclick="deleteCheck()">
			<input type="button" class="btn btn-dark" value="메인으로" id="mainbtn">
		</form>	
	</div>
	
</body>
</html>
