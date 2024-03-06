package com.ipartek.formacion.uf2216.pruebas;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;

import com.ipartek.formacion.uf2216.pojos.Producto;

public class ProductoPrueba {
	public static void main(String[] args) {
		System.out.println(Producto.NOMBRE_POR_DEFECTO);
		
		Producto.setStockMinimo(0);
		
		Producto producto = new Producto();
		
		System.out.println(producto);

		producto.setId(5L);
		producto.setNombre("    Leche   ");
		producto.setPrecio(new BigDecimal(12));
		producto.setCaducidad(LocalDate.of(2025, 3, 7));
		producto.setStock(20);
		producto.setDisponible(true);

		System.out.println(producto);

		Producto producto2 = new Producto(2L, " Monitor  ", new BigDecimal(123), null, 20, true);
		
		System.out.println(producto2);
		
		Producto producto3 = new Producto("Ratón", new BigDecimal(23), null, 50, true);
		
		System.out.println(producto3);
		
		Producto producto4 = new Producto("Ratón", new BigDecimal(23), 50, true);
		
		System.out.println(producto4);
		
//		System.out.println(producto.getId());
//		System.out.println(producto.getNombre());
//		System.out.println(producto.getPrecio());
		
		System.out.println(producto3 == producto4);
		System.out.println(producto3.equals(producto4));
		
		System.out.println();
		System.out.println("COLECCION");
		System.out.println();
		
		var productos = new HashSet<Producto>();
		
		productos.add(producto);
		productos.add(producto2);
		productos.add(producto3);
		productos.add(producto4);
		
		productos.remove(new Producto("Ratón", new BigDecimal(23), 50, true));
		
		for(var p: productos) {
			System.out.println(p);
		}
	}
}












