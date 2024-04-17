<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listado de cursos</title>
</head>

<body>
	<p>${sessionScope.alumno.nombre()}</p>

	<p>
		<a href="formulario">Nuevo alumno</a>
	</p>

	<ul>
		<c:forEach items="${cursos}" var="curso">
			<li>${curso.nombre()} <a href="formulario?id=${curso.id()}">Apúntame</a>
				<a href="listado?id=${curso.id()}">Listado de alumnos</a>
			</li>
		</c:forEach>
	</ul>
</body>
</html>