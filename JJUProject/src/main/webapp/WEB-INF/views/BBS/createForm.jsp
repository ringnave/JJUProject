<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Create</title>
	<jsp:include page="../home.jsp" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
</head>
<body>
	<h1>Create</h1>
	
	<form action = "/BBS/createProcess" method = "post" name="createInfo" onsubmit="return checkCreate()"> 
		<table border="1">
			<tr>
				<th>Title</th>
				<td><input type="text" name="b_title"></td>
				
				<th>Writer</th>
				<c:choose>
					<c:when test="${not empty loginSuccess}">
						<td><input type="text" name="b_writer" readonly="readonly" value="${loginSuccess.name} (${loginSuccess.id})"></td>
					</c:when>
					<c:otherwise>
						<td><input type="text" name="b_writer"></td>
					</c:otherwise>
				</c:choose>				
			</tr>
			<tr>
				<c:choose>
					<c:when test="${not empty loginSuccess}">
						<td style="display:none"><input type="password" name="b_pw" value="${loginSuccess.id }"></td>
					</c:when>
					<c:otherwise>
						<th>Password</th>
						<td><input type="password" name="b_pw"></td>
					</c:otherwise>
				</c:choose>	
			</tr>
			<tr>
				<th>Content</th>
				<td colspan="4"><textarea rows="10" cols="55" name="b_content" ></textarea></td>
			</tr>
		</table>
		
		<button type="submit">Create</button>
	</form>
	 
	<a href="/BBS/list">Back</a>
    
    <script type="text/javascript">
        function checkCreate() {
        	
        	var title = $("input[name=b_title]").val()
        	var writer = $("input[name=b_writer]").val()
        	var pw = $("input[name=b_pw]").val()
        	var content = $("textarea[name=b_content]").val()
        	
        	// First whitespace
    	    var Pattern = /[^\s]/
    	    
        	if(!Pattern.test(title) || !document.createInfo.b_title.value){
        		alert("Please enter a tilte")
        		document.createInfo.b_title.focus()
        		return false
        	}
        	else if(!Pattern.test(writer) || !document.createInfo.b_writer.value){
        		alert("Please enter a writer")
        		document.createInfo.b_writer.focus()
        		return false
        	}
        	else if(!Pattern.test(pw) || !document.createInfo.b_pw.value){
        		alert("Please enter a password")
        		document.createInfo.b_pw.focus()
        		return false
        	}
        	else if(!document.createInfo.b_content.value){
        		alert("Please enter a content")
        		document.createInfo.b_content.focus()
        		return false
        	}
        	else{
        		return true
        	}
        }
        
    </script>
    
</body>
</html>