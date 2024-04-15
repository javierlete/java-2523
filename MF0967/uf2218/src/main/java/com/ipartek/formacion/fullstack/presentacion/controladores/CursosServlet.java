package com.ipartek.formacion.fullstack.presentacion.controladores;

import java.io.IOException;

import com.ipartek.formacion.fullstack.configuraciones.Globales;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cursos")
public class CursosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var cursos = Globales.daoCurso.obtenerTodos();
		
		request.setAttribute("cursos", cursos);
		
		request.getRequestDispatcher("/WEB-INF/vistas/cursos.jsp").forward(request, response);
	}

}
