package com.ipartek.formacion.uf2213.entidades;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "facturas")
public class Factura {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "numero", columnDefinition = "CHAR(8)", nullable = false)
	private String numero;
	
	@Column(name = "fecha", nullable = false)
	private LocalDate fecha;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false, foreignKey = @ForeignKey(name = "FK_factura_cliente"))
	private Cliente cliente;
	
	@OneToMany(mappedBy = "factura", cascade = CascadeType.ALL)
	private Set<DetalleFactura> detalles = new HashSet<>();
	
	public Factura() {
	}

	public Factura(Long id, String numero, LocalDate fecha, Cliente cliente) {
		super();
		this.id = id;
		this.numero = numero;
		this.fecha = fecha;
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<DetalleFactura> getDetalles() {
		return detalles;
	}

	public void setDetalles(Set<DetalleFactura> detalles) {
		this.detalles = detalles;
	}

	@Override
	public String toString() {
		return "Factura [id=" + id + ", numero=" + numero + ", fecha=" + fecha + ", cliente=" + cliente + "]";
	}
}
