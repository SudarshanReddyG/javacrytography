package com.crypto.symmetric;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class SymmetricPadding {

	public static void main(String[] args) throws Exception {
		
		Security.addProvider(new BouncyCastleProvider());
		byte[]  inputData = new byte[] {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
                						0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f,
                						0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17 };
		
		byte[]  keyBytes = new byte[] {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
                						0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f,
                						0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17 };
		
		System.out.println("The input text is "+convertByteToHex(inputData)+" with length "+inputData.length);
		SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");
		
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");
		} catch (NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException e) {
			throw e;
		}
		
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		
		System.out.println("The block size is "+cipher.getBlockSize());
		
		byte[] cipherText = new byte[cipher.getOutputSize(inputData.length)];
		
		int cipherLen = cipher.update(inputData, 0, inputData.length, cipherText, 0);
		
		cipherLen += cipher.doFinal(cipherText, cipherLen);
		
		System.out.println("The cipher text is "+convertByteToHex(cipherText)+" with length "+cipherText.length);
		
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		
		byte[] plainText = new byte[cipher.getOutputSize(cipherText.length)];
		
		int plainTxtLen = cipher.update(cipherText, 0, cipherText.length, plainText, 0);
		
		plainTxtLen += cipher.doFinal(plainText, plainTxtLen);
		
		System.out.println("The plain text is "+convertByteToHex(plainText)+" with length "+plainText.length);
	}
	
	private static String convertByteToHex(byte[] data) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i=0;i<data.length;i++) {
			String str = String.format("%02x", data[i]);
			stringBuffer.append(str);
		}
		return stringBuffer.toString();
	}

}
