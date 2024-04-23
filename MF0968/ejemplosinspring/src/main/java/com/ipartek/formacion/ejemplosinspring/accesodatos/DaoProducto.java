package com.ipartek.formacion.ejemplosinspring.accesodatos;

import java.math.BigDecimal;

import com.ipartek.formacion.ejemplosinspring.entidades.Producto;

public interface DaoProducto extends Dao<Producto> {
	default Iterable<Producto> obtenerPorNombre(String nombre) {
		throw new UnsupportedOperationException("NO ESTA IMPLEMENTADA");
	}
	
	default Iterable<Producto> obtenerPorRangoPrecios(BigDecimal minimo, BigDecimal maximo) {
		throw new UnsupportedOperationException("NO ESTA IMPLEMENTADA");
	}
}
