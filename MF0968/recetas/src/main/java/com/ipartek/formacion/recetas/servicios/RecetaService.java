package com.ipartek.formacion.recetas.servicios;

import com.ipartek.formacion.recetas.entidades.Ingrediente;
import com.ipartek.formacion.recetas.entidades.Plato;
import com.ipartek.formacion.recetas.entidades.PlatoIngrediente;
import com.ipartek.formacion.recetas.entidades.Usuario;
import com.ipartek.formacion.recetas.entidades.Valoracion;

public interface RecetaService {
	default void anadirIngrediente(Ingrediente ingrediente) {
		throw new UnsupportedOperationException("NO IMPLEMENTADO");
	}

	default void anadirPlato(Plato plato) {
		throw new UnsupportedOperationException("NO IMPLEMENTADO");
	}

	default void anadirIngredienteAPlato(PlatoIngrediente platoIngrediente) {
		throw new UnsupportedOperationException("NO IMPLEMENTADO");
	}

	default void registrarUsuario(Usuario usuario) {
		throw new UnsupportedOperationException("NO IMPLEMENTADO");
	}

	default void loginUsuario(Usuario usuario) {
		throw new UnsupportedOperationException("NO IMPLEMENTADO");
	}

	default Iterable<Plato> listadoPlatos() {
		throw new UnsupportedOperationException("NO IMPLEMENTADO");
	}

	default Plato verPlato(Long id) {
		throw new UnsupportedOperationException("NO IMPLEMENTADO");
	}

	default void valorarPlato(Valoracion valoracion) {
		throw new UnsupportedOperationException("NO IMPLEMENTADO");
	}
}
