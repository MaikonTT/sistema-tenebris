<!DOCTYPE html>
<html lang="en">
    <%@page import="model.Cliente"%>
    <%@page import="javax.servlet.http.HttpSession"%>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Tenebris</title>
        <!-- Bootstrap core CSS-->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom fonts for this template-->
        <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <!-- Custom styles for this template-->
        <link href="css/sb-admin.css" rel="stylesheet">
        <link href="css/style-on-blank.css" rel="stylesheet">
    </head>
    <body class="bg-dark2">
        <div class="container">
            <div class="card card-register mx-auto mt-5">
                <div class="card-header">Cadastrar uma Conta</div>
                <div class="card-body">
                    <form action="ControleCliente" method="post">
                        <div class="form-group">
                            <div class="form-row">
                                <div class="col-md-8">
                                    <label for="Name">Nome Completo</label>
                                    <input class="form-control" id="Name" name="nome" type="text" placeholder="Digite nome completo" required />
                                    <br />
                                    <div class="form-group">
                                        <label for="Cpf">CPF<font size= 2px>(sem pontos e hífen)</font></label>
                                        <input class="form-control" id="Cpf" name="cpf" type="text" placeholder="___.___.___-__" title="Digite o CPF sem pontos e sem hífen" required />
                                    </div>
                                    <div class="form-group">
                                        <label for="Telefone">Telefone</label>
                                        <input class="form-control" id="Telefone" name="telefone" type="text" placeholder="(__) ____-_____"  required />
                                    </div> 
                                    <div class="form-group">
                                        <label for="Email">Email</label>
                                        <input class="form-control" id="Email" name="email" type="email" placeholder="exemplo@exemplo.com.br"  required />
                                    </div>
                                    <br/>
                                    <hr/>
                                    <div class="form-group">
                                        <label for="Login">Nome de Usuário</label>
                                        <input class="form-control" id="Login" name="login" type="text" placeholder="Digite o nome de usuário" required />
                                    </div>
                                    <div class="form-group">
                                        <label for="Senha">Senha</label>
                                        <input class="form-control" id="Senha" name="senha" type="password" placeholder="Digite a senha" required />
                                    </div>
                                </div>
                            </div>
                            <br/>
                            <input type="submit" class="btn btn-primary btn-block" name="acao" value="Registrar">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Bootstrap core JavaScript-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <!-- Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
    </body>
</html>
