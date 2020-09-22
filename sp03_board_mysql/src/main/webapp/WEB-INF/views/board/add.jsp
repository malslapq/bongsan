<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 저장</title>
<script type="text/javascript">
 	$(function() {
 		//저장버튼을 클릭했을때
 		$('#btnAdd').on('click', function(e){
 			e.preventDefault(); //객체의 기본기능 소멸
 			$('#addForm').attr('action', '${path}/board/add');
 			$('#addForm').attr('method', 'post');
 			$('#addForm').attr('enctype', 'multipart/form-data');
 			
 			$('#addForm').submit();
 		} );

 		//목록버튼을 클릭했을때
 		$('#btnList').on('click', function(e) {
 			e.preventDefault(); //객체의 기본기능 소멸
			$(location).attr('href', '${path}/board/list');
		});
 		
		//파일 삭제 버튼을 클릭했을때
		$('.btnFileDelete').on('click', function(e) {
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
							'<button class="btnFileDelete">삭제</button>'+		
							'</div>';
			$('#fileGroup').append(appendHtml);
			
		});

		
	});
 </script>
</head>
<body>
	<h2>게시글 저장</h2>
	
	<form id="addForm">
		<table border="1">
			<tr>
				<td>작성자</td>
				<td><input type="text" name="writer"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="email" name="email"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td> <input type="text" name="subject"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name="content" rows="5" cols="20"></textarea> </td>
			</tr>
			<tr>
				<td>파일</td>
				<td>
					<div id = "fileGroup">
						<div>
							<input type="file" name="bfiles">	
							<button class="btnFileDelete">삭제</button>		
						</div>
					</div>
				</td>	
			</tr>
			<tr>
				<td colspan="2">
					<button id="btnAdd">저장</button>
					<button id="btnList">목록</button>
				</td>
				
			</tr>
		</table>
		 
	</form>
</body>
</html>