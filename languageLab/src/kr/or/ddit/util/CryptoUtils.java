package kr.or.ddit.util;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class CryptoUtils {
	
	public static KeyPair keyPairGenerateForRSA(int keySize) { // 양방향 공개키를 위한 키 생성
		try {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA"); // 단문데이터, 전자서명
		keyPairGen.initialize(keySize);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		
		return keyPair;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public static String rsaDecryptBase64(String encoded, Key key) { // 양방향 공개키 복호화
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] decoded = Base64.getDecoder().decode(encoded);
			byte[] decrypted = cipher.doFinal(decoded);
			System.out.println(new String(decrypted));
			return new String(decrypted);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String rsaEncryptBase64(String plain, Key key) { // 양방향 공개키 암호화
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] encrypted = cipher.doFinal(plain.getBytes());
			
			String encoded = Base64.getEncoder().encodeToString(encrypted);
			System.out.println(encoded);
			
			return encoded;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static SecretKey generateAESKey(int keySize) { // 전자책 // 양방향 비밀키를 위한 키 생성
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(keySize);
			
			SecretKey key = keyGen.generateKey();
			return key;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public static String aesDecryptBase64(String encoded, String ivValue, SecretKey key) { // 양방향 비밀키 복호화
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] iv = md.digest(ivValue.getBytes());
			IvParameterSpec ivSpec = new IvParameterSpec(iv); //CBC 를 위한 벡터 생성
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			
			byte[] decoded = Base64.getDecoder().decode(encoded);
			System.out.println(decoded);		
			cipher.init(Cipher.DECRYPT_MODE, key, ivSpec); //암호해독 시작
			byte[] decrypted = cipher.doFinal(decoded);
			System.out.println(decrypted);
			System.out.println(new String(decrypted));
			
			return new String(decrypted);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String aesEncryptBase64(String plain, String ivValue, SecretKey key) { //양방향 비밀키 암호화
		try {
//		String ivValue = "아무거나";
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] iv = md.digest(ivValue.getBytes());
		System.out.println(iv.length);
		

		IvParameterSpec ivSpec = new IvParameterSpec(iv); //CBC 를 위한 벡터 생성
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
				//transformation AEC, CBC, --padding
		
		cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec); //암호화 시작
		byte[] encrypted = cipher.doFinal(plain.getBytes());
		String encoded = Base64.getEncoder().encodeToString(encrypted);
		System.out.println(encoded);

		return encoded;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String sha512Encryptic(String plain) throws NoSuchAlgorithmException { // 단방향 암호화 (단방향은 복호화 불가)
		try {
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		byte[] encrypted = md.digest(plain.getBytes());
		String encoeded = Base64.getEncoder().encodeToString(encrypted);
		
		System.out.println(encrypted.length);
		System.out.println(encoeded);
		
		return encoeded;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
