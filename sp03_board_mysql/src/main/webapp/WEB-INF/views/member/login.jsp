<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script type="text/javascript">
	//회원가입폼으로 이동
	function joinForm() {
		location.href = '${path}/member/join';
	}
	
</script>
</head>
<body>
	<h2 class="alert alert-success">로그인</h2>
	<div class="container">
		<form  name="loginForm" action="${path}/login/check" method="post">
			 <label>아이디 :</label>  <br>
			 <input class="form-control" type="text" name="userid" >
			<label>비밀번호 :</label>  <br>
			 <input class="form-control" type="password" name="passwd" ><br>
			<input class="btn btn-success" type="submit" value="로그인">
			<input class="btn btn-success" type="button" value="회원가입" onclick="joinForm()">
		</form>
		<a href="${apiURL}"><img height="50" src="http://static.nid.naver.com/oauth/small_g_in.PNG"/></a>
	</div>
</body>
</html>
