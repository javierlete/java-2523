<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listado de alumnos del curso ${curso.nombre()}</title>
</head>
<body>
	<h1>Listado de alumnos del curso ${curso.nombre()}</h1>
	<ul>
		<c:forEach items="${alumnos}" var="alumno">
			<li>
				${alumno.nombre()} ${alumno.apellidos()}
				<a href="formulario?id-alumno=${alumno.id()}">Editar</a>
			</li>
		</c:forEach>
	</ul>
</body>
</html>