package com.ipartek.formacion.ejemplospring.servicios;

import com.ipartek.formacion.ejemplospring.dtos.CategoriaConProductosDto;
import com.ipartek.formacion.ejemplospring.entidades.Categoria;
import com.ipartek.formacion.ejemplospring.entidades.Producto;

public interface UsuarioNegocio {
	Iterable<Producto> obtenerProductos();
	Iterable<Categoria> obtenerCategorias();
	CategoriaConProductosDto obtenerCategoriaPorId(Long id);
}
