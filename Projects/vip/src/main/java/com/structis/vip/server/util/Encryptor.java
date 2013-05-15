package com.structis.vip.server.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryptor {
	public static String encrypt(String en) {
		if (en != null) {
	        try
	        {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            byte[] messageDigest = md.digest(en.getBytes());            
	            BigInteger number = new BigInteger(1,messageDigest);
	            return number.toString(32);
	        }
	        catch(NoSuchAlgorithmException e)
	        {
	            throw new RuntimeException(e);
	        }        
		} else {
			return null;
		}
	}
	public static void main(String[] args){
		String s1 = encrypt("12$-.reO*");
		String s2 = encrypt("12$-.reO*");
		System.out.println("encryp str 12$reO*: " + encrypt("12$reO*") + s1.equals(s2));
	}
}
