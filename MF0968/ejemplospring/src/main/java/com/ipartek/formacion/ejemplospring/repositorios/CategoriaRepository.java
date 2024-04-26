package com.ipartek.formacion.ejemplospring.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.ipartek.formacion.ejemplospring.entidades.Categoria;

public interface CategoriaRepository extends CrudRepository<Categoria, Long> {

}
