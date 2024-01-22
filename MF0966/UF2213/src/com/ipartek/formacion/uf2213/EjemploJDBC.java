package com.ipartek.formacion.uf2213;

import static com.ipartek.formacion.bibliotecas.Consola.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class EjemploJDBC {
	private static final String URL = "jdbc:mysql://localhost:3306/manana_tienda";
	private static final String USER = "root";
	private static final String PASS = "1234";

	private static final String SQL_CAMPOS = "dni, dni_diferencial, nombre, apellidos, fecha_nacimiento";

	private static final String SQL_SELECT = "SELECT id," + SQL_CAMPOS + " FROM clientes";
	private static final String SQL_SELECT_ID = "SELECT id," + SQL_CAMPOS + " FROM clientes WHERE id=?";

	private static final String SQL_INSERT = "INSERT INTO clientes (" + SQL_CAMPOS + ") VALUES (?,?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE clientes SET dni=?, dni_diferencial=?, nombre=?, apellidos=?, fecha_nacimiento=? WHERE id=?";
	private static final String SQL_DELETE = "DELETE FROM clientes WHERE id=?";
	private static final int SALIR = 0;
	private static final int LISTADO = 1;
	private static final int BUSCAR = 2;
	private static final int INSERTAR = 3;
	private static final int MODIFICAR = 4;
	private static final int BORRAR = 5;

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

				0. SALIR
				""");
	}

	private static int pedirOpcion() {
		return leerInt("Introduce la opción elegida");
	}

	private static void ejecutar(int opcion) {
		System.out.println("Ejecutando opción " + opcion);

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
		case SALIR:
			System.out.println("Gracias por utilizar esta aplicación");
			break;
		default:
			System.out.println("No conozco esa opción");
		}
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
		LocalDate fechaNacimiento = leerFecha("Fecha de nacimiento", OPCIONAL, LocalDate.of(1900, 1, 1), LocalDate.now().minusYears(18));

		insertar(dni, dniDiferencial, nombre, apellidos, fechaNacimiento);
	}

	private static void modificar() {
		long id = leerLong("Introduce el id a modificar"); // NUEVA

		String dni = leerDni("DNI");
		Integer dniDiferencial = leerInt("DNI diferencial", OPCIONAL, 0, 127);
		String nombre = leerString("Nombre");
		String apellidos = leerString("Apellidos", OPCIONAL);
		LocalDate fechaNacimiento = leerFecha("Fecha de nacimiento", OPCIONAL, LocalDate.of(1900, 1, 1), LocalDate.now().minusYears(18));

		modificar(id, dni, dniDiferencial, nombre, apellidos, fechaNacimiento); // NUEVA
	}

	private static void borrar() {
		long id = leerLong("Introduce el id a borrar");
		borrar(id);
	}

	private static void obtenerPorId(long id) {
		try (PreparedStatement pst = con.prepareStatement(SQL_SELECT_ID)) {
			pst.setLong(1, id);

			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					System.out.printf("""
							Id:                  %s
							DNI:                 %s%s
							Nombre:              %s
							Apellidos:           %s
							Fecha de nacimiento: %s\n
							""",
							// System.out.printf("%2s %s %3s %-3s %-20s %s\n",
							rs.getString("id"), rs.getString("dni"), rs.getString("dni_diferencial"),
							rs.getString("nombre"), rs.getString("apellidos"), rs.getString("fecha_nacimiento"));
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
		try (PreparedStatement pst = con.prepareStatement(SQL_INSERT)) {
			pst.setString(1, dni);

			if (dniDiferencial == null) {
				dniDiferencial = 0;
			}

			pst.setInt(2, dniDiferencial);
			pst.setString(3, nombre);
			pst.setString(4, apellidos);
			pst.setDate(5, fechaNacimiento != null ? java.sql.Date.valueOf(fechaNacimiento) : null);

			int numeroRegistrosModificados = pst.executeUpdate();

			System.out.println(numeroRegistrosModificados);
		} catch (SQLException e) {
			System.err.println("Error en la inserción");
			System.err.println(e.getMessage());
//			e.printStackTrace();
		}
	}

	private static void modificar(long id, String dni, Integer dniDiferencial, String nombre, String apellidos,
			LocalDate fechaNacimiento) {
		try (PreparedStatement pst = con.prepareStatement(SQL_UPDATE)) {
			pst.setString(1, dni);
			
			if (dniDiferencial == null) {
				dniDiferencial = 0;
			}
			
			pst.setInt(2, dniDiferencial);
			pst.setString(3, nombre);
			pst.setString(4, apellidos);
			pst.setDate(5, fechaNacimiento != null ? java.sql.Date.valueOf(fechaNacimiento) : null);

			pst.setLong(6, id);

			int numeroRegistrosModificados = pst.executeUpdate();

			System.out.println(numeroRegistrosModificados);
		} catch (SQLException e) {
			System.err.println("Error en la modificación");
			System.err.println(e.getMessage());
//			e.printStackTrace();
		}
	}

	private static void borrar(long id) {
		try (PreparedStatement pst = con.prepareStatement(SQL_DELETE)) {
			pst.setLong(1, id);

			int numeroRegistrosModificados = pst.executeUpdate();

			System.out.println(numeroRegistrosModificados);
		} catch (SQLException e) {
			System.err.println("Error en el borrado");
			System.err.println(e.getMessage());
//			e.printStackTrace();
		}
	}
}
