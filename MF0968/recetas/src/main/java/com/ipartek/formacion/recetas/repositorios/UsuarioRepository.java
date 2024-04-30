package com.ipartek.formacion.recetas.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.ipartek.formacion.recetas.entidades.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

}
