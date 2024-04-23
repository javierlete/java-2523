package com.ipartek.formacion.ejemplosinspring.negocio;

import com.ipartek.formacion.ejemplosinspring.accesodatos.DaoProducto;
import com.ipartek.formacion.ejemplosinspring.entidades.Producto;
import com.ipartek.formacion.ejemplosinspring.global.Fabrica;

import lombok.extern.java.Log;

@Log
public class UsuarioNegocioImpl implements UsuarioNegocio {
	private final static DaoProducto dao = (DaoProducto) Fabrica.obtenerObjeto("daoProducto");
	
	// private final static Logger log = Logger.getLogger(UsuarioNegocio.class.getName());
	
	@Override
	public Iterable<Producto> obtenerProductos() {
		log.info("Se han pedido productos");
		return dao.obtenerTodos();
	}

}
