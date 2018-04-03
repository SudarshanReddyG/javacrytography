package com.crypto;

import java.security.Provider;
import java.security.Security;
import java.util.Iterator;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class ListBCCapabilities {

	public static void main(String[] args) {
		Security.addProvider(new BouncyCastleProvider());
		
		String providerName = "BC";
		
		Provider provider = Security.getProvider(providerName);
		
		Iterator<Object> iterator = provider.keySet().iterator();
		
		while(iterator.hasNext()) {
			String entry = (String)iterator.next();
			 // this indicates the entry actually refers to another entry

            if (entry.startsWith("Alg.Alias.")) {
                entry = entry.substring("Alg.Alias.".length());
            }

            String factoryClass = entry.substring(0, entry.indexOf('.'));
            String name = entry.substring(factoryClass.length() + 1);

            System.out.println(factoryClass + ": " + name);
		}
	}

}
