package com.ipartek.formacion.fullstack.accesodatos;

import com.ipartek.formacion.fullstack.dtos.AlumnoDto;

public interface DaoAlumno extends Dao<AlumnoDto> {
	void apuntarseACurso(Long idAlumno, Long idCurso);
}
