package com.crypto;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class TestPolicyJars {
	
	public static void main(String[] args) throws Exception {
		
		byte[] data = {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07};
		
		SecretKey secret64BitKey = new SecretKeySpec(new byte[]{0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07}, "Blowfish");
		
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("Blowfish/ECB/NoPadding");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			throw e;
		}
		
		cipher.init(Cipher.ENCRYPT_MODE, secret64BitKey);
		byte[] encryptedVal = cipher.doFinal(data);
		
		System.out.println("64 bit key encrypted text "+new String(encryptedVal));
		
		SecretKey secret192BitKey = new SecretKeySpec(new byte[]{0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09,
																0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x0a, 0x0b,
																0x0d, 0x0e, 0x0f}, "Blowfish");
		
		cipher.init(Cipher.ENCRYPT_MODE, secret192BitKey);
		encryptedVal = cipher.doFinal(data);
		
		System.out.println("192 bit key encrypted text "+new String(encryptedVal));
	}

}
