package com.ipartek.formacion.ejemplospring.servicios;

import com.ipartek.formacion.ejemplospring.entidades.Categoria;
import com.ipartek.formacion.ejemplospring.entidades.Producto;

public interface AdminNegocio extends UsuarioNegocio {
	Producto insertarProducto(Producto producto);
	Categoria insertarCategoria(Categoria categoria);
}
