<%-- 
    Document   : login
    Created on : 30 sept 2024, 19:41:06
    Author     : Carlos Eugenio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/login_servlet" method="post">
            Matricula: <br>
            <input type="text" name="matricula" id="matricula" size="9">
            <br> <br>
            Password: <br>
            <input type="text" name="password" id="password" size="9">
            <br> <br>
            
            <input type="checkbox" name="recordar" id="recordar">
                Recordar mis datos
                </input> <br>
            <input type="submit" value="Iniciar Sesión">
        </form>
    </body>
</html>
