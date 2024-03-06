package com.ipartek.formacion.uf2216.pojos;

import java.util.ArrayList;

public class Almacen {
	private ArrayList<Producto> productos = new ArrayList<>();

	private Long id;
	private String nombre;

	private Producto productoDestacado;
	
	public ArrayList<Producto> getProductos() {
		return productos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Producto getProductoDestacado() {
		return productoDestacado;
	}

	public void setProductoDestacado(Producto productoDestacado) {
		this.productoDestacado = productoDestacado;
	}

	public Almacen(String nombre) {
		this.nombre = nombre;
	}

	public void agregarProducto(Producto producto) {
		if (producto == null) {
			throw new RuntimeException("No es válido intentar introducir productos nulos");
		}

		productos.add(producto);
	}

	@Override
	public String toString() {
		return "Almacen [productos=" + productos + ", id=" + id + ", nombre=" + nombre + ", productoDestacado="
				+ productoDestacado + "]";
	}
}
