package com.ipartek.formacion.uf2213;

import static com.ipartek.formacion.bibliotecas.Consola.*;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class EjemploJDBC {
	private static final String URL = "jdbc:mysql://localhost:3306/manana_tienda";
	private static final String USER = "mananas";
	private static final String PASS = "curso";

	private static final String SQL_SELECT = "CALL clientes_select()";
	private static final String SQL_SELECT_ID = "CALL clientes_select_id(?)";
	private static final String SQL_SELECT_ID_FACTURAS = "CALL clientes_facturas(?)";
	private static final String SQL_SELECT_ID_FACTURAS_PRODUCTOS = "CALL facturas_productos_totales(?)";

	private static final String SQL_INSERT = "CALL clientes_insert(?,?,?,?,?)";
	private static final String SQL_UPDATE = "CALL clientes_update(?,?,?,?,?)";
	private static final String SQL_DELETE = "CALL clientes_delete(?)";

	private static final int SALIR = 0;

	private static final int LISTADO = 1;
	private static final int BUSCAR = 2;
	private static final int INSERTAR = 3;
	private static final int MODIFICAR = 4;
	private static final int BORRAR = 5;
	private static final int FACTURAS = 6;
	private static final int FACTURAS_COMPLETAS = 7;

	private static Connection con;

	public static void main(String[] args) {
		try {
			con = DriverManager.getConnection(URL, USER, PASS);

			int opcion;
			do {
				mostrarMenu();
				opcion = pedirOpcion();
				ejecutar(opcion);
			} while (opcion != SALIR);

		} catch (SQLException e) {
			System.err.println("Error al conectar a la base de datos");
			System.err.println(e.getMessage());
//			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.err.println("Error al cerrar la conexión");
					e.printStackTrace();
				}
			}
		}
	}

	private static void mostrarMenu() {
		System.out.println("""
				MENU
				----

				1. LISTADO
				2. BUSCAR POR ID
				3. INSERTAR
				4. MODIFICAR
				5. BORRAR
				6. FACTURAS
				7. FACTURAS CON PRODUCTOS

				0. SALIR
				""");
	}

	private static int pedirOpcion() {
		return leerInt("Introduce la opción elegida");
	}

	private static void ejecutar(int opcion) {
		System.out.println("Ejecutando opción " + opcion);
		System.out.println();

		switch (opcion) {
		case LISTADO:
			listado();
			break;
		case BUSCAR:
			buscar();
			break;
		case INSERTAR:
			insertar();
			break;
		case MODIFICAR:
			modificar();
			break;
		case BORRAR:
			borrar();
			break;
		case FACTURAS:
			facturas();
			break;
		case FACTURAS_COMPLETAS:
			facturasCompletas();
			break;
		case SALIR:
			System.out.println("Gracias por utilizar esta aplicación");
			break;
		default:
			System.out.println("No conozco esa opción");
		}

		System.out.println();
	}

	private static void listado() {
		try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(SQL_SELECT)) {
			while (rs.next()) {
				// System.out.printf("%s;%s;%s;%s;%s;%s\n",
				System.out.printf("%2s %9s %3s %-20s %-40s %s\n", rs.getString("id"), rs.getString("dni"),
						rs.getString("dni_diferencial"), rs.getString("nombre"), rs.getString("apellidos"),
						rs.getString("fecha_nacimiento"));
			}
		} catch (SQLException e) {
			System.err.println("Error en el listado");
			System.err.println(e.getMessage());
			// e.printStackTrace();
		}
	}

	private static void buscar() {
		long id = leerLong("Introduce el id a buscar");
		obtenerPorId(id);
	}

	private static void insertar() {
		String dni = leerDni("DNI");
		Integer dniDiferencial = leerInt("DNI diferencial", OPCIONAL, 0, 127);
		String nombre = leerString("Nombre");
		String apellidos = leerString("Apellidos", OPCIONAL);
		LocalDate fechaNacimiento = leerFecha("Fecha de nacimiento", OPCIONAL, LocalDate.of(1900, 1, 1),
				LocalDate.now().minusYears(18));

		insertar(dni, dniDiferencial, nombre, apellidos, fechaNacimiento);
	}

	private static void modificar() {
		long id = leerLong("Introduce el id a modificar"); // NUEVA

		String dni = leerDni("DNI");
		Integer dniDiferencial = leerInt("DNI diferencial", OPCIONAL, 0, 127);
		String nombre = leerString("Nombre");
		String apellidos = leerString("Apellidos", OPCIONAL);
		LocalDate fechaNacimiento = leerFecha("Fecha de nacimiento", OPCIONAL, LocalDate.of(1900, 1, 1),
				LocalDate.now().minusYears(18));

		modificar(id, dni, dniDiferencial, nombre, apellidos, fechaNacimiento); // NUEVA
	}

	private static void borrar() {
		long id = leerLong("Introduce el id a borrar");
		borrar(id);
	}

	private static void facturas() {
		long id = leerLong("Introduce el id del cliente para ver sus facturas");
		obtenerPorIdConFacturas(id);
	}

	private static void facturasCompletas() {
		long id = leerLong("Introduce el id del cliente para ver sus facturas con los productos");
		obtenerPorIdConFacturasConProductos(id);
	}

	private static void obtenerPorId(long id) {
		try (CallableStatement cst = con.prepareCall(SQL_SELECT_ID)) {
			cst.setLong(1, id);

			try (ResultSet rs = cst.executeQuery()) {
				if (rs.next()) {
					mostrarCliente(rs);
				} else {
					System.out.println("No se ha encontrado el cliente cuyo id es " + id);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error al obtener por el id " + id);
			System.err.println(e.getMessage());
//			e.printStackTrace();
		}
	}

	private static void insertar(String dni, Integer dniDiferencial, String nombre, String apellidos,
			LocalDate fechaNacimiento) {
		try (CallableStatement cst = con.prepareCall(SQL_INSERT)) {
			cst.setString(1, dni);

			if (dniDiferencial == null) {
				dniDiferencial = 0;
			}

			cst.setInt(2, dniDiferencial);
			cst.setString(3, nombre);
			cst.setString(4, apellidos);
			cst.setDate(5, fechaNacimiento != null ? java.sql.Date.valueOf(fechaNacimiento) : null);

			int numeroRegistrosModificados = cst.executeUpdate();

			System.out.println(numeroRegistrosModificados);
		} catch (SQLException e) {
			System.err.println("Error en la inserción");
			System.err.println(e.getMessage());
//			e.printStackTrace();
		}
	}

	private static void modificar(long id, String dni, Integer dniDiferencial, String nombre, String apellidos,
			LocalDate fechaNacimiento) {
		try (CallableStatement cst = con.prepareCall(SQL_UPDATE)) {
			cst.setString(1, dni);

			if (dniDiferencial == null) {
				dniDiferencial = 0;
			}

			cst.setInt(2, dniDiferencial);
			cst.setString(3, nombre);
			cst.setString(4, apellidos);
			cst.setDate(5, fechaNacimiento != null ? java.sql.Date.valueOf(fechaNacimiento) : null);

			cst.setLong(6, id);

			int numeroRegistrosModificados = cst.executeUpdate();

			System.out.println(numeroRegistrosModificados);
		} catch (SQLException e) {
			System.err.println("Error en la modificación");
			System.err.println(e.getMessage());
//			e.printStackTrace();
		}
	}

	private static void borrar(long id) {
		try (CallableStatement cst = con.prepareCall(SQL_DELETE)) {
			cst.setLong(1, id);

			int numeroRegistrosModificados = cst.executeUpdate();

			System.out.println(numeroRegistrosModificados);
		} catch (SQLException e) {
			System.err.println("Error en el borrado");
			System.err.println(e.getMessage());
//			e.printStackTrace();
		}
	}

	private static void obtenerPorIdConFacturas(long id) {
		boolean fichaClienteMostrada = false;

		try (CallableStatement cst = con.prepareCall(SQL_SELECT_ID_FACTURAS)) {
			cst.setLong(1, id);

			try (ResultSet rs = cst.executeQuery()) {
				while (rs.next()) {
					if (!fichaClienteMostrada) {
						mostrarCliente(rs);

						fichaClienteMostrada = true;
					}

					System.out.printf("%s, %s, %s\n", rs.getString("f.id"), rs.getString("f.numero"),
							rs.getString("f.fecha"));
				}
			}
		} catch (SQLException e) {
			System.err.println("Error al obtener por el id " + id);
			System.err.println(e.getMessage());
//			e.printStackTrace();
		}
	}

	private static void obtenerPorIdConFacturasConProductos(long id) {
		boolean fichaClienteMostrada = false;
		Long idFacturaEnCurso = null;
		BigDecimal totalFactura = BigDecimal.ZERO;

		try (CallableStatement cst = con.prepareCall(SQL_SELECT_ID_FACTURAS_PRODUCTOS)) {
			cst.setLong(1, id);

			try (ResultSet rs = cst.executeQuery()) {
				cst.getString(1);
				while (rs.next()) {
					if (!fichaClienteMostrada) {
						mostrarCliente(rs);

						fichaClienteMostrada = true;
					}

					Long idFactura = rs.getLong("f.id");

					// Si hemos cambiado de factura o es la primera fila de la base de datos
					if (idFactura != idFacturaEnCurso) {

						// Si ya tenemos un total de factura almacenado, significa que tenemos un total
						// pendiente de visualizar de la factura anterior
						if (totalFactura != BigDecimal.ZERO) {
							mostrarTotalFactura(totalFactura);

							// Una vez mostrado el total de la factura anterior, ponemos a cero para volver
							// a acumular el total de la factura actual
							totalFactura = BigDecimal.ZERO;
						}

						mostrarFactura(rs);

						// Ahora consideramos factura en curso la nueva que acabamos de recibir
						idFacturaEnCurso = idFactura;
					}

					BigDecimal totalParcial = rs.getBigDecimal("total");
					totalFactura = totalFactura.add(totalParcial);

					mostrarProducto(rs, totalParcial);
				}

				// Al terminar la lista, debemos mostrar el total de factura de la última
				// factura
				mostrarTotalFactura(totalFactura);
			}
		} catch (SQLException e) {
			System.err.println("Error al obtener por el id " + id);
			System.err.println(e.getMessage());
//			e.printStackTrace();
		}
	}

	private static void mostrarCliente(ResultSet rs) throws SQLException {
		System.out.printf("""

				CLIENTE
				=======
				Id:                  %s
				DNI:                 %s%s
				Nombre:              %s
				Apellidos:           %s
				Fecha de nacimiento: %s

				""",
				// System.out.printf("%2s %s %3s %-3s %-20s %s\n",
				rs.getString("c.id"), rs.getString("c.dni"), rs.getString("c.dni_diferencial"),
				rs.getString("c.nombre"), rs.getString("c.apellidos"), rs.getString("c.fecha_nacimiento"));
	}

	private static void mostrarFactura(ResultSet rs) throws SQLException {
		System.out.printf("""

				FACTURA
				-------
				Id:      %s
				Número:  %s
				Fecha:   %s

					Id Nombre       Precio Cantidad     Total
					-----------------------------------------
				""", rs.getString("f.id"), rs.getString("f.numero"), rs.getString("f.fecha"));
	}

	private static void mostrarProducto(ResultSet rs, BigDecimal totalParcial) throws SQLException {
		System.out.printf("\t%2s %-12s %6s %8s %9s\n", rs.getString("p.id"), rs.getString("p.nombre"),
				rs.getString("p.precio"), rs.getString("fp.cantidad"), totalParcial);
	}

	private static void mostrarTotalFactura(BigDecimal total) {
		System.out.println("\nTotal de factura: " + total + "\n");
	}
}
