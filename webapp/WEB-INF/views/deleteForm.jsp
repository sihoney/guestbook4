<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>삭제 폼입니다.</h1>
	
	<form action="./delete" method="get">
		비밀번호<input type="text" name="password" value="" required>
		<button type="submit">확인</button>
		<input type="hidden" name="no" value="${requestScope.no }">
	</form>
	
	<a href="#">메인으로 돌아가기</a>
</body>
</html>