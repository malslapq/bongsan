<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<head>
    <title>네이버로 간단 회원가입</title>
    <script>
	function joinCheck() {
		var photofile = joinForm.photofile.value;
		if (photofile == ''){
			alert('사진을 등록해 주세요!');
			joinForm.photofile.focus();
		}else{
			joinForm.submit();	
		}		
	}
</script>
</head>
<body>
	<h2>네이버 간단 회원가입</h2>
	<form name="joinForm" action="${path}/member/join" method="post" enctype="multipart/form-data">
		이름 : <input type="text" name="name" value="${mdto.name}" readonly="readonly"><br>
		이메일 : <input type="email" name="email" value="${mdto.email}" readonly="readonly"><br>
		프로필사진 : <input type="file" name="photofile"><br>
		<input type="hidden" name="userid" value="${mdto.userid}">
		<input type="hidden" name="passwd" value="naver">
		<input type="hidden" name="joinflag" value="1">
		<input type="button" value="가입" onclick="joinCheck()">
	</form>	
</html>
