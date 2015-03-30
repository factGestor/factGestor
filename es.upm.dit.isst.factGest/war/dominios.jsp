<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="utf-8">
<link rel="stylesheet" type="text/css" href="css/style.css" />
<title>Gestion de dominios</title>
</head>
<body>

	<header>
		<img src="images/taxy.png" alt="Taxy">
	</header>

	<div id="mensajeInfo">
		<h2>
			<c:out value="${info}" />
		</h2>
	</div>
	<div id="formulario" class="campos">
		<h2>
			Los dominios del usuario
			<c:out value="${u.name}" />
			son los siguientes:
		</h2>

		<p>
			<!-- TABLA DE DOMINIOS -->
		<table>
			<tr>
				<th>Dominio</th>
				<th>¿Desea borrar este dominio?</th>
			</tr>
			<c:forEach items="${dominios}" var="dominio">
				<tr>
					<td><c:out value="${dominio.domain}" /></td>
					<td><a class="borrarDominio"
						href="<c:url value="/borrarDominio?id=${dominio.id}"/>">Borrar
							dominio</a></td>
					<!--  
				<form id="borrarDominio" action="<c:url value="/borrarDominio?id=${dominio.id}"/>" method="post">
					<p class="boton">
						<input type="submit" id="borrarDominio" name="submit" value="Borrar dominio">
					</p>
				</form>
			-->
					</td>
				</tr>
			</c:forEach>

		</table>

		<!-- AÑADIR NUEVO DOMINIO -->

		<form id="cambiarPassword" action="/agregarDominio" method="post">
			<p>
				<label for="agregarDominio">Nuevo Dominio: </label> <br>
				<input name="agregarDominio" type="text" id="agregarDominio"
					class="text" placeholder="Ej: www.dominio.es"
					style="text-align: center" />
			</p>
			<p class="boton">
				<input type="submit" id="agregarDominio" name="submit"
					value="agregar dominio">
			</p>
		</form>
	</div>
</body>
</html>