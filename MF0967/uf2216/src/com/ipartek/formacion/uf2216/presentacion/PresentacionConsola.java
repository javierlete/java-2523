package com.ipartek.formacion.uf2216.presentacion;

import static com.ipartek.formacion.bibliotecas.Consola.*;

import com.ipartek.formacion.uf2216.dal.DaoProducto;
import com.ipartek.formacion.uf2216.dal.FabricaDaoTipos;
import com.ipartek.formacion.uf2216.pojos.Producto;

public class PresentacionConsola {
	private static final int SALIR = 0;

	private static final DaoProducto DAO = new FabricaDaoTipos().getDaoProducto();

	public static void main(String[] args) {
		int opcion;

		do {
			mostrarMenu();
			opcion = pedirOpcion();
			procesarOpcion(opcion);
		} while (opcion != SALIR);
	}

	private static void mostrarMenu() {
		pl("1. LISTADO");
		pl("2. OBTENER POR ID");
		pl();
		pl("3. INSERTAR");
		pl("4. MODIFICAR");
		pl("5. BORRAR");
		pl();
		pl("0. SALIR");
		pl();
	}

	private static int pedirOpcion() {
		return leerInt("Selecciona una opción", OBLIGATORIO);
	}

	private static void procesarOpcion(int opcion) {
		switch (opcion) {
		case 1 -> listado();
		case 2 -> buscar();
		case 3 -> insertar();
		case 4 -> modificar();
		case 5 -> borrar();
		case 0 -> salir();
		default -> pl("Opción no reconocida");
		}
	}

	private static void listado() {
		for (var producto : DAO.obtenerTodos()) {
			mostrarFila(producto);
		}
	}

	private static void buscar() {
		Long id = leerLong("Dime el id", OBLIGATORIO);

		var producto = DAO.obtenerPorId(id);

		mostrarFicha(producto);
	}

	private static void insertar() {
		var producto = new Producto(null, leerString("Nombre", OBLIGATORIO), leerBigDecimal("Precio", OBLIGATORIO), leerInt("Stock"),
				leerBoolean("Disponible"));
		
		DAO.insertar(producto);
	}

	private static void modificar() {
		var producto = new Producto(leerLong("Id", OBLIGATORIO), leerString("Nombre", OBLIGATORIO), leerBigDecimal("Precio", OBLIGATORIO), leerInt("Stock"),
				leerBoolean("Disponible"));
		
		DAO.modificar(producto);
	}

	private static void borrar() {
		Long id = leerLong("Dime el id", OBLIGATORIO);
		
		DAO.borrar(id);
	}

	private static void salir() {
		pl("Gracias por usar esta aplicación");
	}

	private static void mostrarFila(Producto producto) {
		pfl("| %03d | %-30s | %,10.2f € | %4d | %13s |", producto.getId(), producto.getNombre(), producto.getPrecio(),
				producto.getStock(), producto.getDisponible() ? "DISPONIBLE" : "NO DISPONIBLE");
	}

	private static void mostrarFicha(Producto producto) {
		pl();
		pfl("%10s: %s", "Id", producto.getId());
		pfl("%10s: %s", "Nombre", producto.getNombre());
		pfl("%10s: %,.2f", "Precio", producto.getPrecio());
		pfl("%10s: %d", "Stock", producto.getStock());
		pfl("%10s: %s", "Disponible", producto.getDisponible() ? "SÍ" : "NO");
		pl();
	}
}
