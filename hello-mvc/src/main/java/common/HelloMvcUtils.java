package common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;

public class HelloMvcUtils {

	/**
	 * 단방향 알고리즘 중 우리가 쓸 수 있는 것.
	 * 	- SHA256
	 * 	- SHA512
	 * 	- SHA1 또는 MD5는 사용하지 말것.
	 * 
	 * @param password
	 * @param salt 
	 * @return
	 */
	public static String encrypt(String password, String salt) {
		
		// 1. 암호화 Hasing
		MessageDigest md = null;
		byte[] encrypted = null;
		try {
			md = MessageDigest.getInstance("SHA-512");
			byte[] input = password.getBytes("utf-8");
			byte[] saltBytes = salt.getBytes("utf-8");
			md.update(saltBytes);   // salt 값으로 MessageDigest 객체 갱신
			encrypted = md.digest(input); // MessageDigest 객체에 raw password 전달 및 hasing
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		// 2. 인코딩 (단순문자변환)
		Encoder encoder = Base64.getEncoder();
		
		
		return encoder.encodeToString(encrypted);
	}
	
	
}
