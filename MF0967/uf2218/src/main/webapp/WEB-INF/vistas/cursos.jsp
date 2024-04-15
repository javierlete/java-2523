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
	<ul>
		<c:forEach items="${cursos}" var="curso">
			<li>
				${curso.nombre()}
				<a href="formulario?id=${curso.id()}">Apúntame</a>
			</li>
		</c:forEach>
	</ul>
</body>
</html>