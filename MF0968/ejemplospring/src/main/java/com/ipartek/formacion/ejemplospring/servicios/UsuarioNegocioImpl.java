package com.ipartek.formacion.ejemplospring.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.ejemplospring.entidades.Categoria;
import com.ipartek.formacion.ejemplospring.entidades.Producto;
import com.ipartek.formacion.ejemplospring.repositorios.CategoriaRepository;
import com.ipartek.formacion.ejemplospring.repositorios.ProductoRepository;

@Service
public class UsuarioNegocioImpl implements UsuarioNegocio {

	@Autowired
	private ProductoRepository repoProducto;
	
	@Autowired
	private CategoriaRepository repoCategoria;
	
	@Override
	public Iterable<Producto> obtenerProductos() {
		return repoProducto.findAll();
	}

	@Override
	public Iterable<Categoria> obtenerCategorias() {
		return repoCategoria.findAll();
	}

	@Override
	public Categoria obtenerCategoriaPorId(Long id) {
		return repoCategoria.findById(id).orElse(null);
	}
}
