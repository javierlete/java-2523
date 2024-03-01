package com.ipartek.formacion.mf0966ejemplo.accesodatos;

import static com.ipartek.formacion.mf0966ejemplo.accesodatos.AccesoDatosJpa.enTransaccion;

import java.util.List;

import com.ipartek.formacion.mf0966ejemplo.dtos.VendedorDTO;
import com.ipartek.formacion.mf0966ejemplo.entidades.Vendedor;

public class VendedorAccesoDatosJpa {

	public static List<VendedorDTO> obtenerTodos() {
		return enTransaccion(em -> em.createQuery("select v.id, v.nombre, v.apellidos, v.dni from Vendedor v", VendedorDTO.class).getResultList());
	}

	public static VendedorDTO obtenerPorId(Long id) {
		return enTransaccion(em -> em.createQuery("select v.id, v.nombre, v.apellidos, v.dni from Vendedor v where v.id = ?", VendedorDTO.class).setParameter(1, id).getSingleResult());
	}

	public static Vendedor insertar(Vendedor vendedor) {
		return enTransaccion(em -> {
			em.persist(vendedor);
			return vendedor;
		});
	}

	public static Vendedor modificar(Long id, Vendedor vendedor) {
		return enTransaccion(em -> {
			em.merge(vendedor);
			return vendedor;
		});
	}

	public static void borrar(Long id) {
		enTransaccion(em -> {
			em.remove(em.find(Vendedor.class, id));
			return null;
		});
	}
}
