package com.ipartek.formacion.mf0966ejemplo.accesodatos;

import static com.ipartek.formacion.mf0966ejemplo.accesodatos.AccesoDatosJpa.enTransaccion;

import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.mf0966ejemplo.dtos.CocheSimplificadoDTO;
import com.ipartek.formacion.mf0966ejemplo.dtos.VendedorDTO;
import com.ipartek.formacion.mf0966ejemplo.entidades.Coche;

public class CocheAccesoDatosJpa {
	public static List<CocheSimplificadoDTO> obtenerTodos() {
		return enTransaccion(em -> em.createQuery("select c.id, c.matricula, c.marca, c.modelo, v.id, v.nombre, v.apellidos, v.dni from Coche c join c.vendedor v", CocheSimplificadoDTO.class).getResultList());
	}
	
	public static List<CocheSimplificadoDTO> buscarPorMarca(String marca) {
		return enTransaccion(em -> {
			var resultado = em.createQuery("select c from Coche c join fetch c.vendedor v where c.marca like :marca", Coche.class).setParameter("marca", "%" + marca + "%").getResultList();
			
			var coches = new ArrayList<CocheSimplificadoDTO>();
			
			CocheSimplificadoDTO cd;
			VendedorDTO vd;
			
			for(Coche c: resultado) {
				vd = new VendedorDTO(c.getVendedor().getId(), c.getVendedor().getNombre(), c.getVendedor().getApellidos(), c.getVendedor().getDni());
				cd = new CocheSimplificadoDTO(c.getId(), c.getMatricula(), c.getMarca(), c.getModelo(), vd);
				
				coches.add(cd);
			}
			
			return coches;
		});
	}
}
