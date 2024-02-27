package com.ipartek.formacion.mf0966ejemplo.accesodatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ipartek.formacion.mf0966ejemplo.dtos.CocheDTO;
import com.ipartek.formacion.mf0966ejemplo.dtos.VendedorDTO;

public class CocheAccesoDatos {
	private static final Logger LOG = Logger.getLogger(CocheAccesoDatos.class.getName());
	
	private static final String SQL_SELECT = "SELECT * FROM vendedores v JOIN coches c ON c.vendedores_id = v.id";
	private static final String SQL_SELECT_MARCA = SQL_SELECT + " WHERE c.marca LIKE ?";

	public static ArrayList<CocheDTO> obtenerTodos() {
		var coches = new ArrayList<CocheDTO>();

		try (Connection con = AccesoDatos.obtenerConexion();
				PreparedStatement pst = con.prepareStatement(SQL_SELECT);
				ResultSet rs = pst.executeQuery()) {
			
			CocheDTO coche;
			VendedorDTO vendedor;

			while (rs.next()) {
				vendedor = new VendedorDTO(rs.getLong("v.id"), rs.getString("v.nombre"), rs.getString("v.apellidos"),
						rs.getString("v.dni"));
				coche = new CocheDTO(rs.getLong("c.id"), rs.getString("c.matricula"), rs.getString("c.marca"), rs.getString("c.modelo"), rs.getDate("c.fecha_fabricacion").toLocalDate(), rs.getBigDecimal("c.precio"), rs.getInt("c.stock"), vendedor);
				
				LOG.info("SE HA CREADO EL COCHE: " + coche);
				
				coches.add(coche);
			}

			return coches;
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "NO SE HAN PODIDO OBTENER LOS COCHES CON SUS VENDEDORES", e);
			throw new RuntimeException("Error en la select", e);
		}
	}
	
	public static ArrayList<CocheDTO> buscarPorMarca(String marca) {
		var coches = new ArrayList<CocheDTO>();

		try (Connection con = AccesoDatos.obtenerConexion();
				PreparedStatement pst = con.prepareStatement(SQL_SELECT_MARCA);
				) {
			pst.setString(1, "%" + marca + "%");
			
			ResultSet rs = pst.executeQuery();
			
			CocheDTO coche;
			VendedorDTO vendedor;

			while (rs.next()) {
				vendedor = new VendedorDTO(rs.getLong("v.id"), rs.getString("v.nombre"), rs.getString("v.apellidos"),
						rs.getString("v.dni"));
				coche = new CocheDTO(rs.getLong("c.id"), rs.getString("c.matricula"), rs.getString("c.marca"), rs.getString("c.modelo"), rs.getDate("c.fecha_fabricacion").toLocalDate(), rs.getBigDecimal("c.precio"), rs.getInt("c.stock"), vendedor);
				
				LOG.info("SE HA CREADO EL COCHE: " + coche);
				
				coches.add(coche);
			}

			return coches;
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "NO SE HAN PODIDO OBTENER LOS COCHES CON SUS VENDEDORES", e);
			throw new RuntimeException("Error en la select", e);
		}
	}
}
