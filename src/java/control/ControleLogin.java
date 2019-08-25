package control;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import model.Funcionario;
import model.Gerente;
import dao.ClienteDAO;
import dao.FuncionarioDAO;
import dao.GerenteDAO;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Takeshi
 */
@WebServlet(name = "ControleLogin", urlPatterns = {"/ControleLogin"})
public class ControleLogin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {

            String acao1 = request.getParameter("acao");
            if (acao1.equals("Logar")) {

                HttpSession sessao = request.getSession();
                
                String login = request.getParameter("login");
                String senha = request.getParameter("senha");

                Cliente cliente = new Cliente();
                Funcionario funcionario = new Funcionario();
                Gerente gerente = new Gerente();

                ClienteDAO cDAO = new ClienteDAO();
                FuncionarioDAO fDAO = new FuncionarioDAO();
                GerenteDAO gDAO = new GerenteDAO();

                //intanciamentos
                cliente.setLogin(login);
                cliente.setSenha(senha);

                funcionario.setLogin(login);
                funcionario.setSenha(senha);

                gerente.setLogin(login);
                gerente.setSenha(senha);

                //busca no banco / validação
                cDAO.buscarUsuario(cliente);
                fDAO.buscarUsuario(funcionario);
                gDAO.buscarUsuario(gerente);

                if (gerente.getNivel() != null) {

                    sessao.setAttribute("gerente", gerente);
                    RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
                    rd.forward(request, response);
                } else if (funcionario.getNivel() != null) {

                    sessao.setAttribute("funcionario", funcionario);

                    RequestDispatcher rd = request.getRequestDispatcher("func_index.jsp");
                    rd.forward(request, response);
                } else if (cliente.getNivel() != null) {
                    
                    sessao.setAttribute("cliente", cliente);
                    RequestDispatcher rd = request.getRequestDispatcher("cliente_index.jsp");
                    rd.forward(request, response);
                }
            }
        } catch (Exception e) {
            request.setAttribute("erro", e);
            RequestDispatcher rd = request.getRequestDispatcher("erro_autenticacao.jsp");
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
