package com.ipartek.formacion.recetas.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ipartek.formacion.recetas.entidades.Valoracion;

@RepositoryRestResource(path = "valoraciones", collectionResourceRel = "valoraciones")
public interface ValoracionRepository extends CrudRepository<Valoracion, Long> {

}
