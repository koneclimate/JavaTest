package com.javatest.registermember.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashingUtil {

	public static String doHashing(String password) {
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("SHA");
		
		messageDigest.update(password.getBytes());
		   
		   byte[] resultByteArray = messageDigest.digest();
		   
		   StringBuilder sb = new StringBuilder();
		   
		   for (byte b : resultByteArray) {
		    sb.append(String.format("%02x", b));
		   }
		   
		   return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
