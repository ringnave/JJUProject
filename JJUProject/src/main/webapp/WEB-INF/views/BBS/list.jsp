<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>JJProject</title>
	<jsp:include page="../home.jsp" />
	<style type="text/css">
		#content{
			float: left;
			width: 50%;
		}
		
		#sidebar{
			float: right;
			width: 50%;
		}
	</style>	
</head>
<body>
	<div id="content">
		<h1>BBS</h1>
		
		<table border="1">
			<tr>
				<th>No</th>
				<th>Title</th>
				<th>Writer</th>
				<th>Views</th>
				<th>Date</th>
			</tr>
		
		<c:forEach var="list" items="${page}">
			<tr>
				<td>${list.b_no}</td>
				<td><a href = "/BBS/read?b_no=${list.b_no}">${list.b_title}</a></td>
				<td>${list.b_writer}</td>
				<td>${list.b_views}</td>
				<td>${list.b_date}</td>
			</tr>	
		</c:forEach>
		
		</table>
		
		<c:choose>
			<c:when test="${paging.numberOfRecords ne NULL and paging.numberOfRecords ne '' and paging.numberOfRecords ne 0}">
				<c:if test="${paging.currentPageNo gt 5}">
					<a href="javascript:goPage(${paging.prevPageNo})">prev</a>
				</c:if>
				
				<c:forEach var="i" begin="${paging.startPageNo}" end="${paging.endPageNo}" step="1">
					<c:choose>
						<c:when test="${i eq paging.currentPageNo}">
							<a href="javascript:goPage(${i})" style="color:red">${i} </a>
						</c:when>
						<c:otherwise>
							<a href="javascript:goPage(${i})">${i} </a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
	
				<c:if test="${paging.currentPageNo < paging.finalPageNo}">
					<a href="javascript:goPage(${paging.nextPageNo})">next</a>
				</c:if>
			</c:when>
		</c:choose>
		
		<a href="/BBS/create">Write</a>
	</div>
	
	<div id="sidebar">
		<select name="users" size="20">
			<c:forEach var="idList" items="${idList}">
				<option value="${idList.id}"> ${idList.name} (${idList.id}) </option>
			</c:forEach>
		</select>
	</div>
	
	<script type="text/javascript">
		function goPage(pages){
			location.href = '?' + "page=" + pages;
		}
	</script>
</body>
</html>