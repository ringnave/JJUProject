<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Update Form</title>
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
				<h1>Update Form</h1>
				
				<form action = "/BBS/updateProcess" method = "post"> 
					<input type="hidden" name="b_no" value="${dto.b_no}">
					<input type="hidden" name="b_views" value="${dto.b_views}">
					<table class="table table-bordered">
						<tr>
							<th>Title</th>
							<td><input type="text" name="b_title" value="${dto.b_title}" class="form-control"></td>
						</tr>
						<tr>
							<th>Writer</th>
							<c:set var="idName" value="${loginSuccess.name} (${loginSuccess.id})"></c:set>
							<c:choose>
								<c:when test="${idName eq dto.b_writer}">
									<td><input type="text" name="b_writer" readonly="readonly" value="${loginSuccess.name} (${loginSuccess.id})" class="form-control"></td>	
								</c:when>
								<c:otherwise>
									<td><input type="text" name="b_writer" value="${dto.b_writer}" class="form-control"></td>
								</c:otherwise>
							</c:choose>
						</tr>
						<tr>
							<th>Password</th>
							<td><input type="password" name="b_pw" value="${dto.b_pw}" class="form-control"></td>
						</tr>
						<tr>
							<th>Content</th>
							<td colspan="4"><textarea rows="10" cols="55" name="b_content" class="form-control col-sm-55">${dto.b_content}</textarea></td>
						</tr>
					</table>
					
					<input type="submit" value="Modify" class="btn btn-primary">
					<a href="javascript:history.back();" class="btn btn-secondary">Back</a>
				</form>
				 
				
			</div>

			<div class="col" style="margin-top: 16px;">
				<jsp:include page="../sideIds.jsp" />
			</div>
		</div>
	</div>

	
    
    <!-- Bootstrap javascript -->
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    
</body>
</html>