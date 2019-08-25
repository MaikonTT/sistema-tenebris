/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.CupomDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cupom;
import model.CodigoIngresso;

/**
 *
 * @author Takeshi
 */
@WebServlet(name = "ControleCupom", urlPatterns = {"/ControleCupom"})
public class ControleCupom extends HttpServlet {

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
            //recupera param acao
            String acao = request.getParameter("acao");
            if (acao.equals("Criar")) {

                int q = Integer.parseInt(request.getParameter("quant"));
                
                while (q > 0) {
                    
                CodigoIngresso codin = new CodigoIngresso();                
                
                String codigo = codin.gerarCodigo();

                    String descri = request.getParameter("descri");
                    double desconto = Double.parseDouble(request.getParameter("desconto"));
                    String estado = "pronto";

                    Cupom cupom = new Cupom();
                    cupom.setDescri(descri);
                    cupom.setCodigo(codigo);
                    cupom.setDesconto(desconto);
                    cupom.setEstado(estado);

                    CupomDAO cdao = new CupomDAO();
                    cdao.cadastrar(cupom);
                    q--;
                }
                //redireciono
                request.setAttribute("mensagem", " cadastrado com sucesso!");
                RequestDispatcher rdr = request.getRequestDispatcher("sucessoCup.jsp");
                rdr.forward(request, response);
            } else if (acao.equals("Alterar")) {

                int id = Integer.parseInt(request.getParameter("id"));
                
                Cupom cupom = new Cupom();
                cupom.setId(id);              
                
                String descri = request.getParameter("novadescri");
                String codigo = request.getParameter("novocodigo");
                double desconto = Double.parseDouble(request.getParameter("novodesconto"));
                String estado = request.getParameter("novoestado");

                //setar atributos
                cupom.setDescri(descri);
                cupom.setCodigo(codigo);
                cupom.setDesconto(desconto);
                cupom.setEstado(estado);
                
                CupomDAO cDAO = new CupomDAO();
                cDAO.alterar(cupom);
                //redireciono                
                request.setAttribute("cupoms", cupom);
                RequestDispatcher rd = request.getRequestDispatcher("admin_listar_cupom.jsp");
                rd.forward(request, response);
            } else if (acao.equals("Listar")) {
                //cria objeto
                CupomDAO cDAO = new CupomDAO();
                //executa o método listar
                ArrayList<Cupom> cupoms = cDAO.listar();
                //add a lista no obJ eto request   
                request.setAttribute("listaCupoms", cupoms);
                //encaminha o request para o JSP
                RequestDispatcher rd = request.getRequestDispatcher("admin_listar_cupom.jsp");
                rd.forward(request, response);

            } else if (acao.equals("Excluir")) {
                Cupom cupom = new Cupom();
                CupomDAO cdao = new CupomDAO();
                int id = Integer.parseInt(request.getParameter("id"));
                cupom.setId(id);
                cdao.excluir(cupom);
                request.setAttribute("cupom", cupom);
                RequestDispatcher rd = request.getRequestDispatcher("admin_listar_cupom.jsp");
                rd.forward(request, response);
            } else if (acao.equals("Validar")) {

                String codigo = request.getParameter("codigo");
                Cupom cupom = new Cupom();
                CupomDAO cDAO = new CupomDAO();
                
                cupom.setCodigo(codigo);
                cDAO.buscar2(cupom);

                request.setAttribute("cupom", cupom);
                RequestDispatcher rd = request.getRequestDispatcher("pagamento.jsp");
                rd.forward(request, response);

            } else if (acao.equals("Pesquisar")) {
                
                int id = Integer.parseInt(request.getParameter("idpesq"));
                Cupom cupom = new Cupom();
                CupomDAO cDAO = new CupomDAO();
                
                cupom.setId(id);
                cDAO.buscar(cupom);                
                
                request.setAttribute("cupom",cupom);
                RequestDispatcher rd = request.getRequestDispatcher("admin_listar_cupom.jsp");
                rd.forward(request, response);
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
