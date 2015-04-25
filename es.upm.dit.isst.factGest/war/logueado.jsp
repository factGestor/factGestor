<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>

<head>
<title>Sistema de Gestión de Facturas</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
    <link rel="stylesheet" type="text/css" href="css/logueado.css" />
<meta charset="utf-8">
<link href="<c:url value='favicon.ico'/>" rel="shortcut icon" type="image/x-icon" />
<script type="text/javascript">
	
	//funciones
	function ocultar(){
		document.getElementById("aux").style.display = 'none';
		//no va
		aux.style.visible = 'hidden';
	}
	
	
</script>
</head>

<body>
    <header><img src="images/taxy.png" alt="Taxy"></header>
	<div id="mensajeInfo">
		<h2><c:out value="${info}"/></h2>
	</div>
	<div class="login">
        <div class="datos">
		<c:choose>
			<c:when test="${u.confirmado == true}">
				<h2 id="bienvenida" style="font-weight: bold;">
					¡Bienvenido,
					<c:out value="${u.name}" />
					!
				</h2>
				<p>Tus datos son los siguientes:</p>
				<ul>
					<li>ID: <c:out value="${u.id}" /></li>
					<li>Nombre: <c:out value="${u.name}" /></li>
					<li>Email: <c:out value="${u.email}" /></li>
					<li>CIF: <c:out value="${u.CIF}" /></li>
					<li>Tarifa: <c:out value="${u.tarifa}" /></li>
					<div id="aux" class="hidden"><c:out value="${u.consultasActuales}" /></div>
					<div id="aux2" class="hidden"><c:out value="${aux}" /></div>
					<c:if test="${u.tarifa == 'Suscripcion'}">
    					<li>Su suscripcion finalizara el <div id="demo2"><c:out value="${u.fechaSuscripcion}" /></div></li>
    					<canvas id="myCanvas2" width="200" height="50" style="border:4px solid #c3c3c3;">
    					</canvas>
						<script>
						//SUSCRIPCIONES
							var c = document.getElementById("myCanvas2");
							var aux2 = document.getElementById("aux2").innerHTML;
							
							console.log(aux2);
							
							var ctx = c.getContext("2d");	
							ctx.fillStyle = "#FF0000";
							ctx.fillRect(0,0,aux2,50);
							//ctx.fillRect(inicioX,inicioY,finX,finY);
						</script>
					</c:if> 
					<c:if test="${u.tarifa != 'Suscripcion'}">
						<li>Le quedan <div id="demo"><c:out value="${u.consultasDisponibles}" /></div> consultas disponibles</li>
						<canvas id="myCanvas" width="200" height="50" style="border:4px solid #c3c3c3;">
    					</canvas>
						<script>
							var c = document.getElementById("myCanvas");
							var cantidad = parseInt(document.getElementById("demo").innerHTML);
							var aux = parseInt(document.getElementById("aux").innerHTML);
							
							console.log("prueba");
							console.log(cantidad);
							console.log(aux);
							var fin = (parseInt(200*((cantidad+1)/aux)));
							var ctx = c.getContext("2d");	
							ctx.fillStyle = "#FF0000";
							ctx.fillRect(0,0,fin,50);
							
						</script>
    					
					</c:if> 
					
				</ul>
				
				</div>
		<aside class="sidebar">
			<h3 class="select">Seleccione una opción</h3>
			<nav role="navigation">
				<ul>
					<li><a href="cambiarPassword.html" class="design-name">Cambiar
							password</a></li>
					<li><a href="cambiarEmail.html" class="design-name">Cambiar
							email</a></li>
					<li><a href="cambiarCondicionesContratacion.html"
						class="design-name">Cambiar condiciones de contratación </li>
					<li><a href="/listarDominios" class="design-name">Dominios asociados su cuenta</a></li>
					<li><a href="/solicitarInforme" class="design-name">Solicitar Informes</a></li>
					<li><a href="/logout">LOGOUT</a></li>
				</ul>
			</nav>
			<br />
		</aside>
		<div id="hidden"></div>
			</c:when>
		<c:otherwise>
<p>Verifique su cuenta mediante el correo de validacion enviado al correo que puso durante el registro.</p>
</c:otherwise>
		</c:choose>
		<hr />
	</div>
</body>
</html>

