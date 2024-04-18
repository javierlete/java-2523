package com.ipartek.formacion.mf0967.presentacion.controladores;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/vistas/login.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if("admin@email.net".equals(email) && "contra".equals(password)) {
			request.getSession().setAttribute("usuario", email);
			
			response.sendRedirect(request.getContextPath() + "/admin/productos");
			
			return;
		}
		
		request.setAttribute("error", "El usuario o contraseña son incorrectos");
		request.getRequestDispatcher("/WEB-INF/vistas/login.jsp").forward(request, response);
	}
}
