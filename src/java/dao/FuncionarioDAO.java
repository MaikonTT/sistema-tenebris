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
public class FuncionarioDAO {

    private static final String SELECT_ALL = "SELECT * FROM funcionario ORDER BY id ASC";
    private static final String INSERT = "INSERT INTO funcionario (nivel, nome, cpf, telefone, endereco, login, senha) VALUES ( ?, ?, ?, ?, ?, ?, ?);";
    private static final String DELETE = "DELETE FROM funcionario where id=?";
    private static final String BUSCAR = "SELECT id FROM funcionario WHERE cpf=?;";
    private static final String BUSCAR_USER = "SELECT * FROM funcionario WHERE login=? AND senha=?";
    private static final String UPDATE = "UPDATE funcionario SET nome=?, cpf=?,telefone=?, endereco=?, login=?, senha=? WHERE id=?;";

    private Object pstmt;
    private Connection conexao;

    public Funcionario buscarUsuario(Funcionario funcionario) {
        try {
            //Conexao
            conexao = ConectaBanco.getConexao();
            //cria comando SQL
            PreparedStatement pstmt = conexao.prepareStatement(BUSCAR_USER);

            pstmt.setString(1, funcionario.getLogin());
            pstmt.setString(2, funcionario.getSenha());
            //executa
            ResultSet rs = pstmt.executeQuery();

            // como a query ira retornar somente um registro, faremos o NEXT
            rs.next();

            funcionario.setId(rs.getInt("id"));
            funcionario.setNivel(rs.getString("nivel"));
            funcionario.setNome(rs.getString("nome"));
            funcionario.setCpf(rs.getString("cpf"));
            funcionario.setTelefone(rs.getString("telefone"));
            funcionario.setEndereco(rs.getString("endereco"));
            funcionario.setLogin(rs.getString("login"));
            funcionario.setSenha(rs.getString("senha"));

        } catch (Exception e) {

            //
        } finally {

            try {
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return funcionario;

    }

    public boolean alterar(Funcionario funcionario) {

        try {

            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(UPDATE);
            pstmt.setString(1, funcionario.getNome());
            pstmt.setString(2, funcionario.getCpf());
            pstmt.setString(3, funcionario.getTelefone());
            pstmt.setString(4, funcionario.getEndereco());
            pstmt.setString(5, funcionario.getLogin());
            pstmt.setString(6, funcionario.getSenha());
            pstmt.setInt(7, funcionario.getId());
            pstmt.execute();
            return true;

        } catch (Exception ex) {

            return false;

        } finally {

            try {
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public boolean excluir(Funcionario funcionario) {

        try {

            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(DELETE);

            pstmt.setInt(1, funcionario.getId());

            pstmt.execute();

            return true;

        } catch (Exception ex) {

            return false;

        } finally {

            try {
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public boolean cadastrar(Funcionario funcionario) {

        try {
            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(INSERT);

            pstmt.setString(1, funcionario.getNivel());
            pstmt.setString(2, funcionario.getNome());
            pstmt.setString(3, funcionario.getCpf());
            pstmt.setString(4, funcionario.getTelefone());
            pstmt.setString(5, funcionario.getEndereco());
            pstmt.setString(6, funcionario.getLogin());
            pstmt.setString(7, funcionario.getSenha());

            pstmt.execute();

            return true;

        } catch (Exception ex) {
            return false;

        } finally {

            try {
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public ArrayList<Funcionario> listar() {

        //cria uma array de obJ Tipo
        ArrayList<Funcionario> listaFuncionario = new ArrayList<Funcionario>();

        try {

            //Conexao
            conexao = ConectaBanco.getConexao();
            //cria comando SQL
            PreparedStatement pstmt = conexao.prepareStatement(SELECT_ALL);

            //executa
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                //aqui cria um novo produto / instacia o produto que vai no array
                Funcionario novoFuncionario = new Funcionario();

                novoFuncionario.setId(rs.getInt("id"));
                novoFuncionario.setNome(rs.getString("nome"));
                novoFuncionario.setCpf(rs.getString("cpf"));
                novoFuncionario.setTelefone(rs.getString("telefone"));
                novoFuncionario.setEndereco(rs.getString("endereco"));
                novoFuncionario.setLogin(rs.getString("login"));
                novoFuncionario.setSenha(rs.getString("senha"));

                //add na lista
                listaFuncionario.add(novoFuncionario);
            }
            return listaFuncionario;

        } catch (Exception ex) {

            return listaFuncionario;

        } finally {

            try {
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public Funcionario buscar(Funcionario funcionario) {
        try {
            //Conexao
            conexao = ConectaBanco.getConexao();
            //cria comando SQL
            PreparedStatement pstmt = conexao.prepareStatement(BUSCAR);

            pstmt.setString(1, funcionario.getCpf());
            //executa
            ResultSet rs = pstmt.executeQuery();

            // como a query ira retornar somente um registro, faremos o NEXT
            rs.next();

            funcionario.setId(rs.getInt("id"));
            funcionario.setNome(rs.getString("nome"));
            funcionario.setCpf(rs.getString("cpf"));
            funcionario.setTelefone(rs.getString("telefone"));
            funcionario.setEndereco(rs.getString("endereco"));
            funcionario.setLogin(rs.getString("login"));
            funcionario.setSenha(rs.getString("senha"));

        } catch (Exception e) {

            //
        } finally {

            try {
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return funcionario;
    }

}
