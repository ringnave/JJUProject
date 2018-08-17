<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Random Chat</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
<script src="https://cdn.jsdelivr.net/sockjs/1/sockjs.min.js"></script>
</head>
<body>
	<a href="javascript:history.back()" id="back">Back</a> <br>
	<textarea id="data" rows="30" cols="70" readonly="readonly"></textarea> <br>
	
    <input type="text" id="message" />
    <input type="button" id="sendBtn" value="Send" />
    	
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/chat.js"></script>
</body>
</html>
