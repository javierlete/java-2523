package com.ipartek.formacion.uf2216.dal;

import com.ipartek.formacion.uf2216.pojos.Producto;

public interface DaoProducto extends Dao<Producto> {
	Iterable<Producto> buscarPorNombre(String nombre);
}
