package com.ipartek.formacion.uf2215.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/v1/productos/*")
public class ProductosRestServlet extends HttpServlet {
	private static TreeMap<Long, Producto> productos = new TreeMap<>();

	static {
		productos.put(1L, new Producto(1L, "Producto1", new BigDecimal(11), LocalDate.of(2000, 1, 1)));
		productos.put(2L, new Producto(2L, "Producto2", new BigDecimal(22), LocalDate.of(2001, 1, 1)));
		productos.put(3L, new Producto(3L, "Producto3", new BigDecimal(33), LocalDate.of(2002, 1, 1)));
		productos.put(4L, new Producto(4L, "Producto4", new BigDecimal(44), LocalDate.of(2003, 1, 1)));
	}

	private static final long serialVersionUID = 1L;

	private static final String fila = """
			{ "id": %s, "nombre": "%s", "precio": %s, "fechaDeCaducidad": "%s" }
			""";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();

		String path = request.getPathInfo();

		if (path != null && path.trim().length() != 1) {
			Long id = Long.parseLong(path.replace("/", ""));

			System.out.println(id);

			Producto p = productos.get(id);

			if(p != null) {
				out.printf(fila, p.getId(), p.getNombre(), p.getPrecio(), p.getFechaDeCaducidad());
			} else {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}

			return;
		}

		out.println("[");

		for (Producto p : productos.values()) {
			out.printf(fila, p.getId(), p.getNombre(), p.getPrecio(), p.getFechaDeCaducidad());

			if (productos.lastKey() != p.getId()) {
				out.print(",");
			}
		}

		out.println("]");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();
		
		BufferedReader in = request.getReader();

		String json = "";

		String linea;
		while ((linea = in.readLine()) != null) {
			json += linea;
		}

		System.out.println(json);
		
		String patternNombre = "\"nombre\": \"(.*?)\""; // Expresión regular para capturar el valor entre comillas

		Pattern regex = Pattern.compile(patternNombre);
		Matcher matcher = regex.matcher(json);

		matcher.find();
		
		String nombre = matcher.group(1);
		
		System.out.println(nombre);
		
		String patternPrecio = "\"precio\": (.*?),"; // Expresión regular para capturar el valor entre comillas
		
		regex = Pattern.compile(patternPrecio);
		matcher = regex.matcher(json);
		
		matcher.find();
		
		BigDecimal precio = new BigDecimal(matcher.group(1));

		System.out.println(precio);
		
		String patternFecha = "\"fechaDeCaducidad\": \"(.*?)\""; // Expresión regular para capturar el valor entre comillas
		
		regex = Pattern.compile(patternFecha);
		matcher = regex.matcher(json);
		
		matcher.find();
		
		LocalDate fechaDeCaducidad= LocalDate.parse(matcher.group(1));
		
		System.out.println(fechaDeCaducidad);
		
		Long id = productos.size() > 0 ? productos.lastKey() + 1L: 1L;
		
		Producto p = new Producto(id, nombre, precio, fechaDeCaducidad);
		
		System.out.println(p);
		
		productos.put(id, p);
		
		out.printf(fila, p.getId(), p.getNombre(), p.getPrecio(), p.getFechaDeCaducidad());

		response.setStatus(HttpServletResponse.SC_CREATED);
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();
		BufferedReader in = request.getReader();

		String path = request.getPathInfo();

		Long id = Long.parseLong(path.replace("/", ""));
		
		String json = "";

		String linea;
		while ((linea = in.readLine()) != null) {
			json += linea;
		}

		System.out.println(json);
		
		String patternNombre = "\"nombre\": \"(.*?)\""; // Expresión regular para capturar el valor entre comillas

		Pattern regex = Pattern.compile(patternNombre);
		Matcher matcher = regex.matcher(json);

		matcher.find();
		
		String nombre = matcher.group(1);
		
		System.out.println(nombre);
		
		String patternPrecio = "\"precio\": (.*?),"; // Expresión regular para capturar el valor entre comillas
		
		regex = Pattern.compile(patternPrecio);
		matcher = regex.matcher(json);
		
		matcher.find();
		
		BigDecimal precio = new BigDecimal(matcher.group(1));

		System.out.println(precio);
		
		String patternFecha = "\"fechaDeCaducidad\": \"(.*?)\""; // Expresión regular para capturar el valor entre comillas
		
		regex = Pattern.compile(patternFecha);
		matcher = regex.matcher(json);
		
		matcher.find();
		
		LocalDate fechaDeCaducidad= LocalDate.parse(matcher.group(1));
		
		System.out.println(fechaDeCaducidad);
		
		Producto p = new Producto(id, nombre, precio, fechaDeCaducidad);
		
		System.out.println(p);
		
		productos.put(id, p);

		out.printf(fila, p.getId(), p.getNombre(), p.getPrecio(), p.getFechaDeCaducidad());
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getPathInfo();

		if (path != null && path.trim().length() != 1) {
			Long id = Long.parseLong(path.replace("/", ""));

			productos.remove(id);
		}
		
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}
}
