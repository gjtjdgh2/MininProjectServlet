<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>주소록 Servlet</h1>
	<br/>
<h2>새 주소 등록</h2>
<table border="1">
 <form  action="<c:url value="/pns"/>" method="POST">
		
		<input type="hidden" name="action" value="insert" /><!-- 숨은 데이터 -->
	
		<label for="name">이름</label>
		<br/>
		<input type="text" name="name" id="name" />
		<br/>
		<label for="hp">휴대전화</label>
			<br/>
		<input type="text" name="hp" id="hp" />
		<br/>
		<label for="tel">집전화</label>
			<br/>
		<input type="text" name="tel" id="tel" />
		<input type="submit" value="등록" />
		</form>
</table>

<a href="<c:url value="/pns"/>">목록 보기</a>
</body>
</html>