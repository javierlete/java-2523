package com.ipartek.formacion.uf2213.accesodatos;

import static com.ipartek.formacion.uf2213.accesodatos.AccesoDatosJpa.*;

import java.sql.SQLException;
import java.util.LinkedHashSet;

import com.ipartek.formacion.uf2213.dtos.ClienteDTO;
import com.ipartek.formacion.uf2213.entidades.Cliente;

public class ClienteAccesoDatosJpa {
	public static LinkedHashSet<ClienteDTO> obtenerTodos() throws SQLException {
		var clientes = enTransaccion(em -> em.createQuery("select c from Cliente c", Cliente.class).getResultList());

		var clientesDTO = new LinkedHashSet<ClienteDTO>();

		for (Cliente cliente : clientes) {
			clientesDTO.add(entidad2Dto(cliente));
		}

		return clientesDTO;
	}

	public static ClienteDTO obtenerPorId(long id) throws SQLException {
		var cliente = enTransaccion(em -> em.find(Cliente.class, id));

		return entidad2Dto(cliente);
	}

	public static void insertar(ClienteDTO cliente) throws SQLException {
		enTransaccion(em -> {
			em.persist(dto2Entidad(cliente));
			return null;
		});
	}

	public static void modificar(ClienteDTO cliente) throws SQLException {
		enTransaccion(em -> {
			em.merge(dto2Entidad(cliente));
			return null;
		});
	}

	public static void borrar(long id) throws SQLException {
		enTransaccion(em -> {
			em.remove(em.find(Cliente.class, id));
			return null;
		});
	}

	private static ClienteDTO entidad2Dto(Cliente cliente) {
		return new ClienteDTO(cliente.getId(), cliente.getDni(), cliente.getDniDiferencial(), cliente.getNombre(),
				cliente.getApellidos(), cliente.getFechaNacimiento());
	}

	private static Cliente dto2Entidad(ClienteDTO cliente) {
		return new Cliente(cliente.id(), cliente.dni(), cliente.dniDiferencial(), cliente.nombre(), cliente.apellidos(),
				cliente.fechaNacimiento());
	}
}
