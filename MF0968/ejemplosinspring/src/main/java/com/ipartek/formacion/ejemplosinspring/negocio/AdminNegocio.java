package com.ipartek.formacion.ejemplosinspring.negocio;

import com.ipartek.formacion.ejemplosinspring.entidades.Producto;

public interface AdminNegocio extends UsuarioNegocio {
	Producto insertarProducto(Producto producto);
}
