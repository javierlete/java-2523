package com.ipartek.formacion.recetas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ipartek.formacion.recetas.entidades.Ingrediente;

@RepositoryRestResource(path = "ingredientes", collectionResourceRel = "ingredientes")
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {

}
