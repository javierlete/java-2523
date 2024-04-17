package com.ipartek.formacion.fullstack.presentacion.filtros;

import java.io.IOException;

import com.ipartek.formacion.fullstack.dtos.AlumnoLoginDto;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/alumno/*")
public class AlumnoFilter extends HttpFilter implements Filter {
    
	private static final long serialVersionUID = -7537231824256036060L;

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		HttpSession session = request.getSession();
		AlumnoLoginDto alumno = (AlumnoLoginDto) session.getAttribute("alumno");
		
		if(alumno == null) {
			request.setAttribute("error", "Debes estar logueado como alumno para ver tus cursos");
			request.getRequestDispatcher("/WEB-INF/vistas/login.jsp").forward(request, response);
			
			return;
		}
		
		chain.doFilter(request, response);
	}

}
