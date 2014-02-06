package com.iut;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {
	
	private static final String hashAlgo = "SHA-256";
	
	public static String hashPseudoPassword(String pseudo, String password) {
		
		String concat = pseudo + password;
		MessageDigest md = null;
		try{
			 md = MessageDigest.getInstance(hashAlgo);
		}
		catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
		}
		
		md.update( concat.getBytes() );
		
		byte[] byteHash = md.digest();
		
		StringBuffer sb = new StringBuffer();
		
		for (int i=0; i< byteHash.length; i++){
			sb.append( Integer.toHexString( 0xff & byteHash[i]) );
		}
		
		return sb.toString();
	}
}
