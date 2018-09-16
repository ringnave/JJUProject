<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Join Page</title>
    
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
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
				<h1>Join Page</h1>
		 
				<table>
					<tr>
						<td><input type="text" name="id" id="id" placeholder="Your Username (3~20 characters)" class="form-control" style="width: 260px;"></td>
						<td><input type="button" value="Username check" onclick="usernameCheck()" class="btn btn-warning"></td>
					</tr>
					<tr><td><input type="password" id="pw" placeholder="Your Password (6~20 characters)" class="form-control" style="width: 260px;"></td></tr>
					<tr><td><input type="text" id="name" placeholder="Your Name (3~20 characters)" class="form-control" style="width: 260px;"></td></tr>
					<tr><td><input type="text" id="email" placeholder="Your Email (xxx@xxx.xxx)" class="form-control" style="width: 260px;"></td></tr>
					<tr><td><input type="text" id="phone" placeholder="Your Phone (xxx-xxxx-xxxx)" class="form-control" style="width: 260px;margin-bottom: 5px;"></td></tr>
				</table>
				
				<input type="hidden" id="rsaPublicKeyModulus" value="<%=request.getAttribute("publicKeyModulus") %>" />
			    <input type="hidden" id="rsaPublicKeyExponent" value="<%=request.getAttribute("publicKeyExponent") %>" />
				
				<a href="<%=request.getContextPath()%>/member/join" class="btn btn-primary" onclick="checkJoin(); return false;">Sign In</a>
				
				<form action = "/member/joinProcess" method = "post" id="joinInfo" name="joinInfo" style="display: none;"> 
					<input type="hidden" id="id" name="id" value="" >
					<input type="hidden" id="pw" name="pw" value="" >
					<input type="hidden" id="name" name="name" value="" >
					<input type="hidden" id="email" name="email" value="" >
					<input type="hidden" id="phone" name="phone" value="" >
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
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/join.js"></script>
    
    <!-- Bootstrap javascript -->
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    
</body>
</html>