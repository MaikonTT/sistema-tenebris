<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logar</title>
    </head>
    <body>
        <table style="border-style: double">
            <th><h2>Login ou Senha Inválidos</h2></th>
            <tr>
            <td>
                <a class="page-link" href="login.jsp"><%out.println("Tente outra vez!");%></a>
            </td>
        </tr>
    </table>
</body>
</html>

