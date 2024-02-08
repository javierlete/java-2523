package com.ipartek.formacion.uf2213.presentacion;

import static com.ipartek.formacion.bibliotecas.Consola.*;
import static com.ipartek.formacion.uf2213.accesodatos.AccesoDatosJpa.*;
import static com.ipartek.formacion.uf2213.accesodatos.ClienteAccesoDatosJpa.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;

import com.ipartek.formacion.uf2213.accesodatos.ClienteAccesoDatosJpa;
import com.ipartek.formacion.uf2213.accesodatos.FacturaAccesoDatosJpa;
import com.ipartek.formacion.uf2213.dtos.ClienteDTO;
import com.ipartek.formacion.uf2213.dtos.FacturaDTO;
import com.ipartek.formacion.uf2213.dtos.ProductoDTO;

public class Consola {
	private static final int SALIR = 0;
	private static final int LISTADO = 1;
	private static final int BUSCAR = 2;
	private static final int INSERTAR = 3;
	private static final int MODIFICAR = 4;
	private static final int BORRAR = 5;
	private static final int FACTURAS = 6;
	private static final int FACTURAS_COMPLETAS = 7;

	public static void main(String[] args) throws Exception {
		abrirConexion();

		int opcion;
		do {
			mostrarMenu();
			opcion = pedirOpcion();
			ejecutar(opcion);
		} while (opcion != SALIR);

		cerrarConexion();
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

	private static void ejecutar(int opcion) throws Exception {
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

	private static void listado() throws Exception {
		for (ClienteDTO cliente : obtenerTodos()) {
			System.out.printf("%2s %9s %3s %-20s %-20s %s\n", cliente.id(), cliente.dni(), cliente.dniDiferencial(),
					cliente.nombre(), cliente.apellidos(), cliente.fechaNacimiento());
		}
	}

	private static void buscar() throws Exception {
		long id = leerLong("Introduce el id a buscar");
		obtenerPorId(id);
	}

	private static void insertar() throws Exception {
		String dni = leerDni("DNI");
		Integer dniDiferencial = leerInt("DNI diferencial", OPCIONAL, 0, 127);
		String nombre = leerString("Nombre");
		String apellidos = leerString("Apellidos", OPCIONAL);
		LocalDate fechaNacimiento = leerFecha("Fecha de nacimiento", OPCIONAL, LocalDate.of(1900, 1, 1),
				LocalDate.now().minusYears(18));

		ClienteAccesoDatosJpa.insertar(new ClienteDTO(null, dni, dniDiferencial, nombre, apellidos, fechaNacimiento));
	}

	private static void modificar() throws Exception {
		long id = leerLong("Introduce el id a modificar");

		String dni = leerDni("DNI");
		Integer dniDiferencial = leerInt("DNI diferencial", OPCIONAL, 0, 127);
		String nombre = leerString("Nombre");
		String apellidos = leerString("Apellidos", OPCIONAL);
		LocalDate fechaNacimiento = leerFecha("Fecha de nacimiento", OPCIONAL, LocalDate.of(1900, 1, 1),
				LocalDate.now().minusYears(18));

		ClienteAccesoDatosJpa.modificar(new ClienteDTO(id, dni, dniDiferencial, nombre, apellidos, fechaNacimiento));
	}

	private static void borrar() throws Exception {
		long id = leerLong("Introduce el id a borrar");
		ClienteAccesoDatosJpa.borrar(id);
	}

	private static void facturas() throws Exception {
		long id = leerLong("Introduce el id del cliente para ver sus facturas");
		obtenerPorIdConFacturas(id);
	}

	private static void facturasCompletas() throws Exception {
		long id = leerLong("Introduce el id del cliente para ver sus facturas con los productos");
		obtenerPorIdConFacturasConProductos(id);
	}

	private static void obtenerPorId(long id) throws Exception {
		ClienteDTO cliente = ClienteAccesoDatosJpa.obtenerPorId(id);
		if (cliente != null) {
			mostrarCliente(cliente);
		} else {
			System.out.println("No se ha encontrado el cliente cuyo id es " + id);
		}
	}

	private static void obtenerPorIdConFacturas(long id) throws Exception {
		boolean fichaClienteMostrada = false;

		for (FacturaDTO factura : FacturaAccesoDatosJpa.obtenerPorIdCliente(id)) {
			if (!fichaClienteMostrada) {
				mostrarCliente(factura.cliente());

				fichaClienteMostrada = true;
			}

			System.out.printf("%s, %s, %s\n", factura.id(), factura.numero(), factura.fecha());
		}
	}

	public static void obtenerPorIdConFacturasConProductos(long id) throws SQLException, Exception {
		boolean fichaClienteMostrada = false;

		BigDecimal totalFactura = BigDecimal.ZERO;
		BigDecimal totalParcial = null;

		for (FacturaDTO factura : FacturaAccesoDatosJpa.obtenerPorIdClienteConProductos(id)) {
			
			System.out.println(factura);
			
			if (!fichaClienteMostrada) {
				mostrarCliente(factura.cliente());

				fichaClienteMostrada = true;
			}

			mostrarFactura(factura);

			for(ProductoDTO producto: factura.productos()) {
				totalParcial = producto.precio().multiply(new BigDecimal(producto.cantidad()));
				totalFactura = totalFactura.add(totalParcial);

				mostrarProducto(producto, totalParcial);
			}
			
			mostrarTotalFactura(totalFactura);

			totalFactura = BigDecimal.ZERO;
		}
	}

	private static void mostrarCliente(ClienteDTO cliente) throws Exception {
		System.out.printf("""

				CLIENTE
				=======
				Id:                  %s
				DNI:                 %s%s
				Nombre:              %s
				Apellidos:           %s
				Fecha de nacimiento: %s

				""", cliente.id(), cliente.dni(), cliente.dniDiferencial(), cliente.nombre(), cliente.apellidos(),
				cliente.fechaNacimiento());
	}

	private static void mostrarFactura(FacturaDTO factura) throws SQLException {
		System.out.printf("""

				FACTURA
				-------
				Id:      %s
				Número:  %s
				Fecha:   %s

					Id Nombre       Precio Cantidad     Total
					-----------------------------------------
				""", factura.id(), factura.numero(), factura.fecha());
	}

	private static void mostrarProducto(ProductoDTO producto, BigDecimal totalParcial) throws SQLException {
		System.out.printf("\t%2s %-12s %6s %8s %9s\n", producto.id(), producto.nombre(),
				producto.precio(), producto.cantidad(), totalParcial);
	}

	private static void mostrarTotalFactura(BigDecimal total) {
		System.out.println("\nTotal de factura: " + total + "\n");
	}

	public static void pruebaDeAccesoDatos() throws Exception {
		abrirConexion();

		ClienteAccesoDatosJpa.modificar(new ClienteDTO(35L, "12345678B", null, "MODIFICADO", "MODIFICADEZ", null));

		ClienteAccesoDatosJpa.borrar(36L);

		ClienteAccesoDatosJpa.insertar(new ClienteDTO(null, "12345678A", null, "Con Acceso Datos", null, null));

		for (ClienteDTO cliente : obtenerTodos()) {
			System.out.println(cliente);
		}

		ClienteDTO cliente = ClienteAccesoDatosJpa.obtenerPorId(1L);
		System.out.println(cliente);

	}
}
