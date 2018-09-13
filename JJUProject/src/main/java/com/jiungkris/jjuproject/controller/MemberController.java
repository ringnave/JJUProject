package com.jiungkris.jjuproject.controller;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiungkris.jjuproject.rsa.RSA;
import com.jiungkris.jjuproject.service.AlarmService;
import com.jiungkris.jjuproject.service.CurrentService;
import com.jiungkris.jjuproject.service.MemberService;
import com.jiungkris.jjuproject.service.RecordDialogueService;
import com.jiungkris.jjuproject.util.TimerForLogout;
import com.jiungkris.jjuproject.vo.MemberVO;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
 
    @Inject
    MemberService memberService;
    
    @Inject
    CurrentService currentService;
    
    @Inject
    RecordDialogueService recordDialogueService;

    @Inject
    AlarmService alarmService;
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception{
    	
    	//rsa encode start
    	RSA rsa = new RSA();
    	KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    	
        KeyPair keyPair = rsa.createKeyPair();
        
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        
        session = request.getSession();
        session.setAttribute("privateKey", privateKey);
        
        RSAPublicKeySpec publicSpec = (RSAPublicKeySpec) keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
    	
        String publicKeyModulus = publicSpec.getModulus().toString(16);
        String publicKeyExponent = publicSpec.getPublicExponent().toString(16);
        
        request.setAttribute("publicKeyModulus", publicKeyModulus);
        request.setAttribute("publicKeyExponent", publicKeyExponent);
        
        request.getRequestDispatcher("/WEB-INF/views/member/loginForm.jsp").forward(request, response);
        //rsa encode end
    	
        return "member/loginForm";
    }
     
    @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	public String loginProcess(MemberVO dto, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		
		String page = "";
	    
	    //rsa decode start
		String securedId = request.getParameter("id");
		String securedPw = request.getParameter("pw");
		session = request.getSession();
	    PrivateKey privateKey = (PrivateKey) session.getAttribute("privateKey");
	    session.removeAttribute("privateKey");
	    
	    if (privateKey == null) {
	        throw new RuntimeException("No Private Key");
	    }
	    
	    try {            
	        dto.setId(decryptRsa(privateKey, securedId));
	        dto.setPw(decryptRsa(privateKey, securedPw));
	    } catch (Exception ex) {
	        throw new ServletException(ex.getMessage(), ex);
	    }
	    //rsa decode end
	    
	    if ( session.getAttribute("loginSuccess") != null ) {
	        session.removeAttribute("loginSuccess");
	    }
	    
	    MemberVO vo = memberService.login(dto);
	    if ( vo != null ){
	    	session.setAttribute("loginSuccess", vo);
	    	TimerForLogout timerForLogout = new TimerForLogout(session);
	    	session.setAttribute("timerForLogout", timerForLogout);
	        currentService.login(vo.getId());
	        
	        page = "redirect:/";
	    }else {
	    	session.setAttribute("loginFalse", true);
	    	page = "redirect:/member/login";
	    }
	     
	    return page;
	}
 
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
    	MemberVO vo = (MemberVO) session.getAttribute("loginSuccess");
    	TimerForLogout timerForLogout = (TimerForLogout) session.getAttribute("timerForLogout");
    	
    	session.removeAttribute("loginSuccess");
		session.removeAttribute("timerForLogout");
		timerForLogout.cancel();
		
        currentService.logout(vo.getId());
        
    	return "redirect:/";
    }
    
    @RequestMapping(value = "/deactivate")
    public String deactivate(HttpSession session) {
    	MemberVO vo = (MemberVO) session.getAttribute("loginSuccess");
    	
    	// Delete all records of dialogues and alarm function
    	recordDialogueService.deleteAccountInDB(vo);
    	alarmService.deleteAccountInDB(vo);
    	
    	memberService.deactivate(vo.getId());
    	session.invalidate();
        return "redirect:/";
    }
    
    @RequestMapping(value = "/join")
    public String join(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	//rsa encode start
    	RSA rsa = new RSA();
    	KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    	
        KeyPair keyPair = rsa.createKeyPair();
        
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        
        session = request.getSession();
        session.setAttribute("privateKey", privateKey);
        
        RSAPublicKeySpec publicSpec = (RSAPublicKeySpec) keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
    	
        String publicKeyModulus = publicSpec.getModulus().toString(16);
        String publicKeyExponent = publicSpec.getPublicExponent().toString(16);
        
        request.setAttribute("publicKeyModulus", publicKeyModulus);
        request.setAttribute("publicKeyExponent", publicKeyExponent);
        
        request.getRequestDispatcher("/WEB-INF/views/member/joinForm.jsp").forward(request, response);
        //rsa encode end
        
    	return "member/joinForm";
    }
    
    @RequestMapping(value = "/joinProcess", method = RequestMethod.POST)
    public String joinProcess(MemberVO dto, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	//rsa decode start
    	String securedId = request.getParameter("id");
    	String securedPw = request.getParameter("pw");
    	String securedName = request.getParameter("name");
    	String securedEmail = request.getParameter("email");
    	String securedPhone = request.getParameter("phone");
    	session = request.getSession();
        PrivateKey privateKey = (PrivateKey) session.getAttribute("privateKey");
        session.removeAttribute("privateKey");
        
        if (privateKey == null) {
            throw new RuntimeException("No Private Key");
        }
        
        try {            
            dto.setId(decryptRsa(privateKey, securedId));
            dto.setPw(decryptRsa(privateKey, securedPw));
            dto.setName(decryptRsa(privateKey, securedName));
            dto.setEmail(decryptRsa(privateKey, securedEmail));
            dto.setPhone(decryptRsa(privateKey, securedPhone));
        } catch (Exception ex) {
            throw new ServletException(ex.getMessage(), ex);
        }
        //rsa decode end
        
    	memberService.join(dto);
    	
    	// Creating column of dialogue records and alarm function
    	recordDialogueService.insertAccountInDB(dto);
    	alarmService.insertAccountInDB(dto);
    	return "redirect:/";
    }
    
    private String decryptRsa(PrivateKey privateKey, String securedValue) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		byte[] encryptedBytes = HexToByteArray(securedValue);
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
		String decryptedValue = new String(decryptedBytes, "utf-8");
		return decryptedValue;
	}

	private byte[] HexToByteArray(String hex) {
		if(hex == null || hex.length() % 2 != 0) return new byte[] {};
		
		byte[] bytes = new byte[hex.length()/2];
		
		for(int i=0; i<hex.length(); i+=2) {
			byte value = (byte) Integer.parseInt(hex.substring(i, i+2), 16);
			bytes[(int) Math.floor(i/2)] = value;
		}
		
		return bytes;
	}

	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> idCheck(@RequestBody String id) {
    	boolean isUsernameValid = false;
    	
    	Map<Object, Object> map = new HashMap<Object, Object>();
    	 
    	isUsernameValid = memberService.idCheck(id);
        map.put("valid", isUsernameValid);
    	
    	return map;
    }
}