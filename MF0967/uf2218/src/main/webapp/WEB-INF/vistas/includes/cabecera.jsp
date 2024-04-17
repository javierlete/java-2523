<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UF2218</title>

<base href="${pageContext.request.contextPath}/">

<link rel="stylesheet" href="css/uf2218.css">

</head>
<body>
	<header>
		<h1>UF2218</h1>
		<p>${sessionScope.alumno.nombre()}</p>
	</header>
	<nav>
		<ul>
			<li><a href="cursos">Cursos</a></li>
			<li><a href="login">Login</a></li>
			<li><a href="logout">Logout</a></li>
			<li><a href="formulario">Registro</a></li>
			<li><a href="alumno/cursos">Cursos del alumno</a></li>
		</ul>
	</nav>