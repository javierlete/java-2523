package com.ipartek.formacion.uf2214.accesodatos;

import java.util.List;

import com.ipartek.formacion.uf2214.entidades.Rol;

public class RolAccesoDatos {
	public static List<Rol> obtenerTodos() {
		return AccesoDatosJpa.enTransaccion(em -> em.createQuery("select r from Rol r", Rol.class).getResultList());
		
//		return AccesoDatosJpa.enTransaccion(em -> {
//			var roles = new HashSet<Rol>();
//			em.createQuery("select r from Rol r", Rol.class).getResultStream().forEach(rol -> roles.add(rol));
//			return roles;
//		});
	}

	public static Rol obtenerPorId(long id) {
		return AccesoDatosJpa.enTransaccion(em -> em.find(Rol.class, id));
	}

	public static void insertar(Rol rol) {
		AccesoDatosJpa.enTransaccion(em -> {
			em.persist(rol);
			return null;
		});
	}

	public static Rol modificar(Rol rol) {
		return AccesoDatosJpa.enTransaccion(em -> em.merge(rol));
	}

	public static void borrar(Long id) {
		AccesoDatosJpa.enTransaccion(em -> {
			em.remove(em.find(Rol.class, id));
			return null;
		});
	}
}
