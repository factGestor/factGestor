<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="css/style.css" />
<title>Cambiar condiciones de contratacion</title>
<script type="text/javascript">
function paisSeleccionado(formulario){
    /*Ocultamos todos los que puedan quedar visibles de las otras selecciones*/ document.getElementById("mostrarDominio").style.display = 'none';
     
document.getElementById("mostrarFecha").style.display = 'none';
     document.getElementById("mostrarPais").style.display = 'block';
     
 }

function dominioSeleccionado(formulario){
    /*Ocultamos todos los que puedan quedar visibles de las otras selecciones*/ 
    document.getElementById("mostrarPais").style.display = 'none';
    document.getElementById("mostrarFecha").style.display = 'none';
    document.getElementById("mostrarDominio").style.display = 'block';
 }
function fechaSeleccionada(formulario){
    /*Ocultamos todos los que puedan quedar visibles de las otras selecciones*/ 
    document.getElementById("mostrarPais").style.display = 'none';
    document.getElementById("mostrarDominio").style.display = 'none';
     document.getElementById("mostrarFecha").style.display = 'block';
 }
</script>
</head>
<body>
	<header>
		<img src="images/taxy.png" alt="Taxy">
	</header>
	<div class="campos" id="form">

		<form id="formulario" action="/emitirInforme" method="post">
			<p>Seleccione qué campo desea concretar para el informe:</p>
			<br>
			<p class="radios">
				<input type="radio" name="tarifa" id="pais" value="Pais" checked
					onclick="paisSeleccionado(this);"><label for="pais">País
					de consulta</label> <input type="radio" name="tarifa" id="fechaCon"
					value="FechaCon" onclick="fechaSeleccionada(this);"><label
					for="fechaCon">Fecha de consulta</label> <input type="radio"
					name="tarifa" id="dominio" value="Dominio"><label
					for="dominio" onclick="dominioSeleccionado(this);">Dominio
					de consulta</label>

			</p>

			<br> <br> <br>

			<div id="mostrarPais">
				<p>Elija el país:</p>
				<select name="pais">
					<% 	String[] paises ={"España","Alemania","Austria","Bélgica","Croacia","Dinamarca","Eslovaquia","Eslovenia","Estonia","Finlandia","Francia","Grecia","Hungría","Irlanda","Italia","Letonia","Lituania","Luxemburgo","Malta","Países Bajos","Polonia","Portugal","Reino Unido","República Checa","Rumania","Suecia"};
					String[] paisesValue ={"spain","germany","austria","belgium","croatia","denmark","slovakia","slovenia","estonia","finland","france","greece","hungary","ireland","italy","latvia","lithuania","luxembourg","malta","netherlands","poland","portugal","unitedkingdom","czechrepublic","romania","sweeden"};
for (int i=0;i<paises.length;i++) { 
					out.println("<option value=&quot"+paisesValue[i]+"&quot style=&quot width: 100px&quot>"+paises[i]+"</option>");
					} %>
				</select>
			</div>
			<div class="hidden" id="mostrarFecha">
				<p>Elija Fecha de la factura:</p>
				<select name="dia">
					<% 	for (int i=1;i<32;i++) { 
					out.println("<option value=&quot"+i+"&quot >"+i+"</option>");
					} %>
				</select> <select name="mes" id="mes">
					<% 	String[] meses ={"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
for (int i=0;i<12;i++) { 
					out.println("<option value=&quot"+i+1+"&quot >"+meses[i]+"</option>");
					} %>
				</select> <select name="ano">
					<% 
for (int i=1990;i<2016;i++) { 
					out.println("<option value=&quot"+i+"&quot style=&quotwidth: 100px&quot>"+i+"</option>");
					} %>
				</select>
			</div>
			<div class="hidden" id="mostrarDominio">
				<p>Elija el dominio del que quiere ver informacion:</p>
				<select name="dominio">
				<c:forEach items="${dominios}" var="dominio">
				<option><c:out value="${dominio.domain}" /></option>
				</c:forEach>
				</select>
			</div>
			
			<br> <br> <br>
			
			<p class="boton">

				<input type="submit" id="solicitarInforme" name="submit"
					value="Solicitar informe">

			</p>
		</form>

		<a href="https://www.fact-gest.appspot.com/">
			<button class="boton">Volver</button>
		</a>
	</div>
</body>
</html>
