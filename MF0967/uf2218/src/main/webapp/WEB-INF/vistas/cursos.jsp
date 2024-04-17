<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<main>
	<p>
		<a href="formulario">Nuevo alumno</a>
	</p>

	<ul>
		<c:forEach items="${cursos}" var="curso">
			<li>${curso.nombre()}<a href="formulario?id=${curso.id()}">Apúntame</a>
				<a href="listado?id=${curso.id()}">Listado de alumnos</a>
			</li>
		</c:forEach>
	</ul>
</main>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>