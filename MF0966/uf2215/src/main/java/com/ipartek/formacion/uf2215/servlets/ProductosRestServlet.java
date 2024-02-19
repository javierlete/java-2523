package com.ipartek.formacion.uf2215.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.TreeMap;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
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

	private static final Jsonb json = JsonbBuilder.create();
	
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
				out.println(json.toJson(p));
			} else {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}

			return;
		}

		out.println(json.toJson(productos.values()));
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();

		Long id = productos.size() > 0 ? productos.lastKey() + 1L: 1L;
		
		Producto p = json.fromJson(request.getInputStream(), Producto.class);

		p.setId(id);
		
		System.out.println(p);
		
		productos.put(id, p);
		
		out.println(json.toJson(p));

		response.setStatus(HttpServletResponse.SC_CREATED);
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();

		String path = request.getPathInfo();

		Long id = Long.parseLong(path.replace("/", ""));
		
		Producto p = json.fromJson(request.getInputStream(), Producto.class);
		
		System.out.println(p);
		
		productos.put(id, p);

		out.println(json.toJson(p));
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
