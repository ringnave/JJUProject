var sock
var sessionId

$(document).ready(function() {
	// When you click the message button, you can get your session.
	$("#modalBtn").click(function(){
		if(sessionCheck() == false){
			alert("You should sign in!")
			return false
		}
		
		$("#data").empty()
		
		sessionId = randomString()
		sock = new SockJS('/message', [], {
		    sessionId: () => {
		       return sessionId
		    }
		})
		sock.onmessage = onMessage
	})
	
    $("#sendBtn").click(function() {
        sendMessage()
        $("#message").val("")
        $("#message").focus()
    })
    
    $("#closeBtn").click(function() {
        sock.close()
    })
    
    $(document).keyup(function(event){
        if ( event.keyCode == 13 ) {
            $("#sendBtn").click()
            return false
        } else if ( event.keyCode == 27 ) {
            $("#closeBtn").click()
            return false
        }
   })
})

function sendMessage() {
    sock.send($("#message").val())
}

function onMessage(evt) {
    var data = evt.data
    $("#data").append(data)
    
    var idData = document.getElementById("data"); 
    idData.scrollTop = idData.scrollHeight
}

function randomString() {
	var chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz";
	var string_length = 8;
	var randomstring = '';
	for (var i=0; i<string_length; i++) {
		var rnum = Math.floor(Math.random() * chars.length);
		randomstring += chars.substring(rnum,rnum+1);
	}
	return randomstring;
}

$('#idList').on('click', function(){
	if($('#idList').val() != null) $('#modalBtn').prop("disabled", false)
})

$('#messageModal').on('show.bs.modal', function (event) {
	var button = $(event.relatedTarget) // Button that triggered the modal
  	var recipient = $('#idList').val()
  	var modal = $(this)
  	
  	if(recipient == null) return
  	
  	modal.find('.modal-title').text('Message with ' + recipient)
})

function startInterval(seconds, callback) { 
	callback() 
	return setInterval(callback, seconds * 1000) 
}

startInterval(1, function(){
	$.ajax({ 
		async: true,
        type : "GET",
        url : "/current/getCurrentUsers", 
        contentType: "application/json; charset=UTF-8",
        success : function(data) {
        	document.currentUsersForm.currentUsers.value = 'Current Users: ' + data.currentUsers
        	
        	data.currentUsersList.map(value => {
        		if(value.current == 1){
        			document.getElementById(value.id).style.color="blue"
        		}
        		else{
        			document.getElementById(value.id).style.color="black"
        		}
        	})
        },
        error : function(request, status, error) {
        	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
        }
    })
})

function goPage(pages){
	location.href = '?' + "page=" + pages;
}

function enrollSession(){
	var idList = document.getElementById("idList");
    var selectId = idList.options[idList.selectedIndex].id;
    
    $.ajax({ 
		async: true,
        type : "POST",
        url : "/enrollSession",
        data : selectId,
        contentType: "application/json; charset=UTF-8",
        error : function(request, status, error) {
        	alert("enrollSession() error")
        }
    })
}

function sessionCheck(){
	var isSignedIn
	
	$.ajax({ 
		async: false,
        type : "POST",
        url : "/sessionCheck",
        contentType: "application/json; charset=UTF-8",
        success : function(result){
        	isSignedIn = result.isSignedIn
        },
        error : function(request, status, error) {
        	alert("sessionCheck() error")
        }
    })
    return isSignedIn
}