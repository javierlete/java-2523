package com.ipartek.formacion.uf2213.accesodatos;

import static com.ipartek.formacion.uf2213.accesodatos.AccesoDatos.*;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;

import com.ipartek.formacion.uf2213.entidades.Cliente;
import com.ipartek.formacion.uf2213.entidades.Factura;
import com.ipartek.formacion.uf2213.entidades.Producto;

public class FacturaAccesoDatos {
	private static final String SQL_SELECT_ID_FACTURAS = "CALL clientes_facturas(?)";
	private static final String SQL_SELECT_ID_FACTURAS_PRODUCTOS = "CALL facturas_productos_totales(?)";

	public static LinkedHashSet<Factura> obtenerPorIdCliente(Long id) throws SQLException {
		Cliente cliente = null;
		var facturas = new LinkedHashSet<Factura>();
		Factura factura = null;

		boolean fichaClienteMostrada = false;

		CallableStatement cst = con.prepareCall(SQL_SELECT_ID_FACTURAS);
		cst.setLong(1, id);

		try (ResultSet rs = cst.executeQuery()) {
			while (rs.next()) {
				if (!fichaClienteMostrada) {
					cliente = ClienteAccesoDatos.filaACliente(rs);

					fichaClienteMostrada = true;
				}

				factura = completarFactura(cliente, filaAFactura(rs), null);

				facturas.add(factura);
			}
		}

		return facturas;
	}

	public static LinkedHashSet<Factura> obtenerPorIdClienteConProductos(long id) throws SQLException {
		boolean fichaClienteMostrada = false;

		Long idFacturaEnCurso = null;
		Factura facturaActual = null;

		Cliente cliente = null;

		var productos = new LinkedHashSet<Producto>();

		var facturas = new LinkedHashSet<Factura>();

		CallableStatement cst = con.prepareCall(SQL_SELECT_ID_FACTURAS_PRODUCTOS);
		cst.setLong(1, id);

		ResultSet rs = cst.executeQuery();

		while (rs.next()) {
			if (!fichaClienteMostrada) {
				cliente = ClienteAccesoDatos.filaACliente(rs);

				fichaClienteMostrada = true;
			}

			Long idFactura = rs.getLong("f.id");

			if (idFactura != idFacturaEnCurso) {
				if (idFacturaEnCurso != null) {
					facturas.add(completarFactura(cliente, facturaActual, productos));

					productos = new LinkedHashSet<Producto>();
				}
				
				idFacturaEnCurso = idFactura;
			}

			facturaActual = filaAFactura(rs);
			productos.add(filaAProducto(rs));
		}

		facturas.add(completarFactura(cliente, facturaActual, productos));

		return facturas;
	}

	private static Producto filaAProducto(ResultSet rs) throws SQLException {
		return new Producto(rs.getLong("p.id"), rs.getString("p.nombre"), rs.getBigDecimal("p.precio"),
				rs.getInt("fp.cantidad"));
	}

	public static Factura filaAFactura(ResultSet rs) throws SQLException {
		return new Factura(rs.getLong("f.id"), rs.getString("f.numero"), rs.getDate("f.fecha").toLocalDate(), null,
				null);
	}

	public static Factura completarFactura(Cliente cliente, Factura factura, LinkedHashSet<Producto> productos)
			throws SQLException {
		return new Factura(factura.id(), factura.numero(), factura.fecha(), cliente, productos);
	}
}
