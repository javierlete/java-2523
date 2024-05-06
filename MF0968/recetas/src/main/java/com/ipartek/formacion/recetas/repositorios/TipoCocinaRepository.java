package com.ipartek.formacion.recetas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ipartek.formacion.recetas.entidades.TipoCocina;

@RepositoryRestResource(path = "tiposcocina", collectionResourceRel = "tiposcocina")
public interface TipoCocinaRepository extends JpaRepository<TipoCocina, Long> {

}
