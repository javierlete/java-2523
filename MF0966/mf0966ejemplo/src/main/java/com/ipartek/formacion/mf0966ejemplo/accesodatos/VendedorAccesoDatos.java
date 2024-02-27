package com.ipartek.formacion.mf0966ejemplo.accesodatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ipartek.formacion.mf0966ejemplo.dtos.VendedorDTO;

public class VendedorAccesoDatos {
	private static final String SQL_SELECT = "SELECT id, nombre, apellidos, dni FROM vendedores";

	public static ArrayList<VendedorDTO> obtenerTodos() {
		var vendedores = new ArrayList<VendedorDTO>();
		
		Connection con = AccesoDatos.obtenerConexion();
		try (PreparedStatement pst = con.prepareStatement(SQL_SELECT);
				ResultSet rs = pst.executeQuery()) {
			VendedorDTO vendedor;
			
			while(rs.next()) {
				vendedor = new VendedorDTO(rs.getLong("id"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("dni"));
				
				vendedores.add(vendedor);
			}
			
			return vendedores;
		} catch (SQLException e) {
			throw new RuntimeException("Error en la select", e);
		}
	}
}
