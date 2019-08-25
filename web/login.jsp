<!DOCTYPE html>
<html lang="en">
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
        <div class="img-logo"><a href="home.jsp"><h1><img src="img/logo.png" alt="Projeto Tenebris"></h1></a></div>
        <div class="container-log col-12">
            <div class="card card-login mx-auto mt-5">
                <div class="card-header">Login</div>
                <div class="card-body class">
                    <form action="ControleLogin" method="post">
                        <div class="form-group">
                            <label for="Login1">Usuário</label>
                            <input class="form-control" id="Login1" type="text" name="login" placeholder="Digite o usuário">
                        </div>
                        <div class="form-group">
                            <label for="Senha1">Senha</label>
                            <input class="form-control" id="Senha1" type="password" name="senha" placeholder="Digite a senha">
                        </div>
                        <input type="submit" class="btn btn-primary btn-block" name="acao" value="Logar">
                    </form>
                    <div class="text-center">
                        <a class="d-block small mt-3" href="cliente_cadastrar.jsp">Criar conta</a>
                    </div>
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