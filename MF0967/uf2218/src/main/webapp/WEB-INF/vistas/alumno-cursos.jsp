<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp" %>
<main>
	<ul>
		<c:forEach items="${cursos}" var="curso">
			<li>${curso.nombre()}</li>
		</c:forEach>
	</ul>
</main>
<%@ include file="/WEB-INF/vistas/includes/pie.jsp" %>
