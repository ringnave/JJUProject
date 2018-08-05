<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<a href="/"><h1>Home</h1></a>

	<c:if test="${not empty loginSuccess}">
		${loginSuccess.id} (${loginSuccess.name}) is online.
	</c:if> <br>
	
	<c:choose>
		<c:when test="${empty loginSuccess}">
			<a href="/member/login">Sign In</a>
		</c:when>
		<c:otherwise>
			<a href="/member/logout">Sign Out</a>
			<a href="/member/deactivate">Deactivate Account</a>
		</c:otherwise>
	</c:choose>
	
	<a href="/member/join">Sign Up</a> <br>
	
</body>
</html>
