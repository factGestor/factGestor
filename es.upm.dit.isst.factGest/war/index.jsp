<%—index.jsp -->
<%@ page language="java" info="IndexJSP" %>

<html>

<body>
    <%@ include file='index.html'%>
    <% var mensaje=document.getElementById("mensajeError");
    document.innerHTML(mensaje)=<c:out value="${error}"/>;
</body>
        
</html>