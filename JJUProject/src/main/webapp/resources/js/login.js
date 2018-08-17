function checkLogin() {
	
	var id = document.getElementById("id").value;
	var pw = document.getElementById("pw").value;
	
    var idPattern = /[a-z0-9A-Z_-]{3,20}/;
    var pwPattern = /[a-z0-9A-Z_-]{6,20}/;
    
	if(!idPattern.test(id)){
		alert("Username not long enough, 3~20 characters")
		document.joinInfo.id.focus()
		return false
	}
	else if(!pwPattern.test(pw)){
		alert("Password not long enough, 6~20 characters")
		document.joinInfo.pw.focus()
		return false
	}
	else{
		try {
	        var rsaPublicKeyModulus = document.getElementById("rsaPublicKeyModulus").value;
	        var rsaPublicKeyExponent = document.getElementById("rsaPublicKeyExponent").value;
	        submitEncryptedForm(id,pw, rsaPublicKeyModulus, rsaPublicKeyExponent);
	    } catch(err) {
	        alert(err);
	    }
	    return false;
	}
}

function submitEncryptedForm(id,pw, rsaPublicKeyModulus, rsaPpublicKeyExponent) {
    var rsa = new RSAKey();
    rsa.setPublic(rsaPublicKeyModulus, rsaPpublicKeyExponent);

    var securedId = rsa.encrypt(id);
    var securedPw = rsa.encrypt(pw);
    var loginInfo = document.getElementById("loginInfo");
    loginInfo.id.value = securedId;
    loginInfo.pw.value = securedPw;
    loginInfo.submit();
}