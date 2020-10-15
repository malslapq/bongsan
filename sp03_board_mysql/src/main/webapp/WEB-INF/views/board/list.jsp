<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글조회</title>
<script type="text/javascript">
	//document가 로딩 완료된후 함수 실행
	$(function() {
		//조회버튼
		$('#btnSearch').on('click', function(){
			var findKey = $('#findKey').val();
			var findValue = $('#findValue').val();
			console.log(findKey);
			console.log(findValue);
			$(location).attr('href',
					'${path}/board/list?findKey='+findKey+'&findValue='+findValue);
		});
		//추가 버튼 클릭시
		$('#btnAdd').on('click', function() {
			$(location).attr('href','${path}/board/add');
		});
		//제목을 클릭했을때
		$('.aSubject').on('click', function(e) {
			e.preventDefault(); //기본기능  소멸
			var bnum = $(this).attr('href');
			console.log(bnum);
			$(location).attr('href', '${path}/board/detail?bnum='+bnum);
		});
		
		//페이지를 클릭했을때
		$('.page-link').on('click', function(e) {
			e.preventDefault(); //기본기능  소멸
			var curPage = $(this).attr('href');
			var findKey = $('#findKey').val();
			var findValue = $('#findValue').val();
			
			console.log(curPage);
			$(location).attr('href','${path}/board/list?curPage='+curPage+'&findKey='+findKey+'&findValue='+findValue);
		});
		
	});

</script>
</head>
<body>
	<h2 class="alert alert-light">게시판</h2>
	<div class="container">
	<%@ include file="maininclude.jsp" %>
	<div class="navbar navbar-expand-sm bg-dark navbar-dark">
		<select class="form-control" name="sellist1" id ="findKey" style="width: 30%">
			<option value="writer" <c:out value="${pdto.findKey=='writer'?'selected':''}" />>작성자</option>
			<option value="subject" <c:out value="${pdto.findKey=='subject'?'selected':''}" />>제목</option>
			<option value="content" <c:out value="${pdto.findKey=='content'?'selected':''}" />>내용</option>
			<option value="subcon" <c:out value="${pdto.findKey=='subcon'?'selected':''}" />>제목+내용</option>
		</select>
		<input style="width: 70%" class="form-control mr-sm-2" placeholder="Search" type="text" id="findValue" value="${pdto.findValue}">
		<input id="btnSearch" type="button" value="조회" class="btn btn-success">
		<input id="btnAdd" type="button" value="글쓰기" class="btn btn-light">
	</div>
	<!-- 리스트 출력 -->
	<div>
		<table class="table table-dark table-striped">
			<tr>
				<th>순번</th>
				<th style="width: 20%">작성자</th>
				<th style="width: 40%">제목</th>
				<th>이메일</th>
				<th>조회수</th>
				<th>좋아요</th>
				<th>싫어요</th>
				<th>댓글수</th>
			</tr>
			<c:forEach var="board" items="${blist}">
				<tr>
					<td>${board.bnum}</td>
					<td>${board.writer}</td>
					<td><a class="aSubject" href="${board.bnum}" >${board.subject}</a></td>
					<td>${board.email}</td>
					<td>${board.readcnt}</td>
					<td>${board.likecnt}</td>
					<td>${board.dislikecnt}</td>
					<td>${board.replycnt}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<!-- 페이징 처리 -->
	<div>
	<ul class="pagination">
		<c:if test="${pdto.startPage!=1}">
			<li class="page-item"><a class="page-link" href="${pdto.startPage-1}">이전</a></li>
		</c:if> 
		<c:forEach var="i" begin="${pdto.startPage}" end="${pdto.endPage}">
			<li class="page-item"><a class ="page-link" href="${i}"> ${i}</a></li>
		</c:forEach>
		<c:if test="${pdto.endPage < pdto.totPage}">
			<li class="page-item"><a class ="page-link" href="${pdto.endPage+1}">다음</a></li>
		</c:if>
	</ul>
	</div>
	</div>
</body>
</html>
