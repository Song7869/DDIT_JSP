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

	public static KeyPair keyPairGenerateForRSA(int keySize) {
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
			keyPairGen.initialize(keySize);
			KeyPair keyPair = keyPairGen.generateKeyPair();
			return keyPair;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String rsaDecryptBase64(String encoded, Key key) {
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] decoded = Base64.getDecoder().decode(encoded);
			byte[] decrypted = cipher.doFinal(decoded);
			System.out.println(new String(decrypted));	
			return new String(decrypted);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String rsaEncryptBase64(String plain, Key key) {
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] encrypted = cipher.doFinal(plain.getBytes());
			String encoded = Base64.getEncoder().encodeToString(encrypted);
			System.out.println(encoded);
			return encoded;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static SecretKey generateAESKey(int keySize) {
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(keySize);
			
			SecretKey key = keyGen.generateKey();
			return key;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String aesDecryptBase64(String encoded, String ivValue, SecretKey key) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] iv = md.digest(ivValue.getBytes());
			IvParameterSpec ivSpec = new IvParameterSpec(iv);
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			
			byte[] decoded = Base64.getDecoder().decode(encoded);
			cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
			byte[] decrypted = cipher.doFinal(decoded);
			System.out.println(new String(decrypted));
			return new String(decrypted);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String aesEncryptBase64(String plain, String ivValue, SecretKey key) {
		try {
	//		String ivValue = "아무거나";
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] iv = md.digest(ivValue.getBytes());
			System.out.println(iv.length);
	//		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
	//		keyGen.init(128);
			
	//		SecretKey key = keyGen.generateKey();
			IvParameterSpec ivSpec = new IvParameterSpec(iv);
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
			byte[] encrypted = cipher.doFinal(plain.getBytes());
			String encoded = Base64.getEncoder().encodeToString(encrypted);
			System.out.println(encoded);
			
			return encoded;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String sha512EncryptBase64(String plain) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] encrypted = md.digest(plain.getBytes());
			String encoded = Base64.getEncoder().encodeToString(encrypted);
			System.out.println(encrypted.length);
			System.out.println(encoded);
			return encoded;
		}catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
