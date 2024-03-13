package com.ipartek.formacion.uf2216.pruebas;

import java.math.BigDecimal;

import com.ipartek.formacion.uf2216.dal.DalException;
import com.ipartek.formacion.uf2216.dal.DaoProducto;
import com.ipartek.formacion.uf2216.dal.FabricaDaoTipos;
import com.ipartek.formacion.uf2216.pojos.Producto;

public class DaoProductoPrueba {
	public static void main(String[] args) {
		try {
			DaoProducto dao = new FabricaDaoTipos().getDaoProducto(); // DaoProductoArrayList.getInstancia();
			
			for(var p: dao.obtenerTodos()) {
				System.out.println(p);
			}
			
			dao.insertar(new Producto("Nuevo", new BigDecimal("111"), 5, true));
			dao.modificar(new Producto(1L, "Modificado", new BigDecimal("1234"), 7, false));
			dao.borrar(3L);
			
			for(var p: dao.obtenerTodos()) {
				System.out.println(p);
			}
			
			System.out.println(dao.obtenerPorId(2L));
		} catch (DalException e) {
			System.out.println("Ha habido un error en el acceso a datos");
			e.printStackTrace(System.err);
		} catch(Exception e) {
			System.out.println("Se ha detectado un error no esperado");
			e.printStackTrace(System.err);
		}
		
		
	}
}
