var isUsernameValid = false

function checkJoin() {
	
	var id = document.getElementById("id").value;
	var pw = document.getElementById("pw").value;
	var name = document.getElementById("name").value;
	var email = document.getElementById("email").value;
	var phone = document.getElementById("phone").value;
	
    var idPattern = /[a-z0-9A-Z_-]{3,20}/;
    var pwPattern = /[a-z0-9A-Z_-]{6,20}/;
    var namePattern = /[a-z0-9A-Z_-]{3,20}/;
    var emailPattern = /[a-z0-9A-Z_-]+@[a-z0-9A-Z]+.[a-zA-Z.]+/;
    var phonePattern = /\d{3}-\d{3,4}-\d{4}/;
    
	if(!idPattern.test(id)){
		alert("Username not long enough, 3~20 characters")
		document.joinInfo.id.focus()
		return false
	}
	else if(!isUsernameValid){
		alert("Username already exists")
		document.joinInfo.id.focus()
		return false
	}
	else if(!pwPattern.test(pw)){
		alert("Password not long enough, 6~20 characters")
		document.joinInfo.pw.focus()
		return false
	}
	else if(!namePattern.test(name)){
		alert("Name not long enough, 3~20 characters")
		document.joinInfo.name.focus()
		return false
	}
	else if(!emailPattern.test(email)){
		alert("Invalid email form")
		document.joinInfo.email.focus()
		return false
	}
	else if(!phonePattern.test(phone)){
		alert("Invalid phone number form")
		document.joinInfo.phone.focus()
		return false
	}
	else{
		try {
	        var rsaPublicKeyModulus = document.getElementById("rsaPublicKeyModulus").value;
	        var rsaPublicKeyExponent = document.getElementById("rsaPublicKeyExponent").value;
	        submitEncryptedForm(id,pw,name,email,phone, rsaPublicKeyModulus, rsaPublicKeyExponent);
	    } catch(err) {
	        alert(err);
	    }
	    return false;
	}
}

function submitEncryptedForm(id,pw,name,email,phone, rsaPublicKeyModulus, rsaPpublicKeyExponent) {
    var rsa = new RSAKey();
    rsa.setPublic(rsaPublicKeyModulus, rsaPpublicKeyExponent);

    var securedId = rsa.encrypt(id);
    var securedPw = rsa.encrypt(pw);
    var securedName = rsa.encrypt(name);
    var securedEmail = rsa.encrypt(email);
    var securedPhone = rsa.encrypt(phone);

    var joinInfo = document.getElementById("joinInfo");
    joinInfo.id.value = securedId;
    joinInfo.pw.value = securedPw;
    joinInfo.name.value = securedName;
    joinInfo.email.value = securedEmail;
    joinInfo.phone.value = securedPhone;
    joinInfo.submit();
}

function usernameCheck() {
	
	var id = $("input[name=id]").val();

	if(id.length < 3){
		alert("Username too short!")
		return
	}
	
	$.ajax({
        async: true,
        type : 'POST',
        data : id,
        url : "/member/idCheck",
        dataType : "json",
        contentType: "application/json; charset=UTF-8",
        success : function(data) {
        	if (data.valid == true) {
                isUsernameValid = true;
				alert("Username available!");
            } 
        	else {
                isUsernameValid = false;
                alert("Username unavailable!");
            }
        },
        error:function(request,status,error){
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });
}