<%-- 
    Document   : registro
    Created on : 30 sept 2024, 19:41:28
    Author     : Carlos Eugenio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>mensaje page</title>
    </head>
    <body>
        <h1>REGISTRO!</h1> <br>
        <form method="post" action ="${pageContext.request.contextPath}/credenciales">
            
             Matricula: <br>
            <input type="text" name="matricula" id="matricula" size="9">
            
            Password: <br>
            <input type="text" name="password" id="password" size="9">
            <br>

            <input type="submit" value="Registrarse">
            
        </form>
    </body>
</html>
