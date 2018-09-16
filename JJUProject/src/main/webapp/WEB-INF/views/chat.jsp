<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

	<meta charset="UTF-8">
	<title>Random Chat</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
	<script src="https://cdn.jsdelivr.net/sockjs/1/sockjs.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col">
				<jsp:include page="home.jsp" />
			</div>
		</div>
		
		<div class="row">
			<div class="col-9">
				<h1>Random Chat</h1>
				<textarea id="data" rows="25" cols="60" readonly="readonly" class="form-control col-sm-5" style="margin-bottom: 5px;background: white"></textarea>
				<input type="text" id="message" disabled="disabled" class="btn btn-outline-primary" style="width: 344px;"/>
				<input type="button" id="sendBtn" class="btn btn-primary" value="Start"/>
				<input type="hidden" id="closeBtn" class="btn btn-danger" value="Close"/>
				<a href="javascript:history.back();" class="btn btn-secondary">Back</a>
			</div>
		</div>
	</div>
	
    	
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/chat.js"></script>
	
	<!-- Bootstrap javascript -->
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
	
</body>
</html>
