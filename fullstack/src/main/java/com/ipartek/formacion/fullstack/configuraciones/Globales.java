package com.ipartek.formacion.fullstack.configuraciones;

import com.ipartek.formacion.fullstack.accesodatos.DaoAlumno;
import com.ipartek.formacion.fullstack.accesodatos.DaoCurso;
import com.ipartek.formacion.fullstack.fabricas.Fabrica;
import com.ipartek.formacion.fullstack.fabricas.FabricaEstatica;

public class Globales {
	private static final Fabrica fabrica = new FabricaEstatica();
	public static final DaoCurso daoCurso = fabrica.getDaoCurso();
	public static final DaoAlumno daoAlumno = fabrica.getDaoAlumno();
}
