<%—index.jsp -->
<%@ page language="java" info="IndexJSP" %>

<html>

<head>
    
    <meta charset="utf-8">
    <link href="<c:url value='favicon.ico'/>" rel="shortcut icon" type="image/x-icon" />
</head>
    
<body>
    
    <%@ include file='index.html'%>
    <div id="mensajeError"><c:out value="${error}"/></div>
        
</body>
        
</html>