var sock
var sessionId
var repeatInterval
// This is for not affecting by keyboard
var isMatched = false

$(document).ready(function() {
	clearInterval(repeatInterval)
    $("#sendBtn").click(function() {
        if($("#sendBtn").val() == "Start"){
        	$("#data").empty()
        	$("#message").attr('disabled', true)
        	$("#message").val("Finding a user...")
        	
          	sessionId = randomString();
          	sock = new SockJS('/echo', [], {
          	    sessionId: () => {
          	       return sessionId
          	    }
          	})
          	sock.onmessage = onMessage
          	sock.onclose = onClose
          	
          	startInterval(1, function(){
          		$.ajax({ 
          			async: true,
          	        type : "POST",
          	        url : "/isMatched",
          	        data : sessionId,
          	        contentType: "application/json; charset=UTF-8",
          	        success : function(data) {
          	        	if(data.isMatched == true){
          	        		isMatched = true
          	        		clearInterval(repeatInterval)
          	        		$("#sendBtn").attr('disabled', false)
          	        		$("#message").val("")
          	        		$("#message").attr('disabled', false)
          	        	}
          	        },
          	        error : function(request, status, error) {
          	        	console.log("click Error")
          	        }
          	    })
          	})
          	
          	$("#sendBtn").val("Send")
          	$("#sendBtn").attr('disabled', true)
          	$("#closeBtn").attr("type", "button")
        } else {
          	if(isMatched == true){
          		sendMessage()
            	$("#message").val("")
            	$("#message").focus()
          	}
        }
    })
    
    $(document).keyup(function(event){
        if ( event.keyCode == 13 ) {
            $("#sendBtn").click();
            return false;
        } else if ( event.keyCode == 27 ) {
            $("#closeBtn").click();
            return false;
        }
   })
})

$(document).ready(function() {
    $("#closeBtn").click(function() {
        sock.close()
    })
})

function startInterval(seconds, callback) { 
	callback() 
	repeatInterval = setInterval(callback, seconds * 1000) 
	return repeatInterval
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

function sendMessage() {
	clearInterval(repeatInterval)
	sock.send($("#message").val())
}

function onMessage(evt) {
    var data = evt.data;
    $("#data").append(data)
    
    var idData = document.getElementById("data"); 
    idData.scrollTop = idData.scrollHeight
}

function onClose(evt) {	
	clearInterval(repeatInterval)
	isMatched = false
	$("#message").val("")
	$("#data").append("Chatting is over.")
	$("#sendBtn").val("Start").attr('disabled', false)
	$("#message").attr('disabled', true)
    $("#closeBtn").attr("type", "hidden")
}