package com.ipartek.formacion.mf0967.configuraciones;

import java.math.BigDecimal;

import com.ipartek.formacion.mf0967.accesodatos.DaoProducto;
import com.ipartek.formacion.mf0967.accesodatos.DaoProductoMemoria;
import com.ipartek.formacion.mf0967.entidades.Producto;

import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;

public class Globales {
	public static final DaoProducto DAO_PRODUCTO = DaoProductoMemoria.getInstancia();
	public static final ValidatorFactory VALIDATOR_FACTORY = Validation.buildDefaultValidatorFactory();
	
	static {
		for(int i = 1; i <= 10; i++) {
			DAO_PRODUCTO.insertar(new Producto("Producto" + i, new BigDecimal(i * 10), "https://picsum.photos/300/200?" + i));
		}
	}
}
