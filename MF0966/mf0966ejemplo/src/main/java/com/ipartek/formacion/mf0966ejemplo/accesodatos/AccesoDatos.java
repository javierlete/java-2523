package com.ipartek.formacion.mf0966ejemplo.accesodatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccesoDatos {
	private static final String URL = "jdbc:mysql://localhost:3306/mf0966ejemplo";
	private static final String USER = "root";
	private static final String PASS = "1234";
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("No se ha encontrado el driver de MySQL", e);
		}
	}
	
	public static Connection obtenerConexion() {
		try {
			return DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException e) {
			throw new RuntimeException("Error en la conexión a la base de datos", e);
		}
	}
}
