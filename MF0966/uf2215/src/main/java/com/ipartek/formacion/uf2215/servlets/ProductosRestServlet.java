package com.ipartek.formacion.uf2215.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.TreeMap;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String fila = """
				{ "id": %s, "nombre": "%s", "precio": %s, "fechaDeCaducidad": "%s" }
				""";

		String path = request.getPathInfo();

		if (path != null && path.trim().length() != 1) {
			Long id = Long.parseLong(path.replace("/", ""));

			System.out.println(id);
			
			Producto p = productos.get(id);
			
			out.printf(fila, p.getId(), p.getNombre(), p.getPrecio(), p.getFechaDeCaducidad());
			
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
}
