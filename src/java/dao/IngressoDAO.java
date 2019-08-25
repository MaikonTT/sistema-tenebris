/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;
import util.ConectaBanco;

/**
 *
 * @author Takeshi
 */
public class IngressoDAO {

    private static final String SELECT_ALL = "SELECT * FROM ingresso WHERE cpf=?;";
    private static final String INSERT = "INSERT INTO ingresso (codigo, data, valor, cpf) VALUES ( ?, ?, ?, ?);";
    private static final String DELETE = "DELETE FROM ingresso where id=?;";
    private static final String BUSCAR = "SELECT * FROM ingresso WHERE codigo=?;";
    private static final String UPDATE = "UPDATE ingresso SET codigo=? WHERE id=?;";

    private Object pstmt;
    private Connection conexao;

    public boolean alterar(Ingresso ingresso) {

        try {

            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(UPDATE);

            pstmt.setString(1, ingresso.getCodigo());
            pstmt.setInt(1, ingresso.getId());

            pstmt.execute();
            return true;

        } catch (Exception ex) {

            return false;

        } finally {

            try {
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(IngressoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public boolean excluir(Ingresso ingresso) {

        try {

            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(DELETE);

            pstmt.setInt(1, ingresso.getId());

            pstmt.execute();

            return true;

        } catch (Exception ex) {

            return false;

        } finally {

            try {
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(IngressoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public boolean cadastrar(Ingresso ingresso) {

        try {

            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(INSERT);

            pstmt.setString(1, ingresso.getCodigo());
            pstmt.setString(2, ingresso.getData());
            pstmt.setDouble(3, ingresso.getValor());
            pstmt.setString(4, ingresso.getCpf());

            pstmt.execute();

            return true;

        } catch (Exception ex) {

            return false;

        } finally {

            try {
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(IngressoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public ArrayList<Ingresso> listar(Ingresso ingresso) {

        //cria uma array de obJ Tipo
        ArrayList<Ingresso> listaIngresso = new ArrayList<Ingresso>();

        try {
//Conexao
            conexao = ConectaBanco.getConexao();

            
            //cria comando SQL
            PreparedStatement pstmt = conexao.prepareStatement(SELECT_ALL);
            
            pstmt.setString(1, ingresso.getCpf());
            
            //executa
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                //aqui cria um novo produto / instacia o produto que vai no array
                Ingresso novoIngresso = new Ingresso();

                novoIngresso.setId(rs.getInt("id"));
                novoIngresso.setCodigo(rs.getString("codigo"));
                novoIngresso.setData(rs.getString("data"));
                novoIngresso.setValor(rs.getDouble("valor"));
                novoIngresso.setCpf(rs.getString("cpf"));

                //add na lista
                listaIngresso.add(novoIngresso);
            }
            return listaIngresso;

        } catch (Exception ex) {

            return listaIngresso;

        } finally {

            try {
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(IngressoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public Ingresso buscar() {

        Ingresso ingresso = new Ingresso();

        try {

            //Conexao
            conexao = ConectaBanco.getConexao();
            //cria comando SQL
            PreparedStatement pstmt = conexao.prepareStatement(BUSCAR);

            pstmt.setString(1, ingresso.getCodigo());
            //executa
            ResultSet rs = pstmt.executeQuery();

            // como a query ira retornar somente um registro, faremos o NEXT
            rs.next();

            ingresso.setId(rs.getInt("id"));
            ingresso.setCodigo(rs.getString("codigo"));
            ingresso.setData(rs.getString("data"));
            ingresso.setValor(rs.getDouble("valor"));

        } catch (Exception e) {

            //
        } finally {

            try {
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(IngressoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ingresso;
    }

}
