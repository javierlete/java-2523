<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<main>
	<c:if test="${curso != null}">
		<h1>Alta en ${curso.nombre()} de nuevo alumno</h1>
	</c:if>

	<form action="formulario" method="post">
		<input name="id-curso" type="hidden" value="${curso.id()}"> <input
			name="id" type="hidden" value="${alumno.id()}"> <input
			name="nombre" placeholder="Nombre" value="${alumno.nombre()}">
		<input name="apellidos" placeholder="Apellidos"
			value="${alumno.apellidos()}"> <input name="fecha-nacimiento"
			type="date" placeholder="Fecha de Nacimiento"
			value="${alumno.fechaNacimiento()}">

		<button>${curso != null ? 'Alta en el curso' : 'Guardar' }</button>
	</form>
</main>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>