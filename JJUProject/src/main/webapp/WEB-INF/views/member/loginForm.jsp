<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
<head>
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Login Page</title>
<% 
	if(session.getAttribute("loginFalse") == null || (boolean)session.getAttribute("loginFalse") == true){
		session.setAttribute("loginFalse", false);
%>
		<script type="text/javascript">
			alert("Username or Password incorrect");
		</script>
<%
	}
%>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col">
				<jsp:include page="../home.jsp" />
			</div>
		</div>
		
		<div class="row">
			<div class="col-9">
				<h1>Login Page</h1>
				
				<input type="text" id="id" placeholder="Username" class="form-control" style="width: 226px;margin-bottom: 5px;">
				<input type="password" id="pw" placeholder="Password" class="form-control" style="width: 226px;margin-bottom: 5px;">
				
				
				<input type="hidden" id="rsaPublicKeyModulus" value="<%=request.getAttribute("publicKeyModulus") %>" />
			    <input type="hidden" id="rsaPublicKeyExponent" value="<%=request.getAttribute("publicKeyExponent") %>" />
				
				<a href="<%=request.getContextPath()%>/member/login" class="btn btn-primary" onclick="checkLogin(); return false;">Sign In</a>
				
				<form action = '<c:url value="/member/loginProcess" />' method = "post" id="loginInfo" style="display: none;"> 
					<input type="hidden" name="id" id="id" >
					<input type="hidden" name="pw" id="pw" >
			    </form>
			    
				<a href="javascript:history.back();" class="btn btn-secondary">Back</a>
			</div>
			
			<div class="col" style="margin-top: 16px;">
				<jsp:include page="../sideIds.jsp" />
			</div>
		</div>
	</div>
	
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/rsa/jsbn.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/rsa/rsa.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/rsa/prng4.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/rsa/rng.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/login.js"></script>
    
    <!-- Bootstrap javascript -->
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    
</body>
</html>
