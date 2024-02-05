package com.ipartek.formacion.uf2213.accesodatos;

import static com.ipartek.formacion.uf2213.accesodatos.AccesoDatos.*;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;

import com.ipartek.formacion.uf2213.dtos.ClienteDTO;
import com.ipartek.formacion.uf2213.dtos.FacturaDTO;
import com.ipartek.formacion.uf2213.dtos.ProductoDTO;

public class FacturaAccesoDatos {
	private static final String SQL_SELECT_ID_FACTURAS = "CALL clientes_facturas(?)";
	private static final String SQL_SELECT_ID_FACTURAS_PRODUCTOS = "CALL facturas_productos_totales(?)";

	public static LinkedHashSet<FacturaDTO> obtenerPorIdCliente(Long id) throws SQLException {
		ClienteDTO cliente = null;
		var facturas = new LinkedHashSet<FacturaDTO>();
		FacturaDTO factura = null;

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

	public static LinkedHashSet<FacturaDTO> obtenerPorIdClienteConProductos(long id) throws SQLException {
		boolean fichaClienteMostrada = false;

		Long idFacturaEnCurso = null;
		FacturaDTO facturaActual = null;

		ClienteDTO cliente = null;

		var productos = new LinkedHashSet<ProductoDTO>();

		var facturas = new LinkedHashSet<FacturaDTO>();

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

					productos = new LinkedHashSet<ProductoDTO>();
				}
				
				idFacturaEnCurso = idFactura;
			}

			facturaActual = filaAFactura(rs);
			productos.add(filaAProducto(rs));
		}

		facturas.add(completarFactura(cliente, facturaActual, productos));

		return facturas;
	}

	private static ProductoDTO filaAProducto(ResultSet rs) throws SQLException {
		return new ProductoDTO(rs.getLong("p.id"), rs.getString("p.nombre"), rs.getBigDecimal("p.precio"),
				rs.getInt("fp.cantidad"));
	}

	public static FacturaDTO filaAFactura(ResultSet rs) throws SQLException {
		return new FacturaDTO(rs.getLong("f.id"), rs.getString("f.numero"), rs.getDate("f.fecha").toLocalDate(), null,
				null);
	}

	public static FacturaDTO completarFactura(ClienteDTO cliente, FacturaDTO factura, LinkedHashSet<ProductoDTO> productos)
			throws SQLException {
		return new FacturaDTO(factura.id(), factura.numero(), factura.fecha(), cliente, productos);
	}
}
