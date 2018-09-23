<%--
To use this page, you should add this code below to the controller that you want.

	//For sideIds.jsp
	List<MemberVO> idList = new ArrayList<MemberVO>();
	idList = currentService.getCurrentUsers();
	model.addAttribute("idList", idList);
	
Add this code below to jsp file that you want to show this.
	
	<jsp:include page="../sideIds.jsp" />
--%>

<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	
	<style type="text/css">
			
		.modal-dialog,
		.modal-content {
		    /* 80% of window height */
		    height: 80%;
		}
		
		.modal-body {
    		/* 100% = dialog height, 120px = header + footer */
	    	max-height: calc(100% - 120px);
    		overflow-y: auto;
		}

	</style>	
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
	<script src="https://cdn.jsdelivr.net/sockjs/1/sockjs.min.js"></script>
</head>
<body>
	<form name="currentUsersForm">
		<div class="input-group mb-3">
	  		<div class="input-group-prepend">
	    		<span class="input-group-text" id="basic-addon1">Current Users</span>
	  		</div>
			<input type="text" class="form-control" name="currentUsers" readonly="readonly" style="background: white; text-align: center">
		</div>
	</form>
		
	<!-- User listing --> 
	<select id="idList" name="users" size="22" class="custom-select">
		<c:forEach var="idList" items="${idList}">
			<c:choose>
				<c:when test="${idList.current == 1 }">
					<option id="${idList.id}" value="${idList.name} (${idList.id})" style="color:blue"> ${idList.name} (${idList.id}) </option>
				</c:when>
				<c:otherwise>
					<option id="${idList.id}" value="${idList.name} (${idList.id})"> ${idList.name} (${idList.id}) </option>
				</c:otherwise>
			</c:choose>
			<c:out value="test"></c:out>
			<script type="text/javascript">
				function isMobile() {
				    return /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent);
				}
			
				function startInterval(seconds, callback) { 
					callback() 
					return setInterval(callback, seconds * 1000) 
				}
				
				if (isMobile()) {
					$('#idList').on('click', function(){
						
						$('#modalBtn').prop("disabled", false)	
						
						// For Alarm Service
					    $.ajax({ 
							async: false,
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
					        error : function(request, status, error) {}
					    })
					})
				} else {
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
					        error : function(request, status, error) {console.log("error")}
					    })
					})
				}
			</script>
		</c:forEach>
	</select>

	<!-- Button trigger modal -->
	<button data-backdrop="static" data-keyboard="false" id="modalBtn" type="button" 
	class="btn btn-primary" data-toggle="modal" data-target="#messageModal" disabled="disabled" style="width: 255px;">
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
	      	<div id="data"></div>
	      </div>
	      <div class="modal-footer">
	      	<input type="text" id="message">
	        <button id="sendBtn" type="button" class="btn btn-primary">Send Message</button>
	        <button id="closeBtn" type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	      </div>
	    </div>
	  </div>
	</div> 

	<script type="text/javascript" src="/resources/js/message.js"></script>
	
	<!-- Bootstrap javascript -->
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>