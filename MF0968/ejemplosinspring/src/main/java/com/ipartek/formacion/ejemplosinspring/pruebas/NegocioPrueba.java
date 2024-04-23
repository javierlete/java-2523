package com.ipartek.formacion.ejemplosinspring.pruebas;

import java.math.BigDecimal;

import com.ipartek.formacion.ejemplosinspring.entidades.Producto;
import com.ipartek.formacion.ejemplosinspring.global.Fabrica;
import com.ipartek.formacion.ejemplosinspring.negocio.AdminNegocio;
import com.ipartek.formacion.ejemplosinspring.negocio.UsuarioNegocio;

public class NegocioPrueba {
	public static void main(String[] args) {
		UsuarioNegocio un = (UsuarioNegocio) Fabrica.obtenerObjeto("negocioUsuario");
		AdminNegocio an = (AdminNegocio) Fabrica.obtenerObjeto("negocioAdmin");
		
		an.insertarProducto(Producto.builder().nombre("Nuevo").precio(new BigDecimal(1000)).build());
		
		for(Producto p: un.obtenerProductos()) {
			System.out.println(p);
		}
	}
}
