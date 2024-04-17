package com.ipartek.formacion.fullstack.presentacion.controladores;

import java.io.IOException;

import com.ipartek.formacion.fullstack.configuraciones.Globales;
import com.ipartek.formacion.fullstack.dtos.AlumnoLoginDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/alumno/cursos")
public class AlumnoCursosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		AlumnoLoginDto alumno = (AlumnoLoginDto) session.getAttribute("alumno");

		var cursos = Globales.daoAlumno.cursos(alumno.id());

		request.setAttribute("cursos", cursos);

		request.getRequestDispatcher("/WEB-INF/vistas/alumno-cursos.jsp").forward(request, response);
	}

}
