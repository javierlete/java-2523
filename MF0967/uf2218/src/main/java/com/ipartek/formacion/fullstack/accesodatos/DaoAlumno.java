package com.ipartek.formacion.fullstack.accesodatos;

import com.ipartek.formacion.fullstack.dtos.AlumnoDto;
import com.ipartek.formacion.fullstack.dtos.AlumnoLoginDto;

public interface DaoAlumno extends Dao<AlumnoDto> {
	void apuntarseACurso(Long idAlumno, Long idCurso);

	AlumnoLoginDto buscarPorEmail(String email);
}
