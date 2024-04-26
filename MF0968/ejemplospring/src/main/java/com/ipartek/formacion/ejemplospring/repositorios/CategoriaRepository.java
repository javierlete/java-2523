package com.ipartek.formacion.ejemplospring.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ipartek.formacion.ejemplospring.entidades.Categoria;

public interface CategoriaRepository extends CrudRepository<Categoria, Long> {
	@Query("from Categoria c join fetch c.productos where c.id = :id")
	Categoria obtenerCategoriaPorIdConProductos(@Param("id") Long id);
}
