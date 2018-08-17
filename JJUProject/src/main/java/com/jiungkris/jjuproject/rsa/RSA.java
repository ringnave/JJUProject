package com.jiungkris.jjuproject.rsa;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class RSA {

    public KeyPair createKeyPair() throws NoSuchAlgorithmException {
    	//set the length of the encryption
        final int keySize = 512;
        
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);      
        return keyPairGenerator.genKeyPair();
    }

    public byte[] encrypt(PublicKey publicKey, String message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);  

        return cipher.doFinal(message.getBytes());  
    }
    
    public byte[] decrypt(PrivateKey privateKey, byte [] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        
        return cipher.doFinal(encrypted);
    }
    
    // usage of main function
//    public static void main(String[] args) throws Exception {
//		
//		  RSA rsa = new RSA();
//		
//		  // create public and private keys
//        KeyPair keyPair = rsa.createKeyPair();
//        PublicKey publicKey = keyPair.getPublic();
//        PrivateKey privateKey = keyPair.getPrivate();
//        
//        // encrypt the message
//        byte [] encrypted = rsa.encrypt(publicKey, "This is a test");     
//        System.out.println(new String(encrypted));
//        
//        // decrypt the message
//        byte[] secret = rsa.decrypt(privateKey, encrypted);                                 
//        System.out.println(new String(secret));
//	}
}