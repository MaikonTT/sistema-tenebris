<!DOCTYPE html>
<html lang="en">
    <%@page import="java.util.ArrayList" %>
    <%@page import="model.Cupom"%>
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
        <link href="css/meu.css" rel="stylesheet" type="text/css"/>
        <link href="css/style-on-blank.css" rel="stylesheet">
    </head>
    <body class="fixed-nav sticky-footer bg-dark" id="page-top">
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
            <a class="navbar-brand" href="index.jsp">Inicio</a>
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

                    <h1>Administração de Cupons</h1>
                    <br/>
                    <hr/>

                    <%
                        // Recupera os produtos.
                        ArrayList<Cupom> listaCupoms = (ArrayList<Cupom>) request.getAttribute("listaCupoms");                    
                        if (listaCupoms == null) {
                        request.getRequestDispatcher("/ControleCupom?acao=Listar").forward(request,response);
                        }
                    %>

                    <div id="tabela_cup">
                        <form action="ControleCupom" name="acao" value="Listar">
                            <table border="1" class="col-md-12">
                                <thead>
                                    <tr>
                                        <th>Id</th>
                                        <th>Descrição</th>
                                        <th>Código</th>
                                        <th>Desconto</th>
                                        <th>Estado</th>

                                    </tr>
                                </thead>
                                <tbody>
                                    <%          
                                        for (Cupom c : listaCupoms) { 
                                    %>
                                    <tr>
                                        <td><%= c.getId() %></td>
                                        <td><%= c.getDescri() %></td>
                                        <td><%= c.getCodigo() %></td>
                                        <td><%= c.getDesconto() %></td>
                                        <td><%= c.getEstado() %></td>
                                    </tr>
                                    <% }
                                    %>
                                </tbody>
                            </table>
                        </form>
                    </div>
                </div>
                <!-- fim da lista -->

                <hr/>
                <div class="form-group col-3">                    
                    <form class="form-group" action="ControleCupom" method="POST">
                        <h4>Pesquisar</h4>
                        <br/>
                        ID:&nbsp<input class="dados-box" type="text" name="idpesq"><input type="submit" name="acao" value="Pesquisar">
                    </form>
                    <hr/>
                    <% 
                        Cupom cupom = (Cupom) request.getAttribute("cupom");
                        int existe = 0;
                        if(cupom != null){
                        existe = 1;
                        }
                    %>
                    <form class="form-group" action="ControleCupom" method="POST">
                        <h4>Alterar</h4>
                        <hr/>
                        <label for="label1">ID:&nbsp</label><input class="id-box" type="text" <% if(existe == 1){ %> value="<%= cupom.getId() %>" <% } %> name="id" readonly>
                        <input type="submit" name="acao" value="Excluir">
                        <br><label for="label1">Descrição:&nbsp</label><input class="dados-box" type="text" <% if(existe == 1){ %> value="<%= cupom.getDescri() %>" <% } %> name="novadescri">
                        <br><label for="label1">Codigo:&nbsp</label><input class="dados-box" type="text" <% if(existe == 1){ %> value="<%= cupom.getCodigo() %>" <% } %> name="novocodigo">
                        <br><label for="label1">Desconto:&nbsp</label><input class="dados-box" type="text" <% if(existe == 1){ %> value="<%= cupom.getDesconto() %>" <% } %> name="novodesconto">
                        <br><label for="label1">Estado:&nbsp</label><input class="dados-box" type="text" <% if(existe == 1){ %> value="<%= cupom.getEstado() %>" <% } %> name="novoestado">
                        <br><input type="submit" name="acao" value="Alterar">                        
                    </form>
                </div>
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