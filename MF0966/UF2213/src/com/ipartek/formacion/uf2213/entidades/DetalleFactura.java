package com.ipartek.formacion.uf2213.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalles_factura")
public class DetalleFactura {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "factura_id", nullable = false, foreignKey = @ForeignKey(name = "FK_detalle_factura"))
	private Factura factura;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "producto_id", nullable = false, foreignKey = @ForeignKey(name = "FK_detalle_producto"))
	private Producto producto;
	
	@Column(name = "cantidad", nullable = false)
	private Integer cantidad;

	public DetalleFactura() {
		super();
	}

	public DetalleFactura(Long id, Factura factura, Producto producto, Integer cantidad) {
		super();
		this.id = id;
		this.factura = factura;
		this.producto = producto;
		this.cantidad = cantidad;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "DetalleFactura [id=" + id + ", factura=" + factura + ", producto=" + producto + ", cantidad=" + cantidad
				+ "]";
	}
	
	
}
