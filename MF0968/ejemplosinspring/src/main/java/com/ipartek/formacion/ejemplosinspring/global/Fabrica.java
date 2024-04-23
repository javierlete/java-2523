package com.ipartek.formacion.ejemplosinspring.global;

import java.math.BigDecimal;

import com.ipartek.formacion.ejemplosinspring.accesodatos.DaoProducto;
import com.ipartek.formacion.ejemplosinspring.accesodatos.DaoProductoJpa;
import com.ipartek.formacion.ejemplosinspring.entidades.Producto;
import com.ipartek.formacion.ejemplosinspring.negocio.AdminNegocio;
import com.ipartek.formacion.ejemplosinspring.negocio.AdminNegocioImpl;
import com.ipartek.formacion.ejemplosinspring.negocio.UsuarioNegocio;
import com.ipartek.formacion.ejemplosinspring.negocio.UsuarioNegocioImpl;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;

public class Fabrica {
	private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql");
	
	private static final DaoProducto daoProducto = new DaoProductoJpa();
	private static final AdminNegocio adminNegocio = new AdminNegocioImpl();
	private static final UsuarioNegocio usuarioNegocio = new UsuarioNegocioImpl();
	
	// NO ES BUENA PRÁCTICA PERO NOS SIRVE PARA LAS PRUEBAS
	static {
		// TODO quitarlo en el futuro
		for(int i = 1; i <= 10; i++) {
			adminNegocio.insertarProducto(Producto.builder().nombre("Producto " + i).precio(new BigDecimal(i * 10)).stock(i).build());
		}
	}
	
	public static Object obtenerObjeto(String tipo) {
		switch(tipo) {
		case "validador": 
			return factory.getValidator();
		case "em":
			return emf.createEntityManager();
		case "daoProducto":
			return daoProducto;
		case "negocioAdmin":
			return adminNegocio;
		case "negocioUsuario":
			return usuarioNegocio;
		default:
			throw new UnsupportedOperationException("NO SE CONOCE EL TIPO RECIBIDO: " + tipo);
		}
	}
}
