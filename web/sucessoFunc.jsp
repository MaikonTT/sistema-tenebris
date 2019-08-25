<%-- 
    Document   : sucessoCli
    Created on : 30/04/2018, 17:08:21
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sucesso</title>
    </head>
    <body>
        <strong><font color="blue">O funcionário foi <%=request.getAttribute("mensagem")%></font></strong>
         <a href="admin_funcionario.jsp">Voltar</a>
    </body>
</html>

