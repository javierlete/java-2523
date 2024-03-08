package uf2216;

import java.math.BigDecimal;

public class ClasesAbstractas {
	public static void main(String[] args) {
		Number[] numeros = new Number[3];
		
		numeros[0] = 5; // new Integer(5);
		numeros[1] = 6.7; // new Double(6.7);
		numeros[2] = new BigDecimal("1234.23");
		
		for(Number n: numeros) {
			System.out.println(n.intValue());
		}
	}
}
