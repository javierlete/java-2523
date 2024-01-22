package com.ipartek.formacion.bibliotecas;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Consola {
	private static final Scanner sc = new Scanner(System.in);
	
	public static final boolean OPCIONAL = true;
	public static final boolean OBLIGATORIO = false;

	public static String leerString(String mensaje, boolean opcional) {
		String texto;
		boolean repetir = true;
		
		do {
			System.out.print(mensaje + ": ");
			texto = sc.nextLine();
			
			if(!opcional && texto.trim().length() == 0) {
				System.out.println("Este dato es obligatorio");
			} else {
				repetir = false;
			}
		} while (repetir);

		return texto.trim().length() > 0 ? texto : null;
	}

	public static String leerString(String mensaje) {
		return leerString(mensaje, OBLIGATORIO);
	}

	public static Long leerLong(String mensaje) {
		return leerLong(mensaje, OBLIGATORIO);
	}
	public static Long leerLong(String mensaje, boolean opcional) {
		boolean hayError = true;
		long l = 0;

		do {
			try {
				String dato = leerString(mensaje, opcional);

				if (dato == null) {
					return null;
				}

				l = Long.parseLong(dato);
				hayError = false;
			} catch (NumberFormatException e) {
				System.out.println("El número debe ser un entero entre " + Long.MIN_VALUE + " y " + Long.MAX_VALUE);
			}
		} while (hayError);

		return l;
	}

	public static Integer leerInt(String mensaje) {
		return leerInt(mensaje, OBLIGATORIO);
	}

	public static Integer leerInt(String mensaje, boolean opcional) {
		boolean hayError = true;
		int i = 0;

		do {
			try {
				String dato = leerString(mensaje, opcional);

				if (dato == null) {
					return null;
				}

				i = Integer.parseInt(dato);
				hayError = false;
			} catch (NumberFormatException e) {
				System.out
						.println("El número debe ser un entero entre " + Integer.MIN_VALUE + " y " + Integer.MAX_VALUE);
			}
		} while (hayError);

		return i;
	}

	public static LocalDate leerFecha(String mensaje) {
		return leerFecha(mensaje, OBLIGATORIO);
	}
	
	public static LocalDate leerFecha(String mensaje, boolean opcional) {
		boolean hayError = true;
		LocalDate fecha = null;

		do {
			try {
				String dato = leerString(mensaje + " [AAAA-MM-DD] ", opcional);

				if (dato == null) {
					return null;
				}
				
				fecha = LocalDate.parse(dato);
				hayError = false;
			} catch (DateTimeParseException e) {
				System.out.println("La fecha debe ser válida");
			}
		} while (hayError);

		return fecha;
	}
}
