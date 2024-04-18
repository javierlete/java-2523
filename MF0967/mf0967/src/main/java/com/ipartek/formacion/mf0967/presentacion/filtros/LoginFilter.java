package com.ipartek.formacion.mf0967.presentacion.filtros;

import java.io.IOException;

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

@WebFilter("/admin/*")
public class LoginFilter extends HttpFilter implements Filter {
    
	private static final long serialVersionUID = 6648760254760475252L;

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("usuario") != null) {
			chain.doFilter(request, response);
			return;
		}
		
		request.setAttribute("error", "Debes estar logueado para entrar en administración");
		request.getRequestDispatcher("/login").forward(request, response);
	}

}
