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
	
	<table border="1">
		<tr><td><input type="text" id="id" placeholder="Username"></td></tr>
		<tr><td><input type="password" id="pw" placeholder="Password"></td></tr>
	</table>
	
	<input type="hidden" id="rsaPublicKeyModulus" value="<%=request.getAttribute("publicKeyModulus") %>" />
    <input type="hidden" id="rsaPublicKeyExponent" value="<%=request.getAttribute("publicKeyExponent") %>" />
	
	<a href="<%=request.getContextPath()%>/member/login" onclick="checkLogin(); return false;">Sign In</a>
	
	<form action = '<c:url value="/member/loginProcess" />' method = "post" id="loginInfo" style="display: none;"> 
		<input type="hidden" name="id" id="id" >
		<input type="hidden" name="pw" id="pw" >
    </form>
    
	<a href="javascript:history.back();">Back</a>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/rsa/jsbn.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/rsa/rsa.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/rsa/prng4.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/rsa/rng.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/login.js"></script>
</body>
</html>
