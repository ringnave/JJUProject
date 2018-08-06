<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Join Page</title>
	
    
	<jsp:include page="../home.jsp" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
</head>
<body>
	<h1>Join Page</h1>
	 
	<table border="1">
		<tr>
			<td><input type="text" name="id" id="id" placeholder="Your Username (3~20 characters)"></td>
			<td><input type="button" value="Username check" onclick="usernameCheck()"></td>
		</tr>
		<tr><td><input type="password" id="pw" placeholder="Your Password (6~20 characters)"></td></tr>
		<tr><td><input type="text" id="name" placeholder="Your Name (3~20 characters)"></td></tr>
		<tr><td><input type="text" id="email" placeholder="Your Email (xxx@xxx.xxx)"></td></tr>
		<tr><td><input type="text" id="phone" placeholder="Your Phone (xxx-xxxx-xxxx)"></td></tr>
	</table>
	
	<input type="hidden" id="rsaPublicKeyModulus" value="<%=request.getAttribute("publicKeyModulus") %>" />
    <input type="hidden" id="rsaPublicKeyExponent" value="<%=request.getAttribute("publicKeyExponent") %>" />
	
	<a href="<%=request.getContextPath()%>/member/join" onclick="checkJoin(); return false;">Sign In</a>
	
	<form action = "/member/joinProcess" method = "post" id="joinInfo" name="joinInfo" style="display: none;"> 
		<input type="hidden" id="id" name="id" value="" >
		<input type="hidden" id="pw" name="pw" value="" >
		<input type="hidden" id="name" name="name" value="" >
		<input type="hidden" id="email" name="email" value="" >
		<input type="hidden" id="phone" name="phone" value="" >
    </form>
    
    <a href="javascript:history.back();">Back</a>
    
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/rsa/jsbn.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/rsa/rsa.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/rsa/prng4.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/rsa/rng.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/join.js"></script>
</body>
</html>