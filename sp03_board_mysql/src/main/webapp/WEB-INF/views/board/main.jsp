<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
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
		});
	</script> 
<!-- <style>
	img {
	  margin: 0 auto;
	  display: block;
	  margin-top: 20%;
	}
</style> -->
</head>
<body>
	<h2 class="alert alert-info">메인화면</h2>
	
	<div class="container">
		<button class="btn btn-light" id="boardList">게시판</button>
		<button class="btn btn-light" id="memberUpdate">회원정보수정</button>
		<button class="btn btn-light" id="logout">로그아웃</button>
		<button class="btn btn-light" id="company">지도</button>
	</div>
	
	<br>
	<div class="container">
		<input type="button" value="토글" style="border: none; background: transparent;">
		<!-- servlet-context.xml 에 이미지 경로 매핑 -->
		<img alt="메인이미지" id="introimg" src="${path}/img/three.gif" width="430px">
		<input type="button" value="토글" style="border: none; background: transparent;">
		<%-- <img alt="메인이미지" src="${path}/resources/images/1.gif" width="100px"> --%>
<%-- 		<img alt="메인이미지" src="${path}/img/1.gif" width="100px">
		<img alt="메인이미지" src="${path}/localimg/1.gif" width="100px">
		<!-- servet.xml에 이미지 경로 매핑 -->
		<img alt="메인이미지" src="/images/1.gif" width="100px"> --%>
	</div>
</body>
</html>
