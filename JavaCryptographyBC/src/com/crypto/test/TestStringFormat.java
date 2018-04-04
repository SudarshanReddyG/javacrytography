package com.crypto.test;

public class TestStringFormat {

	public static void main(String[] args) {
		String output1 = String.format("%s = %d", "Sudarshan", 100);
		System.out.println(output1);
		System.out.printf("%d", 93);
		System.out.println();
		System.out.printf("%020d", 93);
		System.out.println();
		System.out.printf("%x", 93);
		System.out.println();
		System.out.printf("%o", 93);
		System.out.println();
		System.out.printf("%#x", 93);
		System.out.println();
		System.out.printf("%#o", 93);
	}

}