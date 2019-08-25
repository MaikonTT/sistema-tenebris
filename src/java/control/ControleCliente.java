/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import dao.ClienteDAO;
import dao.FuncionarioDAO;
import dao.GerenteDAO;
import javax.servlet.http.HttpSession;
import model.Funcionario;
import model.Gerente;

/**
 *
 * @author Takeshi
 */
@WebServlet(name = "ControleCliente", urlPatterns = {"/ControleCliente"})
public class ControleCliente extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            //recupera param acao
            String acao = request.getParameter("acao");
            if (acao.equals("Registrar")) {

                HttpSession sessao = request.getSession();

                String nome = request.getParameter("nome");
                String cpf = request.getParameter("cpf");
                String telefone = request.getParameter("telefone");
                String email = request.getParameter("email");
                String login = request.getParameter("login");
                String senha = request.getParameter("senha");

                //dados pessoais
                Cliente cli = new Cliente();
                cli.setNome(nome);
                cli.setCpf(cpf);
                cli.setTelefone(telefone);
                cli.setEmail(email);
                //dados de usuario
                cli.setNivel("3");
                cli.setLogin(login);
                cli.setSenha(senha);

                ClienteDAO cdao = new ClienteDAO();
                cdao.cadastrar(cli);

                Cliente cliente = new Cliente();
                Funcionario funcionario = new Funcionario();
                Gerente gerente = new Gerente();

                ClienteDAO cDAO = new ClienteDAO();
                FuncionarioDAO fDAO = new FuncionarioDAO();
                GerenteDAO gDAO = new GerenteDAO();

                //busca no banco / validação
                cDAO.buscarUsuario(cliente);
                fDAO.buscarUsuario(funcionario);
                gDAO.buscarUsuario(gerente);

                if (gerente.getNivel() != null) {
                    //redireciono                
                    sessao.setAttribute("cliente", cli);
                    RequestDispatcher rd = request.getRequestDispatcher("admin_cliente.jsp");
                    rd.forward(request, response);
                } else if (funcionario.getNivel() != null) {
                    //redireciono                
                    sessao.setAttribute("cliente", cli);
                    RequestDispatcher rd = request.getRequestDispatcher("func_index.jsp");
                    rd.forward(request, response);
                } else if (cliente.getNivel() != null) {
                    //redireciono               
                    sessao.setAttribute("cliente", cli);
                    RequestDispatcher rd = request.getRequestDispatcher("cliente_index.jsp");
                    rd.forward(request, response);
                }
            } else if (acao.equals("Listar")) {
                //cria objeto
                ClienteDAO cDAO = new ClienteDAO();
                //executa o método listar
                ArrayList<Cliente> clientes = cDAO.listar();
                //add a lista no objeto request  
                request.setAttribute("listaClientes", clientes);
                //encaminha o request para o JSP
                RequestDispatcher rd = request.getRequestDispatcher("admin_listar_cliente.jsp");
                rd.forward(request, response);

            } else if (acao.equals("Listar2")) {
                //cria objeto
                ClienteDAO cDAO = new ClienteDAO();
                //executa o método listar
                ArrayList<Cliente> clientes = cDAO.listar();
                //add a lista no obJ eto request   
                request.setAttribute("listaClientes", clientes);
                //encaminha o request para o JSP
                RequestDispatcher rd = request.getRequestDispatcher("func_editarperfil_cliente.jsp");
                rd.forward(request, response);

            } else if (acao.equals("Excluir")) {

                int id = Integer.parseInt(request.getParameter("id"));

                Cliente cliente = new Cliente();
                cliente.setId(id);

                ClienteDAO cdao = new ClienteDAO();

                cdao.excluir(cliente);
                //redireciono
                RequestDispatcher rd = request.getRequestDispatcher("admin_listar_cliente.jsp");
                rd.forward(request, response);

            } else if (acao.equals("Confirmar")) {

                int id = Integer.parseInt(request.getParameter("id"));

                Cliente cli = new Cliente();
                
                cli.setId(id);

                String telefone = request.getParameter("novotelefone");
                String email = request.getParameter("novoemail");
                String senha = request.getParameter("novasenha");

                if (telefone.equals("")) {
                } else {
                    cli.setTelefone(telefone);
                }
                if (email.equals("")) {
                } else {
                    cli.setEmail(email);
                }
                if (senha.equals("")) {
                } else {
                    cli.setSenha(senha);
                }

                ClienteDAO cdao = new ClienteDAO();
                cdao.alterar(cli);
                //redireciono                
                request.setAttribute("cliente", cli);
                RequestDispatcher rd = request.getRequestDispatcher("cliente_index.jsp");
                rd.forward(request, response);

            } else if (acao.equals("Confirmar ")) {
                Cliente cliente = new Cliente();

                String nome = request.getParameter("novonome");
                String cpf = request.getParameter("novocpf");
                String telefone = request.getParameter("novotelefone");
                String email = request.getParameter("novoemail");
                String senha = request.getParameter("novasenha");

                if (nome.equals("")) {
                } else {
                    cliente.setNome(nome);
                }
                if (cpf.equals("")) {
                } else {
                    cliente.setCpf(cpf);
                }
                if (telefone.equals("")) {
                } else {
                    cliente.setTelefone(telefone);
                }
                if (email.equals("")) {
                } else {
                    cliente.setEmail(email);
                }
                if (senha.equals("")) {
                } else {
                    cliente.setSenha(senha);
                }

                ClienteDAO cdao = new ClienteDAO();
                cdao.alterar(cliente);
                //redireciono                
                request.setAttribute("cliente", cliente);
                RequestDispatcher rd = request.getRequestDispatcher("func_editarperfil_cliente.jsp");
                rd.forward(request, response);

            } else if (acao.equals(" Confirmar ")) {
                Cliente cliente = new Cliente();
                
                int id = Integer.parseInt(request.getParameter("id"));
                String nome = request.getParameter("novonome");
                String cpf = request.getParameter("novocpf");
                String telefone = request.getParameter("novotelefone");
                String email = request.getParameter("novoemail");
                String login = request.getParameter("novologin");
                String senha = request.getParameter("novasenha");

                cliente.setId(id);
                if (nome.equals("")) {
                } else {
                    cliente.setNome(nome);
                }
                if (cpf.equals("")) {
                } else {
                    cliente.setCpf(cpf);
                }
                if (telefone.equals("")) {
                } else {
                    cliente.setTelefone(telefone);
                }
                if (email.equals("")) {
                } else {
                    cliente.setEmail(email);
                }
                if (login.equals("")) {
                } else {
                    cliente.setLogin(login);
                }
                if (senha.equals("")) {
                } else {
                    cliente.setSenha(senha);
                }

                ClienteDAO cdao = new ClienteDAO();
                cdao.alterarGer(cliente);
                //redireciono                
                request.setAttribute("cliente", cliente);
                RequestDispatcher rd = request.getRequestDispatcher("admin_listar_cliente.jsp");
                rd.forward(request, response);

            } else if (acao.equals(" Pesquisar ")) {

                //puxa parametro
                int idpesq = Integer.parseInt(request.getParameter("idpesq"));

                //intanciando classe e dao
                Cliente cliente = new Cliente();
                ClienteDAO cDAO = new ClienteDAO();

                cliente.setId(idpesq);
                cDAO.buscar(cliente);

                request.setAttribute("cliente", cliente);
                //encaminha o request para o JSP
                RequestDispatcher rd = request.getRequestDispatcher("admin_listar_cliente.jsp");
                rd.forward(request, response);

            } else if (acao.equals("Pesquisar")) {

                //puxa parametro
                int idpesq = Integer.parseInt(request.getParameter("idpesq"));

                //intanciando classe e dao
                Cliente cliente = new Cliente();
                ClienteDAO cDAO = new ClienteDAO();

                cliente.setId(idpesq);
                cDAO.buscar(cliente);

                request.setAttribute("cliente", cliente);
                //encaminha o request para o JSP
                RequestDispatcher rd = request.getRequestDispatcher("admin_listar_cliente.jsp");
                rd.forward(request, response);

            } else if (acao.equals("Cancelar")) {
                response.sendRedirect("cliente_editarperfil.jsp");
            }
        } catch (Exception e) {
            request.setAttribute("erro", e);
            RequestDispatcher rd = request.getRequestDispatcher("erro.jsp");
            rd.forward(request, response);

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
