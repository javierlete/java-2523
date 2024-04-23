package com.ipartek.formacion.ejemplosinspring.presentacion.controladores;

import java.io.IOException;

import com.ipartek.formacion.ejemplosinspring.global.Fabrica;
import com.ipartek.formacion.ejemplosinspring.negocio.UsuarioNegocio;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/index")
public class ListadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final UsuarioNegocio un = (UsuarioNegocio) Fabrica.obtenerObjeto("negocioUsuario");

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("productos", un.obtenerProductos());
		
		request.getRequestDispatcher("/WEB-INF/vistas/index.jsp").forward(request, response);
	}
}
