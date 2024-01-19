package com.ipartek.formacion.bibliotecas;

import java.time.LocalDate;
import java.util.Scanner;

public class Consola {
	private static final Scanner sc = new Scanner(System.in);
	
	public static String leerString(String mensaje) {
		System.out.print(mensaje + ": ");
		return sc.nextLine();
	}
	
	public static long leerLong(String mensaje) {
		String dato = leerString(mensaje);
		return Long.parseLong(dato);
	}

	public static int leerInt(String mensaje) {
		String dato = leerString(mensaje);
		return Integer.parseInt(dato);
	}
	
	public static LocalDate leerFecha(String mensaje) {
		String dato = leerString(mensaje + " [AAAA-MM-DD] ");
		return LocalDate.parse(dato);
	}
}
