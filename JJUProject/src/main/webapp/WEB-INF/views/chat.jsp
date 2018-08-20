<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Random Chat</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
<script src="https://cdn.jsdelivr.net/sockjs/1/sockjs.min.js"></script>
<jsp:include page="home.jsp" />
</head>
<body>
	<br>
	<a href="javascript:history.back()" id="back">Back</a> <br>
	<textarea id="data" rows="30" cols="70" readonly="readonly"></textarea> <br>
	<input type="text" id="message" disabled="disabled" />
	<input type="button" id="sendBtn" value="Start"/>
	<input type="hidden" id="closeBtn" value="Close"/>
    	
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/chat.js"></script>
</body>
</html>
