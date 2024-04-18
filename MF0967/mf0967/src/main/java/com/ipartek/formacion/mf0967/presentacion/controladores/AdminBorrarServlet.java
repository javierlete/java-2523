package com.ipartek.formacion.mf0967.presentacion.controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ipartek.formacion.mf0967.configuraciones.Globales;

@WebServlet("/admin/borrar")
public class AdminBorrarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sId=request.getParameter("id");
		Long id = Long.valueOf(sId);
		Globales.DAO_PRODUCTO.borrar(id);
		
		response.sendRedirect(request.getContextPath() + "/admin/productos");
	}
}
