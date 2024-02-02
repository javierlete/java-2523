package com.ipartek.formacion.uf2213.accesodatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccesoDatos {
	private static final String URL = "jdbc:mysql://localhost:3306/manana_tienda";
	private static final String USER = "mananas";
	private static final String PASS = "curso";

	public static Connection con;

	public static void abrirConexion() throws SQLException {
		con = DriverManager.getConnection(URL, USER, PASS);
	}

	public static void cerrarConexion() throws SQLException {
		if (con != null) {
			con.close();
		}
	}
}
