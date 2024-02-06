package com.ipartek.formacion.uf2213;

import java.util.function.Consumer;

import com.ipartek.formacion.uf2213.entidades.Cliente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class EjemploJPA {
	private static EntityManagerFactory entityManagerFactory;

	public static void main(String[] args) {
		entityManagerFactory = Persistence.createEntityManagerFactory("com.ipartek.formacion.uf2213.entidades");

		inTransaction(em -> {
			em.persist(new Cliente(null, "12345678Z", null, "Javier", null, null));
			em.persist(new Cliente(null, "87654321X", null, "Pepe", null, null));
			em.persist(new Cliente(null, "17632476", null, "Pepe", null, null));
		});
		
		inTransaction(em -> {
			em.merge(new Cliente(2L, "87654321X", null, "MODIFICADO", null, null));
			em.remove(em.find(Cliente.class, 1L));
		});
		
		inTransaction(em -> System.out.println(em.find(Cliente.class, 2L)));

		inTransaction(em -> {
			var clientes = em.createQuery("select c from Cliente c", Cliente.class).getResultList();
			
			for(Cliente cliente: clientes) {
				System.out.println(cliente);
			}
		});
		
	}

	private static void inTransaction(Consumer<EntityManager> work) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			work.accept(entityManager);
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		} finally {
			entityManager.close();
		}
	}
}
