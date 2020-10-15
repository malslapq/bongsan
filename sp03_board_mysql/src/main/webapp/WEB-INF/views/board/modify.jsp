<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
<script type="text/javascript">
	$(function() {
 		//저장버튼을 클릭했을때
 		$('#btnAdd').on('click', function(e){
 			e.preventDefault(); //객체의 기본기능 소멸
 			$('#modifyForm').attr('action', '${path}/board/modify');
 			$('#modifyForm').attr('method', 'post');
 			$('#modifyForm').attr('enctype', 'multipart/form-data');
 			
 			$('#modifyForm').submit();
 		} );

 		//목록버튼을 클릭했을때
 		$('#btnList').on('click', function(e) {
 			e.preventDefault(); //객체의 기본기능 소멸
			$(location).attr('href', '${path}/board/list');
		});
		
		
		//파일 삭제 버튼을 클릭했을때
		$('#fileGroup').on('click','#btnFileDelete', function(e) {
			e.preventDefault(); 
			$(this).parent().remove();
		});
		
		//파일이 change
		//동적 생성 엘리먼트에 이벤트 달기
		$('#fileGroup').on('change','div',function(e){
			e.preventDefault(); 
			//파일 선택 추가 
			var appendHtml = '<div>' +
							'<input type="file" name="bfiles">' +	
							'<button class="btn btn-danger" id="btnFileDelete">파일삭제</button>'+		
							'</div>';
			$('#fileGroup').append(appendHtml);
			
		});
		
	});


</script>
</head>
<body>
	<h2 class="alert alert-primary">게시글 수정</h2>
	<div class="container">
	<%@ include file="maininclude.jsp" %>
	<form id="modifyForm">
		<table class="table table-hover" >
			<tr>
				<td>번호</td>
				<td><input id="bnum" type="text" class="form-control" style="width: 50%" name="bnum" value="${board.bnum}"></td>
			</tr>		
			<tr>
				<td>작성자</td>
				<td><input type="text" name="writer" class="form-control" style="width: 50%" value="${board.writer}"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="email" name="email" class="form-control" style="width: 50%" value="${board.email}"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td> <input type="text" name="subject" class="form-control" style="width: 50%" value="${board.subject}"> </td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name ="content" class="form-control" style="width: 50%" rows="5" cols="20">${board.content}</textarea> </td>
			</tr>
			<tr>
				<td>파일</td>
				<td>
					<div id="fileGroup">
						<c:forEach var="file" items="${flist}">
							<div>
								<input type="hidden" name="fnum" value="${file.fnum}"> 
								${file.filename}	
								<button class="btnFileDelete"id="btnFileDelete">삭제</button>
							</div>
						</c:forEach>
						<div>
							<input type="file" class="form-control-file border" style="width: 50%" name="bfiles">	
							<button class="btn btn-danger" id="btnFileDelete">파일삭제</button>		
						</div>
					</div>
				</td>	
			</tr>
			<tr>
				<td colspan="2">
					<button class="btn" id="btnAdd">저장</button>
					<button class="btn" id="btnList">목록</button>
				</td>
				
			</tr>
		</table>
	</form>	
	</div>
</body>
</html>
