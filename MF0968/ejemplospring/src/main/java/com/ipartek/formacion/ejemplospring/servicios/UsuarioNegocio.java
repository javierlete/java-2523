package com.ipartek.formacion.ejemplospring.servicios;

import com.ipartek.formacion.ejemplospring.entidades.Producto;

public interface UsuarioNegocio {
	Iterable<Producto> obtenerProductos();
}
