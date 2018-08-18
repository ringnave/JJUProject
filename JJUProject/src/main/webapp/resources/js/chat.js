var sock

$(document).ready(function() {
    $("#sendBtn").click(function() {
        if($("#sendBtn").val() == "Start"){
        	$("#data").empty()
        	sock = new SockJS("/echo")

          	sock.onmessage = onMessage

          	sock.onclose = onClose
          	
          	$("#sendBtn").val("Send")
          	$("#closeBtn").attr("type", "button")
        } else {
        	sendMessage()
        }
    })
})

$(document).ready(function() {
    $("#closeBtn").click(function() {
        sock.close()
    })
})

function sendMessage() {
	sock.send($("#message").val())
}

function onMessage(evt) {
    var data = evt.data;
    $("#data").append(data)
    
    var idData = document.getElementById("data"); 
    idData.scrollTop = idData.scrollHeight
}

function onClose(evt) {
	$("#data").append("Chatting is over.")
	$("#sendBtn").val("Start")
    $("#closeBtn").attr("type", "hidden")
}