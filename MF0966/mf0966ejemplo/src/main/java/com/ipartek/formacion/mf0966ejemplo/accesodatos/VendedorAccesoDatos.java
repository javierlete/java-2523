package com.ipartek.formacion.mf0966ejemplo.accesodatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ipartek.formacion.mf0966ejemplo.dtos.VendedorDTO;

public class VendedorAccesoDatos {
	private static final String SQL_SELECT = "SELECT id, nombre, apellidos, dni FROM vendedores";
	private static final String SQL_SELECT_ID = SQL_SELECT + " WHERE id = ?";
	private static final String SQL_INSERT = "INSERT INTO vendedores (nombre, apellidos, dni) VALUES (?, ? ,?)";
	private static final String SQL_UPDATE = "UPDATE vendedores SET nombre=?, apellidos=?, dni=? WHERE id=?";
	private static final String SQL_DELETE = "DELETE FROM vendedores WHERE id=?";

	public static ArrayList<VendedorDTO> obtenerTodos() {
		var vendedores = new ArrayList<VendedorDTO>();

		try (Connection con = AccesoDatos.obtenerConexion();
				PreparedStatement pst = con.prepareStatement(SQL_SELECT);
				ResultSet rs = pst.executeQuery()) {
			VendedorDTO vendedor;

			while (rs.next()) {
				vendedor = new VendedorDTO(rs.getLong("id"), rs.getString("nombre"), rs.getString("apellidos"),
						rs.getString("dni"));

				vendedores.add(vendedor);
			}

			return vendedores;
		} catch (SQLException e) {
			throw new RuntimeException("Error en la select", e);
		}
	}

	public static VendedorDTO obtenerPorId(Long id) {
		try (Connection con = AccesoDatos.obtenerConexion();
				PreparedStatement pst = con.prepareStatement(SQL_SELECT_ID);) {
			pst.setLong(1, id);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				return new VendedorDTO(rs.getLong("id"), rs.getString("nombre"), rs.getString("apellidos"),
						rs.getString("dni"));
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error en la select", e);
		}
	}

	public static VendedorDTO insertar(VendedorDTO vendedor) {
		try (Connection con = AccesoDatos.obtenerConexion();
				PreparedStatement pst = con.prepareStatement(SQL_INSERT);) {
			pst.setString(1, vendedor.nombre());
			pst.setString(2, vendedor.apellidos());
			pst.setString(3, vendedor.dni());

			pst.executeUpdate();

			return vendedor;
		} catch (SQLException e) {
			throw new RuntimeException("Error en la insert", e);
		}
	}

	public static VendedorDTO modificar(Long id, VendedorDTO vendedor) {
		try (Connection con = AccesoDatos.obtenerConexion();
				PreparedStatement pst = con.prepareStatement(SQL_UPDATE);) {
			pst.setString(1, vendedor.nombre());
			pst.setString(2, vendedor.apellidos());
			pst.setString(3, vendedor.dni());
			pst.setLong(4, id);

			pst.executeUpdate();

			return vendedor;
		} catch (SQLException e) {
			throw new RuntimeException("Error en la update", e);
		}
	}

	public static void borrar(Long id) {
		try (Connection con = AccesoDatos.obtenerConexion();
				PreparedStatement pst = con.prepareStatement(SQL_DELETE);) {
			pst.setLong(1, id);

			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Error en la delete", e);
		}
	}
}
