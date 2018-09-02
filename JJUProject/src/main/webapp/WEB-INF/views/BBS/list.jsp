<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	
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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
	<script src="https://cdn.jsdelivr.net/sockjs/1/sockjs.min.js"></script>
</head>
<body>
	<br>
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
		<form name="currentUsersForm">
			<input type="text" name="currentUsers" readonly="readonly">
		</form>
		
		<!-- User listing -->
		<select id="idList" name="users" size="20" onchange="enrollSession()">
			<c:forEach var="idList" items="${idList}">
				<c:choose>
					<c:when test="${idList.current == 1 }">
						<option id="${idList.id}" value="${idList.name} (${idList.id})" style="color:blue"> ${idList.name} (${idList.id}) </option>
					</c:when>
					<c:otherwise>
						<option id="${idList.id}" value="${idList.name} (${idList.id})"> ${idList.name} (${idList.id}) </option>
					</c:otherwise>
				</c:choose>
				
				<script type="text/javascript">
					function startInterval(seconds, callback) { 
						callback() 
						return setInterval(callback, seconds * 1000) 
					}
					
					startInterval(1, function(){
						// For Alarm Service
					    $.ajax({ 
							async: true,
					        type : "POST",
					        url : "/getAlarm",
					        data : ${idList.id}.id,
					        contentType: "application/json; charset=UTF-8",
					        success : function(data) {
					        	if(data.count != null){
					        		document.getElementById(${idList.id}.id).innerHTML = "${idList.name} (${idList.id}) ["+data.count+"]"
					        	} else {
					        		document.getElementById(${idList.id}.id).innerHTML = "${idList.name} (${idList.id})"
					        	}
					        },
					        error : function(request, status, error) {

					        }
					    })
					})
					
				</script>
			</c:forEach>
		</select>
		
		<!-- Button trigger modal -->
		<button data-backdrop="static" data-keyboard="false" id="modalBtn" type="button" 
		class="btn btn-primary" data-toggle="modal" data-target="#messageModal" disabled="disabled">
		  Message
		</button>
		
		<!-- Modal -->
 		<div class="modal fade" id="messageModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
		  <div class="modal-dialog modal-dialog-centered" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalCenterTitle"></h5>
		        <button id="removeBtn" type="button" class="btn btn-secondary" style="background-color: red; border: 1px red solid">Remove Dialogue</button>
		      </div>
		      <div class="modal-body">
		        <textarea id="data" rows="20" cols="50" readonly="readonly"></textarea>
		      </div>
		      <div class="modal-footer">
		      	<input type="text" id="message">
		        <button id="sendBtn" type="button" class="btn btn-primary">Send Message</button>
		        <button id="closeBtn" type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
		      </div>
		    </div>
		  </div>
		</div> 
	</div>
	
	<script type="text/javascript" src="/resources/js/message.js"></script>
	
	<!-- Bootstrap javascript -->
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>