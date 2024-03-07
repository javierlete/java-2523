package com.ipartek.formacion.uf2216.pruebas;

import com.ipartek.formacion.uf2216.pojos.Producto;
import com.ipartek.formacion.uf2216.pojos.ProductoPerecedero;

public class ProductoPerecederoPrueba {
	public static void main(String[] args) {
		final ProductoPerecedero productoPerecedero = new ProductoPerecedero();

		System.out.println(productoPerecedero);

		Producto producto = productoPerecedero;

		System.out.println(producto);

		if (producto instanceof ProductoPerecedero productoPerecedero2) {
			System.out.println(productoPerecedero2.getCaducidad());
		} else {
			System.out.println("No es perecedero");
		}

		Producto producto2 = new Producto();

		if (producto2 instanceof ProductoPerecedero) {
			ProductoPerecedero productoPerecedero3 = (ProductoPerecedero) producto2;

			System.out.println(productoPerecedero3.getCaducidad());
		} else {
			System.out.println("No es perecedero");
		}
		
		Object objeto = productoPerecedero;

		objeto.equals(objeto);
		
		Producto p = new Producto(producto2);
		
		p.setNombre("Modificado");
		
		System.out.println(p);
		System.out.println(producto2);
	}
}
