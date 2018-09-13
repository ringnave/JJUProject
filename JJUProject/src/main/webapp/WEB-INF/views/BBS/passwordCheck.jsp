<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Update Check</title>
</head>
<body>

	<h1>
		Password Check
	</h1>
	
	<form action = "/BBS/passwordProcess" method = "post"> 
		<input type="hidden" name="b_no" value="${b_no}">
		<input type="hidden" name="b_type" value="${b_type}">
		<table border="1">
			<tr><td><input type="password" name="b_pw" placeholder="Your Password"></td></tr>
			<tr><td><input type="submit" value="Confirm"></td></tr>
		</table>
    </form>
	
	<a href="javascript:history.back();">Back</a>
	
	<!-- Bootstrap javascript -->
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>