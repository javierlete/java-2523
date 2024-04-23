package com.ipartek.formacion.ejemplosinspring.accesodatos;

import java.util.function.Consumer;
import java.util.function.Function;

import com.ipartek.formacion.ejemplosinspring.global.Fabrica;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class AccesoDatosJpa {
	
	public <T> T enTransaccion(Function<EntityManager, T> lambda) {
		T resultado;
		
		EntityManager entityManager = (EntityManager) Fabrica.obtenerObjeto("em");
		EntityTransaction transaction = entityManager.getTransaction();
		
		try {
			transaction.begin();
			resultado = lambda.apply(entityManager);
			transaction.commit();
			
			return resultado;
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw new AccesoDatosException("No se ha podido consultar los cursos", e);
		} finally {
			entityManager.close();
		}
	}
	
	public void enTransaccionVoid(Consumer<EntityManager> lambda) {
		enTransaccion(em -> {
			lambda.accept(em);
			return null;
		});
	}
}
