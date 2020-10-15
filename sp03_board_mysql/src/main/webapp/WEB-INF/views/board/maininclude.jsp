<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 인크루드</title>
<script type="text/javascript">
		$(function() {
			$('#boardList').on('click', function() {
				$(location).attr('href', '${path}/board/list');
			});
			$('#company').on('click', function() {
				$(location).attr('href', '${path}/board/company');
			});
			$('#memberUpdate').on('click', function(){
				$(location).attr('href', '${path}/member/modify');
			});
			$('#logout').on('click', function(){
				$(location).attr('href', '${path}/login/logout');
			});
			//세션삭제
			$('#btnSessionDelete').on('click', function() {
				$(location).attr('href','${path}/board/sessionDelete');
			});
		});
	</script> 
</head>
<body>
		<button class="btn btn-light" id="boardList">게시판</button>
		<button class="btn btn-light" id="memberUpdate">회원정보수정</button>
		<button class="btn btn-light" id="logout">로그아웃</button>
		<button class="btn btn-light" id="company">지도</button>
		<input id="btnSessionDelete" type="button" value="메인화면으로" class="btn btn-light">		
</body>
</html>