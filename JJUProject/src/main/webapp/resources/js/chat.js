$(document).ready(function() {
    $("#sendBtn").click(function() {
      	console.log("btn")
        sendMessage()
    })
})

var sock = new SockJS("/echo")

sock.onmessage = onMessage

sock.onclose = onClose

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
	$("#data").append("is out")
}