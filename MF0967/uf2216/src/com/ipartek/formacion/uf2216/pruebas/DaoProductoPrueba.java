package com.ipartek.formacion.uf2216.pruebas;

import java.math.BigDecimal;

import com.ipartek.formacion.uf2216.dal.DaoProducto;
import com.ipartek.formacion.uf2216.dal.DaoProductoTreeMap;
import com.ipartek.formacion.uf2216.pojos.Producto;

public class DaoProductoPrueba {
	public static void main(String[] args) {
		DaoProducto dao = DaoProductoTreeMap.getInstancia();
		
		dao.insertar(new Producto("Nuevo", new BigDecimal("111"), 5, true));
		dao.modificar(new Producto(1L, "Modificado", new BigDecimal("1234"), 7, false));
		dao.borrar(3L);
		
		for(var p: dao.obtenerTodos()) {
			System.out.println(p);
		}
		
		System.out.println(dao.obtenerPorId(2L));
		
		
	}
}
