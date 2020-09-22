<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러처리</title>
</head>
<body>
	<h2>예외 발생</h2>
	<h2>${exception.getMessage()}</h2>
	<ul>
		<c:forEach var="stack" items="${exception.getStackTrace()}">
			<li>${stack.toString()}</li>
		</c:forEach>
	
	</ul>
	
</body>
</html>