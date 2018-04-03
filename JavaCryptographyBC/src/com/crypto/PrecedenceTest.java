package com.crypto;

import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class PrecedenceTest {
	
	public static void main(String[] args) throws Exception {
		Security.addProvider(new BouncyCastleProvider());
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("Blowfish/ECB/NoPadding");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			throw e;
		}
		
		System.out.println("The provider is "+cipher.getProvider());
		
		try {
			cipher = Cipher.getInstance("Blowfish/ECB/NoPadding", "BC");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			throw e;
		}
		
		System.out.println("The provider is "+cipher.getProvider());
	}

}
