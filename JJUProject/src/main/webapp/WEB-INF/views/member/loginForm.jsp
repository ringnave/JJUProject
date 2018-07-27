<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Login Page</title>
	<jsp:include page="../home.jsp" />
</head>
<body>
	<h1>Login Page</h1>
	
	<form action = '<c:url value="/member/loginProcess" />' method = "post"> 
		<table border="1">
			<tr><td><input type="text" name="id" placeholder="Username"></td></tr>
			<tr><td><input type="password" name="pw" placeholder="Password"></td></tr>
			<tr><td><input type="submit" value="Sign in"></td></tr>
		</table>
    </form>
	<a href="javascript:history.back();">Back</a>
</body>
</html>
