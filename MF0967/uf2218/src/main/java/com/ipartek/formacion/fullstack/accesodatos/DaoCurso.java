package com.ipartek.formacion.fullstack.accesodatos;

import com.ipartek.formacion.fullstack.dtos.AlumnoDto;
import com.ipartek.formacion.fullstack.dtos.CursoDto;

public interface DaoCurso extends Dao<CursoDto> {

	Iterable<AlumnoDto> alumnos(Long id);

}
