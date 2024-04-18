package com.ipartek.formacion.mf0967.accesodatos;

import java.util.TreeMap;

import com.ipartek.formacion.mf0967.entidades.Producto;

public class DaoProductoMemoria implements DaoProducto {

	private TreeMap<Long, Producto> productos = new TreeMap<>();

	// PATRÓN SINGLETON
	private DaoProductoMemoria() {
	}

	private static final DaoProductoMemoria INSTANCIA = new DaoProductoMemoria();

	public static DaoProductoMemoria getInstancia() {
		return INSTANCIA;
	}
	// FIN SINGLETON

	@Override
	public Iterable<Producto> obtenerTodos() {
		return productos.values();
	}

	@Override
	public Producto obtenerPorId(Long id) {
		return productos.get(id);
	}

	@Override
	public Producto insertar(Producto producto) {
		Long id = productos.size() > 0L ? productos.lastKey() + 1L: 1L;
		producto.setId(id);
		productos.put(id, producto);
		
		return producto;
	}

	@Override
	public Producto modificar(Producto producto) {
		productos.put(producto.getId(), producto);
		return producto;
	}

	@Override
	public void borrar(Long id) {
		productos.remove(id);
	}

}
