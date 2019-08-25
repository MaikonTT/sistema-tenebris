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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;
import util.ConectaBanco;

/**
 *
 * @author Takeshi
 */
public class CupomDAO {

    private static final String SELECT_ALL = "SELECT * FROM cupom";
    private static final String INSERT = "INSERT INTO cupom (descri, codigo, desconto, estado) VALUES ( ?, ?, ?, ?);";
    private static final String DELETE = "DELETE FROM cupom where id=?";
    private static final String BUSCAR = "SELECT * FROM cupom WHERE id=?;";
    private static final String BUSCAR2 = "SELECT * FROM cupom WHERE codigo=?;";
    private static final String UPDATE = "UPDATE cupom SET descri=?, codigo=?, desconto=?, estado=? WHERE id=?;";

    private Object pstmt;
    private Connection conexao;

    public boolean alterar(Cupom cupom) {

        try {

            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(UPDATE);
            pstmt.setString(1, cupom.getDescri());
            pstmt.setString(2, cupom.getCodigo());
            pstmt.setDouble(3, cupom.getDesconto());
            pstmt.setString(4, cupom.getEstado());
            pstmt.setInt(5, cupom.getId());
            pstmt.execute();
            return true;

        } catch (Exception ex) {

            return false;

        } finally {

            try {
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(CupomDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean excluir(Cupom cupom) {

        try {

            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(DELETE);

            pstmt.setInt(1, cupom.getId());

            pstmt.execute();

            return true;

        } catch (Exception ex) {

            return false;

        } finally {

            try {
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(CupomDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean cadastrar(Cupom cupom) {

        try {
            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(INSERT);

            pstmt.setString(1, cupom.getDescri());
            pstmt.setString(2, cupom.getCodigo());
            pstmt.setDouble(3, cupom.getDesconto());
            pstmt.setString(4, cupom.getEstado());

            pstmt.execute();

            return true;

        } catch (Exception ex) {
            return false;

        } finally {

            try {
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(CupomDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<Cupom> listar() {

        //cria uma array de obJ Tipo
        ArrayList<Cupom> listaCupoms = new ArrayList<Cupom>();

        try {

            //Conexao
            conexao = ConectaBanco.getConexao();
            //cria comando SQL
            PreparedStatement pstmt = conexao.prepareStatement(SELECT_ALL);

            //executa
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                //aqui cria um novo produto / instacia o produto que vai no array
                Cupom novoCupom = new Cupom();

                novoCupom.setId(rs.getInt("id"));
                novoCupom.setDescri(rs.getString("descri"));
                novoCupom.setCodigo(rs.getString("codigo"));
                novoCupom.setDesconto(rs.getDouble("desconto"));
                novoCupom.setEstado(rs.getString("estado"));

                //add na lista
                listaCupoms.add(novoCupom);
            }
            return listaCupoms;

        } catch (Exception ex) {

            return listaCupoms;

        } finally {

            try {
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(CupomDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Cupom buscar(Cupom cupom) {
        try {
            //Conexao
            conexao = ConectaBanco.getConexao();
            //cria comando SQL
            PreparedStatement pstmt = conexao.prepareStatement(BUSCAR);

            pstmt.setInt(1, cupom.getId());
            //executa
            ResultSet rs = pstmt.executeQuery();

            // como a query ira retornar somente um registro, faremos o NEXT
            rs.next();

            cupom.setId(rs.getInt("id"));
            cupom.setDescri(rs.getString("descri"));
            cupom.setCodigo(rs.getString("codigo"));
            cupom.setDesconto(rs.getDouble("desconto"));
            cupom.setEstado(rs.getString("estado"));

        } catch (Exception e) {

            //
        } finally {

            try {
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(CupomDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cupom;
    }
    public Cupom buscar2(Cupom cupom) {
        try {
            //Conexao
            conexao = ConectaBanco.getConexao();
            //cria comando SQL
            PreparedStatement pstmt = conexao.prepareStatement(BUSCAR2);

            pstmt.setString(1, cupom.getCodigo());
            //executa
            ResultSet rs = pstmt.executeQuery();

            // como a query ira retornar somente um registro, faremos o NEXT
            rs.next();

            cupom.setId(rs.getInt("id"));
            cupom.setDescri(rs.getString("descri"));
            cupom.setCodigo(rs.getString("codigo"));
            cupom.setDesconto(rs.getDouble("desconto"));
            cupom.setEstado(rs.getString("estado"));

        } catch (Exception e) {

            //
        } finally {

            try {
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(CupomDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cupom;
    }
}
