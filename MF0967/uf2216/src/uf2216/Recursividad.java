package uf2216;

import java.math.BigInteger;

public class Recursividad {
	public static void main(String[] args) {
		System.out.println(factorial(30));
		System.out.println(factorial(new BigInteger("10000")));
		System.out.println(factorial(new BigInteger("10000")).toString().length());
	}

	/*
	 * 3! = 3 * 2 * 1
	 * 
	 * 3! = 3 * 2!
	 * 
	 * 2! = 2 * 1!
	 * 
	 * 1! = 1
	 */
	public static long factorial(long l) {
		if(l == 1) {
			return 1;
		}
		
		return l * factorial(l - 1);
	}

	public static BigInteger factorial(BigInteger b) {
		if(b.compareTo(BigInteger.ONE) == 0) {
			return BigInteger.ONE;
		}
		
		return b.multiply(factorial(b.subtract(BigInteger.ONE)));
	}
}
