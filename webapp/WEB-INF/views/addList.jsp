<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="./insert" method="get">
		<table border="1">
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" value="" required></td>
				<td>비밀번호</td>
				<td><input name="password" value="" required></td>
			</tr>
			<tr>
				<td colspan="4"><textarea name="content" style="width: 30rem" required></textarea></td>
			</tr>
			<tr>
				<td colspan="4"><button type="submit">확인</button></td>
			</tr>
		</table>
	</form>
	<br/>
	
	<c:forEach items="${requestScope.guestList }" var="gvo">
		<table border="1">
			<tr>
				<td>${gvo.no }</td>
				<td>${gvo.name }</td>
				<td>${gvo.regDate }</td>
				<td><a href="./deleteForm?no=${gvo.no }">삭제</a></td>
			</tr>
			<tr>
				<td colspan="4" style="width: 30rem">${gvo.content }</td>
			</tr>
		</table>
		<br/>
	</c:forEach>
	
</body>
</html>