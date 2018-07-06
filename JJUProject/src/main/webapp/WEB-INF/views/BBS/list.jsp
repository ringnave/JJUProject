<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>BBS</title>
</head>
<body>
	<h1>BBS</h1>
	
	<table border="1">
		<tr>
			<th>No</th>
			<th>Title</th>
			<th>Writer</th>
			<th>Views</th>
			<th>Date</th>
		</tr>
	
	<c:forEach var="list" items="${list}">
		<tr>
			<td>${list.b_no}</td>
			<td><a href = "/BBS/read?b_no=${list.b_no}">${list.b_title}</a></td>
			<td>${list.b_writer}</td>
			<td>${list.b_views}</td>
			<td>${list.b_date}</td>
		</tr>	
	</c:forEach>
	
	</table>
	
	<a href="/BBS/create">Write</a>
</body>
</html>