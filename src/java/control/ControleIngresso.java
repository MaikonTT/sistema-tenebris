/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.CupomDAO;
import model.Ingresso;
import dao.IngressoDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Cliente;
import model.Cupom;
import model.CodigoIngresso;

/**
 *
 * @author Takeshi
 */
@WebServlet(name = "ControleIngresso", urlPatterns = {"/ControleIngresso"})
public class ControleIngresso extends HttpServlet {

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
        try {

            HttpSession sessao = request.getSession();            
            Cliente cliente = (Cliente) sessao.getAttribute("cliente");

            String acao = request.getParameter("acao");
            
            if (acao.equals("Listar")) {         
                String cpf = cliente.getCpf();
                Ingresso ingresso = new Ingresso();
                ingresso.setCpf(cpf);
                IngressoDAO iDAO = new IngressoDAO();
                //monta uma lista para exibição de ingresso na página de listagem
                ArrayList<Ingresso> ingressos = iDAO.listar(ingresso);
                //armazena os produto na requisição
                request.setAttribute("listaIngressos", ingressos);
                //encaminha o request para o JSP
                RequestDispatcher rd = request.getRequestDispatcher("cliente_ingresso.jsp");
                rd.forward(request, response);
            }else if(acao.equals("Comprar")){
                String data = request.getParameter("data");
                                
                Ingresso ingresso = new Ingresso();
                ingresso.setData(data);                
                
            } else if (acao.equals("Finalizar Compra")) {
                
                int q = Integer.parseInt(request.getParameter("quant"));

                while (q > 0) {
                    //chamada de método para criação de código random
                    CodigoIngresso codin = new CodigoIngresso();
                    String codigo = codin.gerarCodigo();
                    //fim da chamada de método                
                    
                    String cod = request.getParameter("codcup");
                    Cupom cupom = new Cupom();
                    cupom.setCodigo(cod);
                    CupomDAO cDAO = new CupomDAO();
                    cDAO.buscar2(cupom);
                    
                    //altera estado do cupom
                    if(cupom.getCodigo().equals(cod)){
                        cupom.setEstado("utilizado");
                        cDAO.alterar(cupom);
                    }

                    String data = request.getParameter("data");
                    double v_ingresso = 30.00;
                    String cpf = cliente.getCpf();

                    Ingresso ingresso = new Ingresso();
                    ingresso.setCodigo(codigo);
                    ingresso.setData(data);
                    ingresso.setValor(v_ingresso - (v_ingresso*(cupom.getDesconto()/100)));
                    
                    ingresso.setCpf(cpf);

                    //registra a venda do ingresso
                    IngressoDAO iDAO = new IngressoDAO();
                    iDAO.cadastrar(ingresso);
                    q--;
                }
                request.getRequestDispatcher("/cliente_comprar_ingresso.jsp").forward(request, response);

            }

        } catch (Exception erro) {
            request.setAttribute("erro", erro);
            request.getRequestDispatcher("erro.jsp").forward(request, response);
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
