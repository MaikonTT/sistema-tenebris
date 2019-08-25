/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.FuncionarioDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Funcionario;

/**
 *
 * @author Takeshi
 */
@WebServlet(name = "ControleFuncionario", urlPatterns = {"/ControleFuncionario"})
public class ControleFuncionario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try{
            //recupera param acao
            String acao = request.getParameter("acao");
            if (acao.equals("Registrar")) {
                String nome = request.getParameter("nome");
                String cpf  = request.getParameter("cpf");                
                String telefone = request.getParameter("telefone");
                String endereco = request.getParameter("endereco");
                String login = request.getParameter("login");
                String senha = request.getParameter("senha");

                Funcionario funcionario = new Funcionario();
                funcionario.setNivel("2");
                funcionario.setNome(nome);
                funcionario.setCpf(cpf);
                funcionario.setTelefone(telefone);
                funcionario.setEndereco(endereco);
                funcionario.setLogin(login);
                funcionario.setSenha(senha);
                
                FuncionarioDAO fDAO = new FuncionarioDAO();
                fDAO.cadastrar(funcionario);
                //redireciono
                request.setAttribute("mensagem", " cadastrado com sucesso!");
                RequestDispatcher rd = request.getRequestDispatcher("admin_funcionario.jsp");
                rd.forward(request, response);

            } else if (acao.equals("Listar")) {
                //cria objeto
                FuncionarioDAO fDAO = new FuncionarioDAO();
                //executa o método listar
                ArrayList<Funcionario> funcionarios = fDAO.listar();
                //add a lista no obJ eto request   
                request.setAttribute("listaFuncionarios", funcionarios);
                //encaminha o request para o JSP
                RequestDispatcher rd = request.getRequestDispatcher("admin_listar_funcionario.jsp");
                rd.forward(request, response);

            } else if (acao.equals("Excluir")) {

                int id = Integer.parseInt(request.getParameter("id"));

                Funcionario funcionario = new Funcionario();
                funcionario.setId(id);

                FuncionarioDAO fDAO = new FuncionarioDAO();

                fDAO.excluir(funcionario);
                //redireciono
                request.setAttribute("mensagem", " excluido com sucesso!");
                RequestDispatcher rd = request.getRequestDispatcher("sucessoCli.jsp");
                rd.forward(request, response);

            } else if (acao.equals("Alterar")) {

                int id = Integer.parseInt(request.getParameter("id"));

                Funcionario funcionario = new Funcionario();
                funcionario.setId(id);

                FuncionarioDAO fDAO = new FuncionarioDAO();

                fDAO.buscar(funcionario);
                //redireciono
                request.setAttribute("funcionario", funcionario);

                RequestDispatcher rd = request.getRequestDispatcher("editarperfil_cliente.jsp");
                rd.forward(request, response);

            } else if (acao.equals("Confirmar")) {

                String nome = request.getParameter("nome");
                String cpf  = request.getParameter("cpf");
                String telefone = request.getParameter("telefone");
                String endereco = request.getParameter("endereco");
                String login = request.getParameter("usuario");
                String senha = request.getParameter("senha");
                
                int id = Integer.parseInt(request.getParameter("Id"));

                Funcionario funcionario = new Funcionario();
                funcionario.setNome(nome);
                funcionario.setCpf(cpf);
                funcionario.setTelefone(telefone);
                funcionario.setEndereco(endereco);
                funcionario.setLogin(login);
                funcionario.setSenha(senha);

                funcionario.setId(id);

                FuncionarioDAO fDAO = new FuncionarioDAO();
                fDAO.alterar(funcionario);
                //redireciono
                request.setAttribute("mensagem", " alterado com sucesso!");
                RequestDispatcher rd = request.getRequestDispatcher("sucessoCli.jsp");
                rd.forward(request, response);

            }else if (acao.equals("interfaceCADCLI")) {
                response.sendRedirect("index.jsp");
            }
            
            
        } catch(Exception e){
            
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
