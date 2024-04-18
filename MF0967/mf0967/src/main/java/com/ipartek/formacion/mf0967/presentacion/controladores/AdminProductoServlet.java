package com.ipartek.formacion.mf0967.presentacion.controladores;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.ipartek.formacion.mf0967.configuraciones.Globales;
import com.ipartek.formacion.mf0967.entidades.Producto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@WebServlet("/admin/producto")
public class AdminProductoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sId = request.getParameter("id");

		if (sId != null) {
			Long id = Long.valueOf(sId);
			request.setAttribute("producto", Globales.DAO_PRODUCTO.obtenerPorId(id));
		}

		request.getRequestDispatcher("/WEB-INF/vistas/producto.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sId = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		String sPrecio = request.getParameter("precio");
		String urlFoto = request.getParameter("url-foto");

		Long id = sId.isBlank() ? null : Long.valueOf(sId);
		BigDecimal precio = sPrecio.isBlank() ? null : new BigDecimal(sPrecio);
		
		Producto producto = new Producto(id, nombre, precio, urlFoto);
		
        Map<String, String> errores = validarProducto(producto);
        
        if(errores.size() > 0) {
        	request.setAttribute("producto", producto);
        	request.setAttribute("errores", errores);
        	request.getRequestDispatcher("/WEB-INF/vistas/producto.jsp").forward(request, response);

        	return;
        }
        
		if(id != null) {
			Globales.DAO_PRODUCTO.modificar(producto);
		} else {
			Globales.DAO_PRODUCTO.insertar(producto);
		}

		response.sendRedirect(request.getContextPath() + "/admin/productos");
	}

	private Map<String, String> validarProducto(Producto producto) {
		Map<String, String> errores = new HashMap<>();
		
		Validator validator = Globales.VALIDATOR_FACTORY.getValidator();
        Set<ConstraintViolation<Producto>> constraintViolations = validator.validate(producto);
		
        for(ConstraintViolation<Producto> constraintViolation: constraintViolations) {
        	errores.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
        }
        
        return errores;
	}
}
