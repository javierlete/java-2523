package com.ipartek.formacion.ejemplosinspring.negocio;

import com.ipartek.formacion.ejemplosinspring.entidades.Producto;

public interface UsuarioNegocio {
	Iterable<Producto> obtenerProductos();
}
