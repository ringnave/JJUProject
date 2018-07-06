<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Update Form</title>
</head>
<body>
	<h1>Update Form</h1>
	
	<form action = "/BBS/updateProcess" method = "post"> 
		<input type="hidden" name="b_no" value="${dto.b_no}">
		<input type="hidden" name="b_views" value="${dto.b_views}">
		<table border="1">
			<tr>
				<th>Title</th>
				<td><input type="text" name="b_title" value="${dto.b_title}"></td>
				
				<th>Writer</th>
				<td><input type="text" name="b_writer" value="${dto.b_writer}"></td>
			</tr>
			<tr>
				<th>Password</th>
				<td><input type="password" name="b_pw" value="${dto.b_pw}"></td>
			</tr>
			<tr>
				<th>Content</th>
				<td colspan="4"><textarea rows="10" cols="55" name="b_content">${dto.b_content}</textarea></td>
			</tr>
		</table>
		
		<input type="submit" value="Modify">
	</form>
	 
	<a href="javascript:history.back();">Back</a>
    
</body>
</html>