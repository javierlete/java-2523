package com.ipartek.formacion.uf2216.dal;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Scanner;
import java.util.TreeMap;

import com.ipartek.formacion.uf2216.pojos.Producto;

// Comma Separated Values
public class DaoProductoCSV extends DaoProductoTreeMap {
	protected String fichero;

	public DaoProductoCSV(String fichero) {
		super();
		productos.clear();
		this.fichero = fichero;
	}

	protected TreeMap<Long, Producto> leerFichero() {
		FileReader fr = null;
		Scanner sc = null;

		try {
			fr = new FileReader(fichero);
			sc = new Scanner(fr);

			String linea;
			String[] pedazos;

			Producto producto;

			Long id;
			String nombre;
			BigDecimal precio;
			Integer stock;
			Boolean disponible;

			if (sc.hasNext()) {
				// Saltar la línea de títulos
				sc.nextLine();
			}

			while (sc.hasNext()) {
				linea = sc.nextLine();

				pedazos = linea.split(";");

				id = Long.valueOf(pedazos[0]);
				nombre = pedazos[1];
				precio = new BigDecimal(pedazos[2].replace(",", "."));
				stock = Integer.valueOf(pedazos[3]);
				disponible = Boolean.valueOf(pedazos[4]);

				producto = new Producto(id, nombre, precio, stock, disponible);

				productos.put(producto.getId(), producto);
			}

			return productos;
		} catch (NumberFormatException e) {
			throw new DalException("Un dato recibido que debía ser numérico no lo es", e);
		} catch (FileNotFoundException e) {
			throw new DalException("No se ha encontrado el fichero CSV " + fichero, e);
		} finally {
			if (sc != null) {
				sc.close();
				sc = null;
			}

			if (fr != null) {
				try {
					fr.close();
					fr = null;
				} catch (IOException e) {
					throw new DalException("No se ha podido cerrar el fichero", e);
				}
			}
		}
	}

	protected void guardarFichero() {
		try (FileWriter fw = new FileWriter(fichero);
				PrintWriter pw = new PrintWriter(fw)) {
			pw.println("id;nombre;precio;stock;disponible");
			
			for(var p: productos.values()) {
				pw.printf("%s;%s;%s;%s;%s\n", p.getId(), p.getNombre(), p.getPrecio(), p.getStock(), p.getDisponible());
			}
		} catch (IOException e) {
			throw new DalException("No se ha podido escribir el fichero", e);
		}
	}

	@Override
	public Iterable<Producto> obtenerTodos() {
		leerFichero();
		
		return super.obtenerTodos();
	}

	@Override
	public Producto obtenerPorId(Long id) {
		leerFichero();
		
		return super.obtenerPorId(id);
	}

	@Override
	public Producto insertar(Producto producto) {
		leerFichero();
		
		var p = super.insertar(producto);
		
		guardarFichero();
		
		return p;
	}

	@Override
	public Producto modificar(Producto producto) {
		leerFichero();
		
		var p = super.modificar(producto);
		
		guardarFichero();
		
		return p;
	}

	@Override
	public void borrar(Long id) {
		super.borrar(id);
		
		guardarFichero();
	}

	@Override
	public Iterable<Producto> buscarPorNombre(String nombre) {
		leerFichero();
		
		return super.buscarPorNombre(nombre);
	}

}
