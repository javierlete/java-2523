<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UF2218</title>

<base href="${pageContext.request.contextPath}/">

</head>
<body>
	<header>
		<h1>UF2218</h1>
		<p>${sessionScope.alumno.nombre()}</p>
	</header>