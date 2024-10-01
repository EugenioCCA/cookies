<%-- 
    Document   : mensaje
    Created on : 30 sept 2024, 19:41:15
    Author     : Carlos Eugenio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mensaje</title>
    </head>
    <body>
        <h2>Operación</h2>
        <p> <%= request.getAttribute("mensaje") %> </p>
        <a href="${pageContext.request.contextPath}/JSP/login.jsp">Iniciar sesion</a>
    </body>
</html>
