package com.ipartek.formacion.uf2216.dal;

import java.util.Properties;

public class FabricaDaoTipos implements FabricaDao {

	private static final String FABRICADAO_PROPERTIES = "fabricadao.properties";
	
	private final DaoProducto daoProducto;

	public FabricaDaoTipos() {
		this(FABRICADAO_PROPERTIES);
	}
	
	public FabricaDaoTipos(String configuracion) {
		try {
			Properties props = new Properties();
			props.load(FabricaDaoTipos.class.getClassLoader().getResourceAsStream(configuracion));

			String tipo = props.getProperty("tipo");
			String fichero = props.getProperty("fichero");

			daoProducto = switch (tipo) {
			case "arraylist" -> DaoProductoArrayList.getInstancia();
			case "treemap" -> DaoProductoTreeMap.getInstancia();
			case "csv" -> new DaoProductoCSV(fichero);
			case "objetos" -> new DaoProductoFicheroObjetos(fichero);
			default -> throw new DalException("NO se reconoce el tipo " + tipo);
			};
		} catch (Exception e) {
			throw new DalException("Error al leer la configuración", e);
		} finally {
			
		}

	}

	@Override
	public DaoProducto getDaoProducto() {
		return daoProducto;
	}

}
