package com.ipartek.formacion.ejemplosinspring.accesodatos;

import com.ipartek.formacion.ejemplosinspring.entidades.Producto;

public class DaoProductoJpa extends AccesoDatosJpa implements DaoProducto {

	@Override
	public Iterable<Producto> obtenerTodos() {
		return enTransaccion(em -> em.createQuery("from Producto", Producto.class).getResultList());
	}

	@Override
	public Producto insertar(Producto producto) {
		return enTransaccion(em -> {
			em.persist(producto);
			return producto;
		});
	}
}
