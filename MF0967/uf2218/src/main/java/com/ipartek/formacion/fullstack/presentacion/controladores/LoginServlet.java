package com.ipartek.formacion.fullstack.presentacion.controladores;

import java.io.IOException;

import com.ipartek.formacion.fullstack.configuraciones.Globales;
import com.ipartek.formacion.fullstack.dtos.AlumnoLoginDto;
import com.ipartek.formacion.fullstack.dtos.LoginDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/vistas/login.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		LoginDto login = new LoginDto(email, password);
		
		AlumnoLoginDto alumno = validarLogin(login);
		
		if(alumno != null) {
			HttpSession session = request.getSession();
			
			session.setAttribute("alumno", alumno);
			
			response.sendRedirect("cursos");
		} else {
			request.setAttribute("error", "El usuario o la contraseña son incorrectos");
			request.getRequestDispatcher("/WEB-INF/vistas/login.jsp").forward(request, response);
		}
		
	}

	private AlumnoLoginDto validarLogin(LoginDto login) {
		AlumnoLoginDto alumno = Globales.daoAlumno.buscarPorEmail(login.email());
		
		return alumno != null && alumno.password().equals(login.password()) ? alumno : null;
	}
}
