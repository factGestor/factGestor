<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page isELIgnored="false"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<link href="<c:url value='favicon.ico'/>" rel="shortcut icon"
	type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<title>Informe</title>
</head>

<body>
    <header><img src="images/taxy.png" alt="Taxy"></header>
	<div class="informe">
		<h2 style="font-weight: bold;">¡Operacion realizada con exito!</h2>
		<p>
			Se ha completado su solicitud. Este es su informe:
		<p>
		
		
		<table class="tablaInforme">
			<tr>
				<th>Factura</th>
				<th>ID Usuario</th>
				<th>Dominio</th>
				<th>IVA Pagado</th>
				<th>ID de País</th>
				<th>Número Factura</th>
				<th>Tipo de IVA</th>
				<th>Fecha</th>
			</tr>
			<c:forEach items="${facturas}" var="factura">
				<tr>
					<td><c:out value="${factura.id}" /></td>
					<td><c:out value="${factura.userid}" /></td>
					<td><c:out value="${factura.domain}" /></td>
					<td><c:out value="${factura.ivapagado}" /></td>
					<td><c:out value="${factura.paisId}" /></td>
					<td><c:out value="${factura.numeroFactura}" /></td>
					<td><c:out value="${factura.tipoIVA}" /></td>
					<td><c:out value="${factura.fecha}" /></td>
					</td>
				</tr>
			</c:forEach>

		</table>

		<br> <br> <br>
		<button class="boton" onclick="window.print();">Imprimir factura</button>
		<br> <br> <a href="https://www.fact-gest.appspot.com/">
			<button class="boton">Volver</button>
		</a>
	</div>
</body>
</html>