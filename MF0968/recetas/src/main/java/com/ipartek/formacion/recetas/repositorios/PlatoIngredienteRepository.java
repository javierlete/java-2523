package com.ipartek.formacion.recetas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ipartek.formacion.recetas.entidades.PlatoIngrediente;

@RepositoryRestResource(path = "platoingredientes", collectionResourceRel = "platoingredientes")
public interface PlatoIngredienteRepository extends JpaRepository<PlatoIngrediente, Long> {

	Iterable<PlatoIngrediente> findByPlatoId(Long id);
	void deleteByPlatoId(Long id);
}
