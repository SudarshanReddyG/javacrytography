package com.crypto;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class SimpleProviderTest {

	public static void main(String[] args) {
		Security.addProvider(new BouncyCastleProvider());
		
		String providerName = "BC";
		
		if(Security.getProvider(providerName) == null) {
			System.out.println(providerName + " provider not installed.");
		} else {
			System.out.println(providerName + " provider is installed.");
		}
	}

}
