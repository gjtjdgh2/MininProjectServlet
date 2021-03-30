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

	
	<h2>주소록 Servlet</h2>
	<br/>
	<h3>목록</h3>
	<form action="<c:url value="/pns"/>">
		<input type="hidden" name="a" value="search" />
		<label for="keyword">검색어</label>
		<input type="text" name="keyword" id="keyword"/>
		<input type="submit" value="검색" />
	</form>
	
	<table border="1">
	<tr>
			<th>이름</th>
		
			<th>휴대전화</th>
		
			<th>전화번호</th>
			<th>도구</th>
			
		</tr>
	<c:forEach  items="${list }" var="vo">
	
		<tr>
		<td>${vo.name }</td>
		<td>${vo.hp }</td>
		<td>${vo.tel }</td>
		<td>
		<form  action="<c:url value="/pns"/>">
		<input type="hidden" name="a" value="delete" />
		<input type="hidden" name="no" value="${vo.id }"/>
		<input type="submit" value="삭제" />
		</form>
		</td>
		</tr>
		
	</c:forEach>
	</table>
	<br/>
	
	<a href="<c:url value="/pns?a=form"/>">새 주소 주가</a>
	
</body>
</html>