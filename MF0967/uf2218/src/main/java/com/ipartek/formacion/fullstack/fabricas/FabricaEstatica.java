package com.ipartek.formacion.fullstack.fabricas;

import com.ipartek.formacion.fullstack.accesodatos.DaoAlumno;
import com.ipartek.formacion.fullstack.accesodatos.DaoAlumnoJpa;
import com.ipartek.formacion.fullstack.accesodatos.DaoCurso;
import com.ipartek.formacion.fullstack.accesodatos.DaoCursoJpa;

public class FabricaEstatica implements Fabrica {

	@Override
	public DaoAlumno getDaoAlumno() {
		return new DaoAlumnoJpa();
	}

	@Override
	public DaoCurso getDaoCurso() {
		return new DaoCursoJpa();
	}

}
