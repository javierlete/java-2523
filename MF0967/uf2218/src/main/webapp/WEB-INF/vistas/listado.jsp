<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<main>
	<h1>Listado de alumnos del curso ${curso.nombre()}</h1>
	<ul>
		<c:forEach items="${alumnos}" var="alumno">
			<li>${alumno.nombre()} ${alumno.apellidos()} <a
				href="formulario?id-alumno=${alumno.id()}">Editar</a>
			</li>
		</c:forEach>
	</ul>
</main>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>