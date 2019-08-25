<!DOCTYPE html>
<html lang="en">
    <%@page import="java.util.ArrayList"%>
    <%@page import="model.Funcionario"%>
    <%@page import="model.Gerente"%>
    <%@page import="javax.servlet.http.HttpSession"%>
    <head>
        <% HttpSession sessao = request.getSession();
           Gerente gerente = (Gerente) sessao.getAttribute("gerente"); %>    
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
    <body class="fixed-nav sticky-footer bg-dark" id="page-top">
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
            <a class="navbar-brand" href="admin.jsp">Inicio</a>
            <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav navbar-sidenav" id="exampleAccordion">

                    <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Components">
                        <a class="nav-link" href="admin_funcionario.jsp">
                            <i class="fa fa-fw fa-wrench"></i>
                            <span class="nav-link-text">Administração de Funcionários</span>
                        </a>
                    </li>

                    <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Link">
                        <a class="nav-link" href="admin_cliente.jsp">
                            <i class="fa fa-fw fa-wrench"></i>
                            <span class="nav-link-text">Administração de Clientes</span>
                        </a>
                    </li>

                    <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Link" >
                        <a class="nav-link" href="admin_cupom.jsp">
                            <i class="fa fa-fw fa-wrench"></i>
                            <span class="nav-link-text">Administração de Cupons</span>
                        </a>
                    </li>

                </ul>
                <ul class="navbar-nav sidenav-toggler">
                    <li class="nav-item">
                        <a class="nav-link text-center" id="sidenavToggler">
                            <i class="fa fa-fw fa-angle-left"></i>
                        </a>
                    </li>
                </ul>

                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" data-toggle="modal" data-target="#exampleModal">
                            <%= gerente.getLogin() + "," %><i class="fa fa-fw fa-sign-out"></i>Logout</a>
                    </li>
                </ul>
            </div>
        </nav>

        <!-- Corpo da página-->
        <div class="text-center bg-index">
            <div class="content-wrapper">
                <div class="container-fluid">

                    <h1>Administração de Funcionários</h1>
                    <br/>
                    <hr/>
                </div>

                <h3>Lista de Funcionários</h3>
                <hr/>
                <%
                    ArrayList<Funcionario> listaFuncionario = (ArrayList<Funcionario>) request.getAttribute("listaFuncionarios");
                if (listaFuncionario == null) {
                request.getRequestDispatcher("/ControleFuncionario?acao=Listar").forward(request,response);
                }
                %>
                <form action="ControleFuncionario" name="acao">
                    <table border="2" class="col-md-12">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nome</th>
                                <th>CPF</th>
                                <th>Telefone</th>
                                <th>Endereço</th>
                                <th>Login</th>
                                <th>Senha</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% 
                                for(Funcionario f : listaFuncionario){
                            %>
                            <tr>
                                <td><%= f.getId() %></td>
                                <td><%= f.getNome() %></td>
                                <td><%= f.getCpf() %></td>
                                <td><%= f.getTelefone() %></td>                    
                                <td><%= f.getEndereco() %></td>
                                <td><%= f.getLogin() %></td>
                                <td><%= f.getSenha() %></td>
                            </tr>
                            <%
                                }
                            %>
                        </tbody>
                    </table>
                    <!-- fim da lista -->
                    <hr/>
                    <div class="form-group col-3">                    
                        <form class="form-group" action="ControleFuncionario" method="POST">
                            <h4>Pesquisar</h4>
                            <br/>
                            ID:&nbsp<input class="dados-box" type="text" name="idpesq"><input type="submit" name="acao" value="Pesquisar">
                        </form>
                        <hr/>
                        <% 
                            Funcionario funcionario = (Funcionario) request.getAttribute("funcionario");
                            int existe = 0;
                            if(funcionario != null){
                            existe = 1;
                            }
                        %>
                        <form class="form-group" action="ControleFuncionario" method="POST">
                            <h4>Alterar</h4>
                            <hr/>
                            <label for="label1">ID:&nbsp</label><input class="id-box" type="text" <% if(existe == 1){ %> value="<%= funcionario.getId() %>" <% } %> name="id" readonly>
                            <input type="submit" name="acao" value="Excluir">
                            <br><label for="label1">Nome:&nbsp</label><input class="dados-box" type="text" <% if(existe == 1){ %> value="<%= funcionario.getNome() %>" <% } %> name="novanome">
                            <br><label for="label1">Cpf:&nbsp</label><input class="dados-box" type="text" <% if(existe == 1){ %> value="<%= funcionario.getCpf() %>" <% } %> name="novocpf">
                            <br><label for="label1">Telefone:&nbsp</label><input class="dados-box" type="text" <% if(existe == 1){ %> value="<%= funcionario.getTelefone() %>" <% } %> name="novotelefone">
                            <br><label for="label1">Endereço:&nbsp</label><input class="dados-box" type="text" <% if(existe == 1){ %> value="<%= funcionario.getEndereco() %>" <% } %> name="novoemail">
                            <br><input type="submit" name="acao" value="Alterar">                        
                        </form>
                    </div>
                </form>  
            </div>




            <!-- /.container-fluid-->
            <!-- /.content-wrapper-->
            <footer class="sticky-footer">
                <div class="container">
                    <div class="text-center">
                        <small>Direitos autorais © Tenebris 2018</small>
                    </div>
                </div>
            </footer>
            <!-- Scroll to Top Button-->
            <a class="scroll-to-top rounded" href="#page-top">
                <i class="fa fa-angle-up"></i>
            </a>
            <!-- Logout Modal-->
            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Deseja sair?</h5>
                            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span>
                            </button>
                        </div>
                        <div class="modal-body">Selecione "Sair" se você está pronto para finalizar sua sessão.</div>
                        <div class="modal-footer">
                            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
                            <a class="btn btn-primary" href="login.jsp">Sair</a>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Bootstrap core JavaScript-->
            <script src="vendor/jquery/jquery.min.js"></script>
            <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
            <!-- Core plugin JavaScript-->
            <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
            <!-- Custom scripts for all pages-->
            <script src="js/sb-admin.min.js"></script>
        </div>
    </body>

</html>