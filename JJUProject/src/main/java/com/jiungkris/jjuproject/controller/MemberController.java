package com.jiungkris.jjuproject.controller;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
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
import com.jiungkris.jjuproject.service.MemberService;
import com.jiungkris.jjuproject.vo.MemberVO;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
 
    @Inject
    MemberService service;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(){
        return "member/loginForm";
    }
     
    @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
    public String loginProcess(HttpSession session, MemberVO dto) {
        String page = "";
        
        if ( session.getAttribute("loginSuccess") != null ) {
            session.removeAttribute("loginSuccess");
        }
        MemberVO vo = service.login(dto);
        if ( vo != null ){
            session.setAttribute("loginSuccess", vo);
            page = "redirect:/";
        }else {
        	page = "redirect:/member/login";
        }
         
        return page;
    }
 
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    
    @RequestMapping(value = "/deactivate")
    public String deactivate(HttpSession session) {
    	
    	 MemberVO vo = (MemberVO) session.getAttribute("loginSuccess");
    	
    	service.deactivate(vo.getId());
    	
    	session.invalidate();
        
        return "redirect:/";
    }
    
    @RequestMapping(value = "/join")
    public String join(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	//rsa start
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
        //rsa end
        
    	return "member/joinForm";
    }
    
    @RequestMapping(value = "/joinProcess", method = RequestMethod.POST)
    public String joinProcess(MemberVO dto, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
        
    	service.join(dto);
    	
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
    	 
    	isUsernameValid = service.idCheck(id);
        map.put("valid", isUsernameValid);
    	
    	return map;
    }
}