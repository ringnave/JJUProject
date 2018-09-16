<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	session.setAttribute("loginFalse", false);
%>
<html>
<head>
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>JJProject</title>
	
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
				<div id="content">
					<h1>BBS</h1>
					
					<table class="table table-hover table-bordered">
						<thead>
							<tr>
								<th scope="col" style="width: 46px;">No</th>
								<th scope="col" style="width: 336px;">Title</th>
								<th scope="col" style="width: 136px;">Writer</th>
								<th scope="col" style="width: 66px;">Views</th>
								<th scope="col" style="width: 240px;">Date</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="list" items="${pages}">
								<tr>
									<td>${list.b_no}</td>
									<td>
										<a href = "/BBS/read?b_no=${list.b_no}">${list.b_title}</a>
										<c:if test="${list.b_commentCount > 0 }">
											(${list.b_commentCount})
										</c:if>
									</td>
									<td>${list.b_writer}</td>
									<td>${list.b_views}</td>
									<td>${list.b_date}</td>
								</tr>	
							</c:forEach>
						</tbody>
					</table>
					
					<a class="btn btn-primary" href="/BBS/create" role="button">Write</a>
					<nav aria-label="Page navigation example">
						<ul class="pagination justify-content-center">	 	
						    <c:choose>
								<c:when test="${paging.numberOfRecords ne NULL and paging.numberOfRecords ne '' and paging.numberOfRecords ne 0}">
									<c:if test="${paging.currentPageNo gt 5}">
										 <li class="page-item"><a class="page-link" href="javascript:goPage(${paging.prevPageNo})">prev</a></li>
									</c:if>
									
									<c:forEach var="i" begin="${paging.startPageNo}" end="${paging.endPageNo}" step="1">
										<c:choose>
											<c:when test="${i eq paging.currentPageNo}">
												 <li class="page-item active"><a class="page-link" href="javascript:goPage(${i})" >${i} </a></li>
											</c:when>
											<c:otherwise>
												 <li class="page-item"><a class="page-link" href="javascript:goPage(${i})">${i} </a></li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
						
									<c:if test="${paging.currentPageNo < paging.finalPageNo}">
										 <li class="page-item"><a class="page-link" href="javascript:goPage(${paging.nextPageNo})">next</a></li>
									</c:if>
								</c:when>
							</c:choose>
					  	</ul>
					</nav>
				</div>
		    </div>
		    
		    <dir class="col">
 		    	<jsp:include page="../sideIds.jsp" /> 
		    </dir>
	  	</div>
	</div>
	
	<!-- Bootstrap javascript -->
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>