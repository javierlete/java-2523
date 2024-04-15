<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.ipartek.formacion.fullstack.dtos.CursoDto"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listado de cursos</title>
</head>
<body>

	<ul>
		<% 
@SuppressWarnings("unchecked")
var cursos = (Iterable<CursoDto>)request.getAttribute("cursos"); 
for(var curso: cursos) { %>
		<li><%= curso.nombre() %></li>
		<% } %>
	</ul>
</body>
</html>