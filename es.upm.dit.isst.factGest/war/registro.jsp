<%—registro.jsp -->
<%@ page language="java" info="RegistroJSP" %>

<html>

<head>
    <meta charset="utf-8">
    <link href="<c:url value='favicon.ico'/>" rel="shortcut icon" type="image/x-icon" />
    
</head>

<body>
    
    <%@ include file='registro.html'%>
    <div id="mensajeError"><c:out value="${error}"/></div>
        
</body>
        
</html>