package com.ipartek.formacion.guasa.pruebas;

import jakarta.persistence.Persistence;

public class Pruebas {

	public static void main(String[] args) {
		Persistence.createEntityManagerFactory("com.ipartek.formacion.guasa.entidades");
	}

}
