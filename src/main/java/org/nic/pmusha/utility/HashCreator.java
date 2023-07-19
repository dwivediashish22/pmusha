package org.nic.pmusha.utility;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashCreator {

	public static String createMD5Hash(String originalString)
	           throws NoSuchAlgorithmException {
	      String hashtext = null;
	      MessageDigest digest = MessageDigest.getInstance("SHA-256");
	      byte[] encodedhash = digest.digest(
	        originalString.toLowerCase().getBytes(StandardCharsets.UTF_8));

	      // Compute message digest of the input
	      hashtext = bytesToHex(encodedhash);

	      return hashtext;
	   }

	   private static String bytesToHex(byte[] hash) {
		    StringBuilder hexString = new StringBuilder(2 * hash.length);
		    for (int i = 0; i < hash.length; i++) {
		        String hex = Integer.toHexString(0xff & hash[i]);
		        if(hex.length() == 1) {
		            hexString.append('0');
		        }
		        hexString.append(hex);
		    }
		    return hexString.toString();
		}
	   
	   public static void main (String []args) throws NoSuchAlgorithmException {
		   System.out.println(HashCreator.createMD5Hash("kamal.danish010@gmail.com"));
		   System.out.println(HashCreator.createMD5Hash("kamal.danish010@gmail.com"));
		   System.out.println(HashCreator.createMD5Hash("kamal.danish010@gmail.com"));
		   System.out.println(HashCreator.createMD5Hash("kamal.danish010@gmail.com"));
		   System.out.println(HashCreator.createMD5Hash("kamal.danish010@gmail.com"));
	   }
	 
	}