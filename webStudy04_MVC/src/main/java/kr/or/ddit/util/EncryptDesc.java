package kr.or.ddit.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import kr.or.ddit.util.CryptoUtils;

/**
 * encoding (부호화) : 데이터의 전송이나 저장을 할 때 사용되는 구조, "해당 매체가 이해 할 수 있는 방식"으로 데이터의 표현을 바꾸는 과정이다.
 * 	ex) URL encoding, Base64 encoding
 * 
 * encrypting (암호화) : 허가받지 않은 접근에 의해 데이터가 읽히거나(스니핑) 변환(스푸핑)될 수 없도록 막기 위한 작업이다.
 * 	1. 단방향 암호화 : 복호화가 불가능한 암호화 기법. (해시-hash 함수)
 * 		ex) Sha-512 ...
 * 		hash함수란 : 다양한 길이의 입력데이터를 처리하여, 일정길이의 문자열을 만드는 함수 (글자수 상관없이 결과의 크기가 일정, 충돌의 위험이 잔존한다.)
 * 
 * 	2. 양방향 암호화 : 복호화를 하면 원래의 평문으로 복원 가능한 기법.
 * 		2-1. 비밀키(대칭키) 암호화
 * 			ex) AES
 * 
 * 		2-2. 공개키(비대칭키) 암호화 : 한쌍의 키(공개키, 개인키)로 데이터를 암호화한다. 암호화와 복호화에 서로 반대의 키가 사용된다. 
 * 			ex) RSA
 * 			ex) 개인키는 네이버가 가지고 있고, 공개키는 브라우져가 가지고 있게 된다. 
 * 			네이버->브라우져로 데이터를 전송 할 때, 개인키로 암호화된 것을 브라우져가 공개키로 복호화 한 것을 우리가 보게 되는데
 * 			복호화 한 것 또한 비밀키가 된다.
 * 
 *  		네이버가 보낸 공개키를 이용하려면, 해당 사이트(네이버)에 대한 신뢰(인증서)가 있어야 한다.
 * 
 * @author pc12
 *
 */
public class EncryptDesc {
	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException{
		String plain = "java";
		
		String encoded = CryptoUtils.sha512Encryptic(plain);
		System.out.println(encoded);
		
//		KeyPair keyPair = CryptoUtils.keyPairGenerateForRSA(2048);
//		PrivateKey privateKey = keyPair.getPrivate();
//		PublicKey publicKey = keyPair.getPublic();
//		String encoded = CryptoUtils.rsaEncryptBase64(plain, privateKey);
//		String decryptedStr = CryptoUtils.rsaDecryptBase64(encoded, publicKey);
//		
//		String ivValue = "aaaaaasdasd";
//		SecretKey key = CryptoUtils.generateAESKey(128);
//		String encoded1 = CryptoUtils.aesEncryptBase64(plain, ivValue, key);
//		
//		String decryptedStr1 = CryptoUtils.aesDecryptBase64(encoded1, ivValue, key);
	}
	


	
	
}
