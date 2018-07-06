<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Join Page</title>
</head>
<body>
	<h1>Join Page</h1>
	
	<form action = "/member/joinProcess" method = "post"> 
		<table border="1">
			<tr><td><input type="text" name="id" placeholder="Your Username"></td></tr>
			<tr><td><input type="password" name="pw" placeholder="Your Password"></td></tr>
			<tr><td><input type="text" name="name" placeholder="Your Name"></td></tr>
			<tr><td><input type="text" name="email" placeholder="Your Email"></td></tr>
			<tr><td><input type="text" name="phone" placeholder="Your Phone"></td></tr>
			<tr><td><input type="submit" value="Sign in"></td></tr>
		</table>
    </form>
    
</body>
</html>