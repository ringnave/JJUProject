<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
<head>
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	
	<title>Home</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
</head>
<body>
	<a href="/"><h1>Home</h1></a>

	<c:if test="${not empty loginSuccess}">
		${loginSuccess.id} (${loginSuccess.name}) is online.
	</c:if>
	
	<c:choose>
		<c:when test="${empty loginSuccess}">
			<a href="/member/login">Sign In</a> &emsp;
			<a href="/member/join">Sign Up</a> &emsp;
		</c:when>
		<c:otherwise>
			<a href="/member/logout">Sign Out</a>
			<a href="/member/deactivate">Deactivate Account</a>
		</c:otherwise>
	</c:choose>
	
	<a href="/chat">Random Chat</a>
	
	<script type="text/javascript">
		// ajax로 1초마다 시간변수를 초기화 하는 신호를 보냄. 서버에서는 시간이 지날수록 감소시키는 함수를 만듦, 시간이 지나버리면 로그아웃해버림.
		function startInterval(seconds, callback) { 
			callback()
			return setInterval(callback, seconds * 1000) 
		}
		
		startInterval(1, function(){
			// Just for painting blue on current users.
			$.ajax({ 
				async: true,
		        type : "POST",
		        url : "/sessionTimeLogout", 
		        contentType: "application/json; charset=UTF-8",
		        success : function(data) {},
		        error : function(request, status, error) {}
		    })
		})
	</script>
	
	<!-- Bootstrap javascript -->
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>