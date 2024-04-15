<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Alta en curso de nuevo alumno</title>
</head>
<body>
	<h1>Alta en ${curso.nombre()} de nuevo alumno</h1>
	
	<form action="formulario" method="post">
		<input name="id-curso" type="text" value="${curso.id()}">
		<input name="nombre" placeholder="Nombre">
		<input name="apellidos" placeholder="Apellidos">
		<input name="fecha-nacimiento" type="date" placeholder="Fecha de Nacimiento">
		<button>Alta en el curso</button>
	</form>
</body>
</html>