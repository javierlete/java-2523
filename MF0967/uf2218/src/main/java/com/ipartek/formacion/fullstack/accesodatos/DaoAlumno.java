package com.ipartek.formacion.fullstack.accesodatos;

import com.ipartek.formacion.fullstack.dtos.AlumnoDto;
import com.ipartek.formacion.fullstack.dtos.AlumnoLoginDto;
import com.ipartek.formacion.fullstack.dtos.CursoDto;

public interface DaoAlumno extends Dao<AlumnoDto> {
	void apuntarseACurso(Long idAlumno, Long idCurso);
	Iterable<CursoDto> cursos(Long idAlumno);
	
	AlumnoLoginDto buscarPorEmail(String email);
}
