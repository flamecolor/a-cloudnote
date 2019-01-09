package util;

import java.security.MessageDigest;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

public class NoteUtil {
	
	public static String createId(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	
	public static String md5(String str){
		try{
			MessageDigest md = 
				MessageDigest.getInstance("MD5");
			byte[] input = str.getBytes();
			byte[] output = md.digest(input);
			System.out.println(output.length);
			return Base64.encodeBase64String(output);
		}catch(Exception ex){
			ex.printStackTrace();
			return "";
		}
	}
	
	public static void main(String[] args){
		System.out.println(createId());
		System.out.println(createId());
		System.out.println(createId());
	}
	
}



