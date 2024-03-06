package uf2216;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Clase básica de Java con ejemplos de sintaxis
 */
public class Basico {
	public static void main(String[] args) {
		enum Color {
			ROJO, VERDE, AZUL
		}
		;

		Color color;

		color = Color.VERDE;

		switch (color) {
		case AZUL:
			System.out.println("Es AZUL");
			break;
		default:
			System.out.println(color.name());
			System.out.println(color.ordinal());
		}

		int dias, mes = 6;

		switch (mes) {
		case 2 -> dias = 28;
		case 4, 6, 9, 11 -> dias = 30;
		default -> dias = 31;
		}

		System.out.println(dias);
	}

	@SuppressWarnings("unused")
	public static void mainOperadores(String[] args) {
		System.out.println(5 ^ 3);

		int x;

		System.out.println(x = 5);

		int a, b, c;
		a = b = c = 0;

		System.out.println(x++);
		System.out.println(x);

		System.out.println(++x);
		System.out.println(x);

		a = 5;
		b = 3;

		boolean bool = true;

		System.out.println(bool ? "VERDADERO" : "FALSO");

		System.out.println(a > b ? a : b);

		System.out.println(-20 >> 3); // Rellena con el bit de signo a la izquierda
		System.out.println(-20 << 3);
		System.out.println(-20 >>> 3); // Rellena con ceros a la izquierda siempre
	}

	public static void mainStringBuffer(String[] args) {
		String log = "";

		log += LocalDateTime.now() + ": Una cosa\n";
		log = new StringBuffer(log).append(LocalDateTime.now()).append(": Otra cosa\n").toString();
		log = new StringBuffer(log).append(LocalDateTime.now()).append(": Otra cosa\n").toString();

		System.out.println(log);

		StringBuffer sb = new StringBuffer();

		sb.append(LocalDateTime.now()).append(": Una cosa\n");
		sb.append(LocalDateTime.now()).append(": Otra cosa\n");
		sb.append(LocalDateTime.now()).append(": Otra cosa\n");

		System.out.println(sb.toString());
	}

	public static void mainEquals(String[] args) {
		String texto1 = "Texto";
		String texto2 = "Texto";

		texto1 += "1";
		texto2 += "2";

		// texto2 = new Scanner(System.in).nextLine();

		System.out.println("#" + texto1 + "#");
		System.out.println("#" + texto2 + "#");

		System.out.println(texto1 == texto2); // son el MISMO OBJETO
		System.out.println(texto1.equals(texto2)); // son IGUALES
	}

	public static void mainColecciones(String[] args) {
		List<String> al = new ArrayList<>();

		al.add("UNO");
		al.add("DOS");
		al.add("TRES");
		al.add("CUATRO");
		al.add("TRES");

		al.remove(1);

		al.set(0, "MODIFICADO");

		al.add(2, "INSERTADO");

		System.out.println(al);

		System.out.println(al.get(0));

		System.out.println(al.size());

		Set<String> hs = new HashSet<>();

		System.out.println(hs.add("UNO"));
		System.out.println(hs.add("DOS"));
		System.out.println(hs.add("UNO"));
		System.out.println(hs.add("TRES"));

		System.out.println(hs);
		System.out.println(hs.size());

		Map<String, String> dic = new TreeMap<>();

		dic.put("casa", "house");
		dic.put("perro", "dog");
		dic.put("casa", "home");

		System.out.println(dic);
		System.out.println(dic.keySet());
		System.out.println(dic.values());
		System.out.println(dic.get("perro"));
	}

	public static void mainArrays(String[] args) {
		int[] arr;
		int tamano = 3;
		arr = new int[tamano];

		arr[0] = 5;
		arr[1] = 6;
		arr[2] = 7;
		// arr[3] = 8;

		System.out.println(Arrays.toString(arr));

		char[][] tablero = new char[8][8];

		tablero[0][0] = 't';
		tablero[0][1] = 'c';
		tablero[1][0] = 'p';

		for (int fila = 0; fila < tablero.length; fila++) {
			for (int columna = 0; columna < tablero[fila].length; columna++) {
				if (tablero[fila][columna] != 0) {
					System.out.print(tablero[fila][columna] + " ");
				} else {
					System.out.print(". ");
				}
			}

			System.out.println();
		}
	}

	/**
	 * Método de entrada de la aplicación para la JVM
	 * 
	 * @param args Parámetros recibidos a través de la ejecución de consola
	 */
	@SuppressWarnings("unused")
	public static void mainTiposBasicos(String[] args) {
		/*
		 * Programa sencillo para ilustrar las características básicas de Java
		 */
		System.out.println("Hola"); // Muestra por consola hola

		double d1 = 0.1, d2 = 0.2;

		System.out.println(d1 + d2);

		long l1 = 123123123123L;
		long l2 = 1;

		Long l3 = 1L;

		int binario = 0b100010;
		int octal = 0123;
		int hexadecimal = 0x123;

		System.out.println(binario);
		System.out.println(octal);
		System.out.println(hexadecimal);

		String nombre = "Javier";

		System.out.println(nombre);

		Double d3 = null;

		d3 = d2; // Double.valueOf(d2);

		d1 = d3; // d3.doubleValue();

		BigDecimal bd1 = new BigDecimal("0.1");
		BigDecimal bd2 = new BigDecimal("0.2");
		BigDecimal bd3 = new BigDecimal("0.3");

		System.out.println(bd1.add(bd2));

		System.out.println(bd1.compareTo(bd2) <= 0);

		System.out.println(bd2.divide(bd3, 2, RoundingMode.HALF_UP));

		LocalDate fecha = LocalDate.of(2025, 1, 2);

		String sFecha = "2024-03-05T08:41";

		LocalDateTime fechaHora = LocalDateTime.parse(sFecha);

		System.out.println(fechaHora);

		sFecha = "5/12/2024 8:42";

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/M/yyyy H:mm");

		fechaHora = LocalDateTime.parse(sFecha, dtf);

		System.out.println(fechaHora);

		System.out.println(fechaHora.format(dtf));
	}

}
