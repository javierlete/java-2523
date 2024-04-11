package com.ipartek.formacion.fullstack.fabricas;

import com.ipartek.formacion.fullstack.accesodatos.DaoAlumno;
import com.ipartek.formacion.fullstack.accesodatos.DaoCurso;

public interface Fabrica {
	DaoAlumno getDaoAlumno();
	DaoCurso getDaoCurso();
}
