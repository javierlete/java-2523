package com.ipartek.formacion.fullstack.presentacion.controladores;

import java.io.IOException;

import com.ipartek.formacion.fullstack.configuraciones.Globales;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/listado")
public class ListadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sId = request.getParameter("id");

		Long id = Long.valueOf(sId);

		var alumnos = Globales.daoCurso.alumnos(id);
		var curso = Globales.daoCurso.obtenerPorId(id);

		request.setAttribute("alumnos", alumnos);
		request.setAttribute("curso", curso);

		request.getRequestDispatcher("/WEB-INF/vistas/listado.jsp").forward(request, response);
	}

}
