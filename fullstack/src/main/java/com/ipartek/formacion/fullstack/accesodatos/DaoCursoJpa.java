package com.ipartek.formacion.fullstack.accesodatos;

import com.ipartek.formacion.fullstack.dtos.CursoDto;
import com.ipartek.formacion.fullstack.entidades.Curso;

public class DaoCursoJpa extends AccesoDatosJpa implements DaoCurso {

	@Override
	public Iterable<CursoDto> obtenerTodos() {
		return enTransaccion(em -> em.createQuery("select c.id, c.nombre from Curso c", CursoDto.class).getResultList());
	}
	
	@Override
	public CursoDto obtenerPorId(Long id) {
		return enTransaccion(em -> em.createQuery("select c.id, c.nombre from Curso c where c.id=:id", CursoDto.class).setParameter("id", id).getSingleResult());
	}

	@Override
	public CursoDto insertar(CursoDto curso) {
		return enTransaccion(em -> {
			Curso c = new Curso(null, curso.nombre(), null);
			em.persist(c);
			return new CursoDto(c.getId(), c.getNombre());
		});
	}

	@Override
	public CursoDto modificar(CursoDto curso) {
		return enTransaccion(em -> {
			if(curso.id() == null) {
				throw new AccesoDatosException("Para modificar un curso debes proporcionar el id");
			}
			
			Curso c = new Curso(curso.id(), curso.nombre(), null);
			em.merge(c);
			return new CursoDto(c.getId(), c.getNombre());
		});
	}

	@Override
	public void borrar(Long id) {
		enTransaccionVoid(em -> em.remove(em.find(Curso.class, id)));
	}
	
//	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.ipartek.formacion.fullstack.entidades");
//	@Override
//	public Iterable<Curso> obtenerTodos() {
//		EntityTransaction t = null;
//		
//		try (EntityManager em = emf.createEntityManager()) {
//			t = em.getTransaction();
//			t.begin();
//			
//			List<Curso> cursos = em.createQuery("select c from Curso c", Curso.class).getResultList();
//			
//			t.commit();
//			
//			return cursos;
//		} catch(Exception e) {
//			if(t != null) {
//				t.rollback();
//			}
//			
//			throw new AccesoDatos("No se ha podido consultar los cursos");
//		}
//	}
}
