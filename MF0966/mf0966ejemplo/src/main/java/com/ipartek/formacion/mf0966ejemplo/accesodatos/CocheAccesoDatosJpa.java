package com.ipartek.formacion.mf0966ejemplo.accesodatos;

import static com.ipartek.formacion.mf0966ejemplo.accesodatos.AccesoDatosJpa.enTransaccion;

import java.util.List;

import com.ipartek.formacion.mf0966ejemplo.dtos.CocheSimplificadoDTO;

public class CocheAccesoDatosJpa {
	public static List<CocheSimplificadoDTO> obtenerTodos() {
		return enTransaccion(em -> em.createQuery("select c.id, c.matricula, c.marca, c.modelo, v.id, v.nombre, v.apellidos, v.dni from Coche c join c.vendedor v", CocheSimplificadoDTO.class).getResultList());
	}
	
	public static List<CocheSimplificadoDTO> buscarPorMarca(String marca) {
		return enTransaccion(em -> em.createQuery("select c.id, c.matricula, c.marca, c.modelo, v.id, v.nombre, v.apellidos, v.dni from Coche c join c.vendedor v where c.marca like :marca", CocheSimplificadoDTO.class).setParameter("marca", "%" + marca + "%").getResultList());
	}
}
