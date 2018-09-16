<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Read</title>
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
				<h1>Read</h1>
				
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>Title</th>
							<td colspan="5">${read.b_title}</td>
						</tr>
						<tr>
							<th style="width: 66px;">Writer</th>
							<td>${read.b_writer}</td>
							
							<th style="width: 56px;">Views</th>
							<td>${read.b_views}</td>
							
							<th style="width: 56px;">Date</th>
							<td>${read.b_date}</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>Content</th>
							<td colspan="7" style="height: 411px;">${read.b_content}</td>
						</tr>
					</tbody>
				</table>
				
				<c:set var="idName" value="${loginSuccess.name} (${loginSuccess.id})"></c:set>
				<c:choose>
					<c:when test="${idName eq read.b_writer}"> <!-- 프리패스 -->
						<a href="/BBS/pass?b_type=update&b_no=${read.b_no}" class="btn btn-info">Modify</a>
						<a href="/BBS/pass?b_type=delete&b_no=${read.b_no}" class="btn btn-danger">Delete</a>	
					</c:when>
					<c:when test="${read.b_writer.indexOf('(No Account)') eq -1}">	
					</c:when>
					<c:otherwise>
						<a href="/BBS/prePasswordCheck?b_type=update&b_no=${read.b_no}" class="btn btn-info">Modify</a>
						<a href="/BBS/prePasswordCheck?b_type=delete&b_no=${read.b_no}" class="btn btn-danger">Delete</a>
					</c:otherwise>
				</c:choose>
				
				<a href="javascript:history.back();" class="btn btn-secondary">Back</a><br>
				
				<!-- Comments -->
				<div id="commentList"></div>
				<div id="commentListPaging"></div>
				
				<c:if test="${sessionScope.loginSuccess != null}">
					<textarea rows="3" id="commentContent" class="form-control col-sm-10" style="margin-bottom: 5px;"></textarea>
					<button class="btn btn-primary" type="submit" id="commentBtn"style="margin-bottom: 50px;">Post a comment</button>
				</c:if>	
			</div>
			
			<div class="col" style="margin-top: 16px;">
				<jsp:include page="../sideIds.jsp" />
			</div>
		</div>
	</div>
	
	
	<script type="text/javascript">
		commentList(getRecentPage())
		
		$("#commentBtn").click(function(){
		    var content = $("#commentContent").val()
		    var boardNo = "${read.b_no}"
		    var param = "content="+content+"&boardNo="+boardNo
		    
		    // All characters except for whitespace
		    var Pattern = /[^\s]/
		    
		    if(content == '' || !Pattern.test(content)) {
		    	alert("Please enter a content")
		    	return
		    }
		    
		    $.ajax({                
		        type: "post",
		        url: "/comment/insert",
		        data: param,
		        success: function(){
		            alert("Comment Posted.")
		            $("#commentContent").val('')
		            commentList(getRecentPage())
		        },
		        error: function(){
		        	console.log("comment error")
		        }
		    })
		})
		
		function commentList(page) {
			var myId
			
			// get session to show the buttons
			$.ajax({
				async: true,
		        type: "POST",
		        url: "${path}/comment/getSession",
			    contentType: "application/json; charset=UTF-8",
		        success: function(data){
		        	myId = data.id
		        },
		        error: function(){
		        	console.log("getSession error")
		        }
		    })
		    
		    $.ajax({
		        type: "get",
		        url: "/comment/list?b_no=${read.b_no}"+"&page="+page,
		        success: function(result){
		        	var output = '<br><table class="table">'
		        	var outputPaging = '<nav aria-label="Page navigation example">'
		        	
		            for(var i in result.comments){
		                output += '<tr scope="row">'
		                output += "<td scope='col' style='width: 174px;'>"+result.comments[i].name+" ("+result.comments[i].id+")</td>"
		                output += "<td scope='col'>"+result.comments[i].content+"</td>"
		                output += "<td scope='col' style='width: 174px;'>"+dateForm(result.comments[i].regDate)+"</td>"
		                if(myId == result.comments[i].id){
		                	output += '<td scope="col"><a href="javascript:commentDelete('+result.comments[i].commentNo+')">delete</a></td>'
		                }
		                output += "<tr>"
		            }
		            output += "</table>"
		            
		            outputPaging += '<ul class="pagination justify-content-center">'
		            if(result.paging.numberOfRecords != 0){
		            	if(result.paging.currentPageNo > 5){
		            		outputPaging += '<li class="page-item"><a class="page-link" href="javascript:goPage('+result.paging.prevPageNo+')">prev</a></li>'
		            	}
		            	
		            	for (var i = result.paging.startPageNo; i <= result.paging.endPageNo; i++) {
							if(i == result.paging.currentPageNo){
								outputPaging += '<li class="page-item"><a class="page-link" href="javascript:goPage('+i+')" style="color:red">'+i+'</a></li>'
							} else {
								outputPaging += '<li class="page-item"><a class="page-link" href="javascript:goPage('+i+')">'+i+'</a></li>'
							}
						}
		            	
		            	if(result.paging.currentPageNo < result.paging.finalPageNo){
		            		outputPaging += '<li class="page-item"><a class="page-link" href="javascript:goPage('+result.paging.nextPageNo+')">next</a></li>'
		            	}
		            }
		            
		            outputPaging += '</ul>'
		            outputPaging += '</nav>'
		            $("#commentList").html(output)
		            $("#commentListPaging").html(outputPaging)
		        },
		        error: function(){
		        	console.log("comment page error")
		        }
		    })
		}
		
		function dateForm(value){
			var date = new Date(parseInt(value));
		    var year = date.getFullYear();
		    var month = date.getMonth();
		    var day = date.getDate();
		    var hour = date.getHours();
		    var minute = date.getMinutes();
		    var second = date.getSeconds();
		    strDate = year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
		    return strDate;
		}
		
		function commentDelete(commentNo){
			$.ajax({
				async: true,
		        type: "GET",
		        url: "${path}/comment/delete?c_no="+commentNo,
			    contentType: "application/json; charset=UTF-8",
		        success: function(){
		        	alert("Comment deleted")
		        	commentList(getRecentPage())
		        },
		        error: function(){
		        	console.log("getSession error")
		        }
		    })
		}
		
		function goPage(page){
			commentList(page)
		}
		
		function getRecentPage(){
			var recentPage
			$.ajax({
				async: false,
		        type: "get",
		        url: "/comment/list?b_no=${read.b_no}",
		        success: function(result){
		        	recentPage = result.paging.finalPageNo
		        }
		    })
		    return recentPage
		}
	</script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/comment.js"></script>
	
	<!-- Bootstrap javascript -->
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>