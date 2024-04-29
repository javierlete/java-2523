package com.ipartek.formacion.recetas.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.ipartek.formacion.recetas.entidades.Ingrediente;

public interface IngredienteRepository extends CrudRepository<Ingrediente, Long> {

}
