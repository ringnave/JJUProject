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
	
	<form action = "/member/joinProcess" method = "post" name="joinInfo" onsubmit="return checkJoin()"> 
		<table border="1">
			<tr><td><input type="text" name="id" placeholder="Your Username (3~20 characters)"></td></tr>
			<tr><td><input type="password" name="pw" placeholder="Your Password (6~20 characters)"></td></tr>
			<tr><td><input type="text" name="name" placeholder="Your Name (3~20 characters)"></td></tr>
			<tr><td><input type="text" name="email" placeholder="Your Email (xxx@xxx.xxx)"></td></tr>
			<tr><td><input type="text" name="phone" placeholder="Your Phone (xxx-xxxx-xxxx)"></td></tr>
			<tr><td><button type="submit">Sign In</button></td></tr>
		</table>
    </form>
    <a href="javascript:history.back();">Back</a>
	
	<script type="text/javascript">
    
		

        function checkJoin()
        {
        	
        	var id = $("input[name=id]").val();
        	var pw = $("input[name=pw]").val();
        	var name = $("input[name=name]").val();
        	var email = $("input[name=email]").val();
        	var phone = $("input[name=phone]").val();
        	
    	    var idPattern = /[a-z0-9A-Z_-]{3,20}/;
    	    var pwPattern = /[a-z0-9A-Z_-]{6,20}/;
    	    var namePattern = /[a-z0-9A-Z_-]{3,20}/;
    	    var emailPattern = /[a-z0-9A-Z_-]+@[a-z0-9A-Z]+.[a-zA-Z.]+/;
    	    var phonePattern = /\d{3}-\d{3,4}-\d{4}/;
    	    
        	if(!idPattern.test(id)){
        		alert("Username not long enough, 3~20 characters")
        		document.joinInfo.id.focus()
        		return false
        	}
        	else if(!pwPattern.test(pw)){
        		alert("Password not long enough, 6~20 characters")
        		document.joinInfo.pw.focus()
        		return false
        	}
        	else if(!namePattern.test(name)){
        		alert("Name not long enough, 3~20 characters")
        		document.joinInfo.name.focus()
        		return false
        	}
        	else if(!emailPattern.test(email)){
        		alert("Invalid email form")
        		document.joinInfo.email.focus()
        		return false
        	}
        	else if(!phonePattern.test(phone)){
        		alert("Invalid phone number form")
        		document.joinInfo.phone.focus()
        		return false
        	}
        	else{
        		return true
        	}
        }
        
    </script>
    
</body>
</html>