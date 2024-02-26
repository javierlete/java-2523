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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "chats")
public class Chat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "titulo", length = 100, nullable = false)
	private String titulo;
	
	@Column(name = "archivado", nullable = false)
	private Boolean archivado = false;
	
	@Column(name = "fijado", nullable = false)
	private Boolean fijado = false;

	@ManyToMany
	@JoinTable(name = "chat_administradores", joinColumns = @JoinColumn(name = "chat_id"), inverseJoinColumns = @JoinColumn(name = "admin_id"), foreignKey = @ForeignKey(name = "FK_chat_admins_chat"), inverseForeignKey = @ForeignKey(name = "FK_chat_admins_admin"))
	private List<Contacto> admins;

	@ManyToMany
	@JoinTable(name = "chat_contactos", joinColumns = @JoinColumn(name = "chat_id"), inverseJoinColumns = @JoinColumn(name = "contacto_id"), foreignKey = @ForeignKey(name = "FK_chat_contactos_chat"), inverseForeignKey = @ForeignKey(name = "FK_chat_contactos_contacto"))
	private List<Contacto> contactos;

	@OneToMany(mappedBy = "chat")
	private List<Mensaje> mensajes;
}
