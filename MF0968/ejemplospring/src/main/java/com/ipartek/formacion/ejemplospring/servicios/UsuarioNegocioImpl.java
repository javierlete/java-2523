package com.ipartek.formacion.ejemplospring.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.ejemplospring.entidades.Producto;
import com.ipartek.formacion.ejemplospring.repositorios.ProductoRepository;

@Service
public class UsuarioNegocioImpl implements UsuarioNegocio {

	@Autowired
	private ProductoRepository repo;
	
	@Override
	public Iterable<Producto> obtenerProductos() {
		return repo.findAll();
	}

}
