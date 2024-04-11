package com.ipartek.formacion.fullstack.accesodatos;

import java.util.function.Consumer;
import java.util.function.Function;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class AccesoDatosJpa {
	private static final EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("com.ipartek.formacion.fullstack.entidades");
			
	public <T> T enTransaccion(Function<EntityManager, T> lambda) {
		T resultado;
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
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
