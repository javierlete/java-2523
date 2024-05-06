package com.ipartek.formacion.recetas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ipartek.formacion.recetas.entidades.Dificultad;

@RepositoryRestResource(path = "dificultades", collectionResourceRel = "dificultades")
public interface DificultadRepository extends JpaRepository<Dificultad, Long> {

}
