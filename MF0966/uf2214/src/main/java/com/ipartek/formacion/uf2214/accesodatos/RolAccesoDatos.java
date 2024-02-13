package com.ipartek.formacion.uf2214.accesodatos;

import com.ipartek.formacion.uf2214.entidades.Rol;

public class RolAccesoDatos {
	public static Rol obtenerPorId(long id) {
		return AccesoDatosJpa.enTransaccion(em -> em.find(Rol.class, id));
	}
	
	public static void insertar(Rol rol) {
		AccesoDatosJpa.enTransaccion(em -> {
			em.persist(rol);
			return null;
		});
	}
}
