package com.ipartek.formacion.ejemplospring.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.ejemplospring.entidades.Producto;
import com.ipartek.formacion.ejemplospring.repositorios.ProductoRepository;

import lombok.extern.java.Log;

@Log
@Service
@Primary
public class AdminNegocioImpl extends UsuarioNegocioImpl implements AdminNegocio {

	@Autowired
	private ProductoRepository repo;
	
	@Override
	public Producto insertarProducto(Producto producto) {
		log.info("Insertando el producto " + producto);
		producto.setId(null);
		return repo.save(producto);
	}

}
