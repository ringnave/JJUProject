<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Read</title>
	<jsp:include page="../home.jsp" />
</head>
<body>
	<h1>Read</h1>
	
	<table border="1">
		<tr>
			<th>Title</th>
			<td>${read.b_title}</td>
			
			<th>Writer</th>
			<td>${read.b_writer}</td>
			
			<th>Views</th>
			<td>${read.b_views}</td>
			
			<th>Date</th>
			<td>${read.b_date}</td>
		</tr>
		<tr>
			<th>Content</th>
			<td colspan="7">${read.b_content}</td>
		</tr>
	</table>
	<a href="/BBS/prePasswordCheck?b_type=update&b_no=${read.b_no}">Modify</a>
	<a href="/BBS/prePasswordCheck?b_type=delete&b_no=${read.b_no}">Delete</a>
	<a href="#" onClick="history.go(-1); return false;">Back</a>
</body>
</html>