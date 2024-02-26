package com.ipartek.formacion.guasa.entidades;

import java.time.LocalDateTime;

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
@Table(name = "mensajes")
public class Mensaje {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "fecha", nullable = false)
	private LocalDateTime fecha = LocalDateTime.now();
	
	@Column(name = "contenido", length = 2000, nullable = false)
	private String contenido; // MarkDown
	
	@Column(name = "reacciones", length = 255)
	private String reacciones; // :smile:,2,:sad:,5
	
	@Column(name="destacado", nullable = false)
	private Boolean destacado = false;

	@ManyToOne
	@JoinColumn(name = "chat_id", nullable = false, foreignKey = @ForeignKey(name = "FK_mensaje_chat"))
	private Chat chat;
	
	@ManyToOne
	@JoinColumn(name = "estado_mensaje_id", nullable = false, foreignKey = @ForeignKey(name = "FK_mensaje_estado_mensaje"))
	private EstadoMensaje estadoMensaje;

	@ManyToOne
	@JoinColumn(name = "respuesta_de_id", nullable = true, foreignKey = @ForeignKey(name = "FK_mensaje_respuesta_de"))
	private Mensaje respuestaDe;
	
	@ManyToOne
	@JoinColumn(name = "contacto_id", nullable = false, foreignKey = @ForeignKey(name = "FK_mensaje_contacto"))
	private Contacto contacto;
}
