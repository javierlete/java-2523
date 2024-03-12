package com.ipartek.formacion.uf2216.dal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import com.ipartek.formacion.uf2216.pojos.Producto;

class DaoProductoArrayList implements DaoProducto {

	private ArrayList<Producto> productos = new ArrayList<>();

	// SINGLETON
	private DaoProductoArrayList() {
		productos.add(new Producto(1L, "Producto2", new BigDecimal("1234"), 45, false));
		productos.add(new Producto(2L, "Producto2", new BigDecimal("1234"), 45, false));
		productos.add(new Producto(3L, "Producto3", new BigDecimal("321"), 33, true));
	}

	private static DaoProductoArrayList instancia;

	public static DaoProductoArrayList getInstancia() {
		if(instancia == null) {
			instancia = new DaoProductoArrayList();
		}
		
		return instancia;
	}
	// FIN SINGLETON

	@Override
	public Iterable<Producto> obtenerTodos() {
		return productos;
	}

	@Override
	public Producto obtenerPorId(Long id) {
//		PRIMERA OPCIÓN CON CLASE INTERNA
//		return productos.stream().filter(new ComparadorProducto(id)).findFirst().orElse(null);
//		SEGUNDA OPCIÓN CON CLASE ANÓNIMA
//		return productos.stream().filter(new Predicate<Producto>() {
//			@Override
//			public boolean test(Producto p) {
//				return p.getId() == id;
//			}
//		}).findFirst().orElse(null);
//		LAMBDA
		return productos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
	}

	@Override
	public Producto insertar(Producto producto) {
		Optional<Long> oIdMaximo = productos.stream().map(p -> p.getId()).max((id1, id2) -> id1.compareTo(id2));
		// Long idMaximo = oId.orElse(null);
		// Long id = idMaximo == null ? 1L : idMaximo + 1L;
		Long id = oIdMaximo.isEmpty() ? 1L : oIdMaximo.get() + 1L;

		producto.setId(id);

		productos.add(producto);

		return producto;
	}

	@Override
	public Producto modificar(Producto producto) {
		for(int i = 0; i < productos.size(); i++) {
			if(producto.getId() == productos.get(i).getId()) {
				productos.set(i, producto);
			}
		}

		return producto;
	}

	@Override
	public void borrar(Long id) {
		for(int i = 0; i < productos.size(); i++) {
			if(id == productos.get(i).getId()) {
				productos.remove(i);
			}
		}
	}

	@Override
	public Iterable<Producto> buscarPorNombre(String nombre) {
		return productos.stream().filter(p -> p.getNombre().contains(nombre)).toList();
	}

//	PRIMERA OPCIÓN CON CLASE INTERNA
//	static class ComparadorProducto implements Predicate<Producto> {
//		private Long id;
//		
//		public ComparadorProducto(Long id) {
//			this.id = id;
//		}
//		
//		@Override
//		public boolean test(Producto producto) {
//			return producto.getId() == id;
//		}
//		
//	}
}
