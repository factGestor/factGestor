<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>

<head>
<title>Sistema de Gestión de Facturas</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<meta charset="utf-8">
<link href="<c:url value='favicon.ico'/>" rel="shortcut icon" type="image/x-icon" />
</head>

<body>

    <header><img src="images/taxy.png" alt="Taxy"></header>

	<div id="mensajeInfo">
		<h2><c:out value="${info}" /></h2>
	</div>
	<div class="login">
		<c:choose>
			<c:when test="${u.confirmado == true}">
				<h2 style="font-weight: bold;">
					¡Bienvenido,
					<c:out value="${u.name}" />
					!
				</h2>
				<p>Tus datos son los siguientes:</p>
				<ul>
					<li>ID: <c:out value="${u.id}" /></li>
					<li>Nombre: <c:out value="${u.name}" /></li>
					<li>CIF: <c:out value="${u.CIF}" /></li>
					<li>Email: <c:out value="${u.email}" /></li>
				</ul>
				<form id="cambiarPassword" action="cambiarPassword.html" method="get">
					<p class="boton">
						<input type="submit" id="cambiarPassword" name="submit" value="Cambiar password">
					</p>
				</form>
				<form id="cambiarEmail" action="cambiarEmail.html" method="get">
					<p class="boton">
						<input type="submit" id="cambiarEmail" name="submit" value="Cambiar email">
					</p>
				</form>
				<form id="cambiarCuentaBancaria" action="cambiarCuentaBancaria.html" method="get">
					<p class="boton">
						<input type="submit" id="cambiarCuentaBancaria" name="submit" value="Cambiar cuenta bancaria">
					</p>
				</form>
				<form id="cambiarCondicionesContratacion" action="cambiarCondicionesContratacion.html" method="get">
					<p class="boton">
						<input type="submit" id="cambiarCondicionesContratacion" name="submit" value="Cambiar condiciones de contratacion">
					</p>
				</form>
				<form id="listarDominios" action="/listarDominios" method="get">
					<p class="boton">
						<input type="submit" id="listarDominios" name="submit" value="Añadir/eliminar dominios a su cuenta">
					</p>
				</form>
			</c:when>
		<c:otherwise>
Verifique su cuenta mediante el correo de validacion enviado al correo que puso durante el registro.
</c:otherwise>
		</c:choose>
		<hr />
	</div>
</body>
</html>