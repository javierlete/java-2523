package com.ipartek.formacion.bibliotecas;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Consola {
	private static final Scanner sc = new Scanner(System.in);

	public static String leerString(String mensaje) {
		System.out.print(mensaje + ": ");
		return sc.nextLine();
	}

	public static long leerLong(String mensaje) {
		boolean hayError = true;
		long l = 0;
		
		do {
			try {
				String dato = leerString(mensaje);
				l = Long.parseLong(dato);
				hayError = false;
			} catch (NumberFormatException e) {
				System.out.println("El número debe ser un entero entre " + Long.MIN_VALUE + " y " + Long.MAX_VALUE);
			}
		} while (hayError);

		return l;
	}

	public static int leerInt(String mensaje) {
		boolean hayError = true;
		int i = 0;
		
		do {
			try {
				String dato = leerString(mensaje);
				i = Integer.parseInt(dato);
				hayError = false;
			} catch (NumberFormatException e) {
				System.out.println("El número debe ser un entero entre " + Integer.MIN_VALUE + " y " + Integer.MAX_VALUE);
			}
		} while (hayError);

		return i;
	}

	public static LocalDate leerFecha(String mensaje) {
		boolean hayError = true;
		LocalDate fecha = null;
		
		do {
			try {
				String dato = leerString(mensaje + " [AAAA-MM-DD] ");				
				fecha = LocalDate.parse(dato);
				hayError = false;
			} catch (DateTimeParseException e) {
				System.out.println("La fecha debe ser válida");
			}
		} while (hayError);
		
		return fecha;
	}
}
