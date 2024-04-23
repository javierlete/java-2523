package com.ipartek.formacion.ejemplosinspring.negocio;

import com.ipartek.formacion.ejemplosinspring.accesodatos.DaoProducto;
import com.ipartek.formacion.ejemplosinspring.entidades.Producto;
import com.ipartek.formacion.ejemplosinspring.global.Fabrica;

import lombok.extern.java.Log;

@Log
public class AdminNegocioImpl extends UsuarioNegocioImpl implements AdminNegocio {
	private final static DaoProducto dao = (DaoProducto) Fabrica.obtenerObjeto("daoProducto");
	
	@Override
	public Producto insertarProducto(Producto producto) {
		log.info("Se va a crear un nuevo producto " + producto);
		return dao.insertar(producto);
	}

}
