package com.ipartek.formacion.ejemplosinspring.accesodatos;

public interface Dao<T> {
	default Iterable<T> obtenerTodos() {
		throw new UnsupportedOperationException("NO ESTA IMPLEMENTADA");
	}

	default T obtenerPorId(Long id) {
		throw new UnsupportedOperationException("NO ESTA IMPLEMENTADA");
	}

	default T insertar(T objeto) {
		throw new UnsupportedOperationException("NO ESTA IMPLEMENTADA");
	}

	default T modificar(T objeto) {
		throw new UnsupportedOperationException("NO ESTA IMPLEMENTADA");
	}

	default void borrar(Long id) {
		throw new UnsupportedOperationException("NO ESTA IMPLEMENTADA");
	}

}
