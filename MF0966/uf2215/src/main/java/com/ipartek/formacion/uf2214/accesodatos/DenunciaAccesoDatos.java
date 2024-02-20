package com.ipartek.formacion.uf2214.accesodatos;

import static com.ipartek.formacion.uf2214.accesodatos.AccesoDatosJpa.enTransaccion;

import com.ipartek.formacion.uf2214.entidades.Denuncia;

public class DenunciaAccesoDatos {
	public static void insertar(Denuncia denuncia) {
		enTransaccion(em -> {
			em.persist(denuncia);
			return null;
		});
	}
}
