<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회사 위치</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js"></script>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=f2aihev7t7"></script>
<script type="text/javascript">
	$(function(){
			var addr = $('#addr').val();
			$.ajax({
				type : 'get',
				contentType : 'application/x-www-form-urlencoded; charset=utf-8',
				url : '/spring_mysql/board/mapgeo?addr='+addr,
				dataType : 'json',
				success : function(result){
					alert('성공');
					console.log(result);
					/* $('#divresult').html('<label>위도 : </label>'+result.x);
					$('#divresult').append('<br>');
					$('#divresult').append('<label>경도 : </label>'+result.y); */
					mapDraw(result.x,result.y);
				},
				error : function(result){
					alert('실패');
					console.log(result);
				}
			});
			
			function mapDraw(x,y) {
				var mapOptions = {
					    center: new naver.maps.LatLng(y, x),
					    zoom: 15
					};

				//옵션을 이용한 맵만들기
				var map = new naver.maps.Map('map', mapOptions);
				
				//마커 만들기
				var marker = new naver.maps.Marker({
				    position: new naver.maps.LatLng(y, x),
				    map: map
				});
				
			}
			
			$('#btnlist').on('click', function(){
				$(location).attr('href', '/spring_mysql/board/');
			});
		});	
		//지도불러오기
		

	
</script>
</head>
<body>
	주소 : <input id="addr" type="text" value="서울 관악구 국회단지15길 32" readonly="readonly">
	<button id="btnlist">돌아가기</button>
	<div id="divresult">
	</div>
	
	<div id="map" style="width:100%;height:400px;"></div>
</body>
</html>