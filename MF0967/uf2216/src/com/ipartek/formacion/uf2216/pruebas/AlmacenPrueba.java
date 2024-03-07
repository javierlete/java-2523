package com.ipartek.formacion.uf2216.pruebas;

import java.math.BigDecimal;

import com.ipartek.formacion.uf2216.pojos.Almacen;
import com.ipartek.formacion.uf2216.pojos.Producto;

public class AlmacenPrueba {
	public static void main(String[] args) {
		Almacen almacen = new Almacen("Bilbao");
		
		Producto producto2 = new Producto(2L, " Monitor  ", new BigDecimal(123), 20, true);
		
		almacen.agregarProducto(new Producto());
		almacen.agregarProducto(producto2);
		almacen.agregarProducto(new Producto("Portátil", new BigDecimal(1234), 5, true));
		
		almacen.setProductoDestacado(producto2);

		System.out.println(almacen);
		
		for(var p: almacen.getProductos()) {
			System.out.println(p.getNombre() + ": " + p.getPrecio());
		}
	}
}
