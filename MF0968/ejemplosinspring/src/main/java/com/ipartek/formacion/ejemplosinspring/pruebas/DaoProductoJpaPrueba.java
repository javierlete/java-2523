package com.ipartek.formacion.ejemplosinspring.pruebas;

import java.math.BigDecimal;

import com.ipartek.formacion.ejemplosinspring.accesodatos.DaoProducto;
import com.ipartek.formacion.ejemplosinspring.entidades.Producto;
import com.ipartek.formacion.ejemplosinspring.global.Fabrica;

public class DaoProductoJpaPrueba {
	public static void main(String[] args) {
		DaoProducto dao = (DaoProducto) Fabrica.obtenerObjeto("daoProducto");
		
		dao.insertar(Producto.builder().nombre("Prueba").precio(new BigDecimal(1234)).build());
	}
}
