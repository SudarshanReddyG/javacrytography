package com.crypto.symmetric;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class SymmetricEncDec {
	
	public static void main(String[] args) throws Exception {
		Security.addProvider(new BouncyCastleProvider());
		
		byte[] inputData = new byte[] {0x00, 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77,
                						(byte)0x88, (byte)0x99, (byte)0xaa, (byte)0xbb,
                						(byte)0xcc, (byte)0xdd, (byte)0xee, (byte)0xff 
                					  };
		
		byte[] keyBytes = new byte[] {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
                					  0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f,
                					  0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17 
                					  };
		
		System.out.println("This example has input data same as block size.");
		
		System.out.println("The input text "+ convertToHex(inputData) +" having length "+inputData.length);
		
		SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");
		
		Cipher cipher = null;
		
		try {
			cipher = Cipher.getInstance("AES/ECB/NoPadding", "BC");
		} catch (NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException e) {
			throw e;
		}
		
		byte[] cipherText = new byte[inputData.length];
		
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		
		System.out.println("The block size is "+cipher.getBlockSize());
		
		//Updation will take place in parts and the dofinal method will have final decryption/encryption with proper byte and length
		int ctTextLen = cipher.update(inputData, 0, inputData.length, cipherText, 0);
		
		ctTextLen += cipher.doFinal(cipherText, ctTextLen);
		
		System.out.println("The cipher text "+ convertToHex(cipherText) +" having length "+ctTextLen);
		
		byte[] plainText = new byte[ctTextLen];
		
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		
		//Updation will take place in parts and the dofinal method will have final decryption/encryption with proper byte and length
		int plainTxtLen = cipher.update(cipherText, 0, cipherText.length, plainText, 0);
		
		plainTxtLen += cipher.doFinal(plainText, plainTxtLen);
		
		System.out.println("The output text "+ convertToHex(plainText) +" having length "+ctTextLen);
	}
	
	public static String convertToHex(byte[] data) {
		StringBuffer strBuffer = new StringBuffer();
		for (byte b : data) {
            String st = String.format("%02X", b);
            strBuffer.append(st);
        }
		return strBuffer.toString();
	}

}
