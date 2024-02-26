package com.ipartek.formacion.guasa.entidades;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "contactos", uniqueConstraints = {
		@UniqueConstraint(name = "UK_contacto_telefono", columnNames = { "telefono" }) })
public class Contacto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "telefono", columnDefinition = "CHAR(9)", length = 9, nullable = false)
	private String telefono;

	@Column(name = "nombre", length = 50, nullable = false)
	private String nombre;

	@Column(name = "apellido", length = 50)
	private String apellido;

	@ManyToMany
	@JoinTable(name = "estados_contactos", joinColumns = @JoinColumn(name = "contacto_id"), inverseJoinColumns = @JoinColumn(name = "estado_id"), foreignKey = @ForeignKey(name = "FK_contacto_estado_contacto_contacto"), inverseForeignKey = @ForeignKey(name = "FK_contacto_estado_contacto_estado_contacto"))
	private List<Mensaje> estadoContacto;

	@ManyToMany
	@JoinTable(name = "lista_contactos", joinColumns = @JoinColumn(name = "propietario_id"), inverseJoinColumns = @JoinColumn(name = "contacto_id"), foreignKey = @ForeignKey(name = "FK_contacto_contactos_contacto"))
	private List<Contacto> contactos;

	@ManyToMany(mappedBy = "contactos")
	private List<Chat> chats;
}
