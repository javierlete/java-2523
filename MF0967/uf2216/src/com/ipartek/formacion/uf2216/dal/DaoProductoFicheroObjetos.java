package com.ipartek.formacion.uf2216.dal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.TreeMap;

import com.ipartek.formacion.uf2216.pojos.Producto;

public class DaoProductoFicheroObjetos extends DaoProductoCSV {

	public DaoProductoFicheroObjetos(String fichero) {
		super(fichero);
	}

	@Override
	protected void guardarFichero() {
		try (FileOutputStream fos = new FileOutputStream(fichero);
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(productos);
		} catch (IOException e) {
			throw new DalException("No se ha podido escribir el fichero " + fichero, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected TreeMap<Long, Producto> leerFichero() {
		try (FileInputStream fis = new FileInputStream(fichero); ObjectInputStream ois = new ObjectInputStream(fis)) {
			return (TreeMap<Long, Producto>) ois.readObject();
		} catch (FileNotFoundException e) {
			guardarFichero();
			return productos;
		} catch (IOException | ClassNotFoundException e) {
			throw new DalException("No se ha podido leer el fichero " + fichero, e);
		}
	}

}
