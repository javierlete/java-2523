package com.ipartek.formacion.mf0967.accesodatos;

public interface Dao<T> {
	default Iterable<T> obtenerTodos() {
		throw new UnsupportedOperationException("NO LO HE IMPLEMENTADO");
	}

	default T obtenerPorId(Long id) {
		throw new UnsupportedOperationException("NO LO HE IMPLEMENTADO");
	}

	default T insertar(T objeto) {
		throw new UnsupportedOperationException("NO LO HE IMPLEMENTADO");
	}

	default T modificar(T objeto) {
		throw new UnsupportedOperationException("NO LO HE IMPLEMENTADO");
	}

	default void borrar(Long id) {
		throw new UnsupportedOperationException("NO LO HE IMPLEMENTADO");
	}
}
