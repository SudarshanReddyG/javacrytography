package com.crypto.symmetric;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class SimpleECBExample {

	public static void main(String[] args) throws Exception {
		
		Security.addProvider(new BouncyCastleProvider());
		byte[] inputData = new byte[] {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
                					   0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f,
                					   0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07 };
		byte[] keyBytes = new byte[] { 0x01, 0x23, 0x45, 0x67,
                					   (byte)0x89, (byte)0xab, (byte)0xcd, (byte)0xef };
		
		SecretKey secretKey = new SecretKeySpec(keyBytes, "DES");
		
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("DES/ECB/PKCS7Padding", "BC");
		} catch (NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException e) {
			throw e;
		}
		
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		
		System.out.println("The block size is "+cipher.getBlockSize());
		
		byte[] cipherText = new byte[inputData.length];
		
		int cipherLen = 
	}

}
