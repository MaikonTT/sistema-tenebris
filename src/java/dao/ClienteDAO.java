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
import model.Cliente;
import util.ConectaBanco;

/**
 *
 * @author Takeshi
 */
public class ClienteDAO {

    private static final String SELECT_ALL = "SELECT * FROM cliente ORDER BY id ASC;";
    private static final String INSERT = "INSERT INTO cliente (nivel, nome, cpf, telefone, email, login, senha) VALUES ( ?, ?, ?, ?, ?, ?, ?);";
    private static final String DELETE = "DELETE FROM cliente where id=?;";
    private static final String BUSCAR = "SELECT * FROM cliente WHERE id=?;";
    private static final String BUSCAR_USER = "SELECT * FROM cliente WHERE login=? AND senha=?;";
    private static final String UPDATE = "UPDATE cliente SET telefone=?, email=?, senha=? WHERE id=?;";
    private static final String UPDATE_FUNC = "UPDATE cliente SET telefone=?, email=?, senha=? WHERE id=?;";
    private static final String UPDATE_GER = "UPDATE cliente SET nome=?, cpf=?, telefone=?, email=?, login=?, senha=? WHERE id=?;";

    private Object pstmt;
    private Connection conexao;

    public boolean alterar(Cliente cliente) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(UPDATE);
            pstmt.setString(1, cliente.getTelefone());
            pstmt.setString(2, cliente.getEmail());
            pstmt.setString(3, cliente.getSenha());
            pstmt.setInt(4, cliente.getId());
            pstmt.execute();
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean alterarFunc(Cliente cliente) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(UPDATE_FUNC);
            pstmt.setString(1, cliente.getTelefone());
            pstmt.setString(2, cliente.getEmail());
            pstmt.setString(3, cliente.getSenha());
            pstmt.setInt(4, cliente.getId());
            pstmt.execute();
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean alterarGer(Cliente cliente) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(UPDATE_GER);
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getCpf());
            pstmt.setString(3, cliente.getTelefone());
            pstmt.setString(4, cliente.getEmail());            
            pstmt.setString(5, cliente.getLogin());
            pstmt.setString(6, cliente.getSenha());
            pstmt.setInt(7, cliente.getId());
            pstmt.execute();
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean excluir(Cliente cliente) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(DELETE);
            pstmt.setInt(1, cliente.getId());
            pstmt.execute();
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean cadastrar(Cliente cliente) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(INSERT);
            pstmt.setString(1, cliente.getNivel());
            pstmt.setString(2, cliente.getNome());
            pstmt.setString(3, cliente.getCpf());
            pstmt.setString(4, cliente.getTelefone());
            pstmt.setString(5, cliente.getEmail());
            pstmt.setString(6, cliente.getLogin());
            pstmt.setString(7, cliente.getSenha());
            pstmt.execute();
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<Cliente> listar() {
        //cria uma array de obJ Tipo
        ArrayList<Cliente> listaCliente = new ArrayList<Cliente>();
        try {
            //Conexao
            conexao = ConectaBanco.getConexao();
            //cria comando SQL
            PreparedStatement pstmt = conexao.prepareStatement(SELECT_ALL);
            //executa
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                //aqui cria um novo produto / instacia o produto que vai no array
                Cliente novoCliente = new Cliente();
                novoCliente.setId(rs.getInt("id"));
                novoCliente.setNome(rs.getString("nome"));
                novoCliente.setCpf(rs.getString("cpf"));
                novoCliente.setTelefone(rs.getString("telefone"));
                novoCliente.setEmail(rs.getString("email"));
                novoCliente.setLogin(rs.getString("login"));
                novoCliente.setSenha(rs.getString("senha"));
                //add na lista
                listaCliente.add(novoCliente);
            }
            return listaCliente;
        } catch (Exception ex) {
            return listaCliente;
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Cliente buscar(Cliente cliente) {
        try {
            //Conexao
            conexao = ConectaBanco.getConexao();
            //cria comando SQL
            PreparedStatement pstmt = conexao.prepareStatement(BUSCAR);
            pstmt.setInt(1, cliente.getId());
            //executa
            ResultSet rs = pstmt.executeQuery();
            // como a query ira retornar somente um registro, faremos o NEXT
            rs.next();
            cliente.setId(rs.getInt("id"));
            cliente.setNivel(rs.getString("nivel"));
            cliente.setNome(rs.getString("nome"));
            cliente.setCpf(rs.getString("cpf"));
            cliente.setTelefone(rs.getString("telefone"));
            cliente.setEmail(rs.getString("email"));
            cliente.setLogin(rs.getString("login"));
            cliente.setSenha(rs.getString("senha"));
        } catch (Exception e) {
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cliente;
    }

    public Cliente buscarUsuario(Cliente cliente) {
        try {
            //Conexao
            conexao = ConectaBanco.getConexao();
            //cria comando SQL
            PreparedStatement pstmt = conexao.prepareStatement(BUSCAR_USER);
            pstmt.setString(1, cliente.getLogin());
            pstmt.setString(2, cliente.getSenha());
            //executa
            ResultSet rs = pstmt.executeQuery();
            // como a query ira retornar somente um registro, faremos o NEXT
            rs.next();
            cliente.setId(rs.getInt("id"));
            cliente.setNivel(rs.getString("nivel"));
            cliente.setNome(rs.getString("nome"));
            cliente.setCpf(rs.getString("cpf"));
            cliente.setTelefone(rs.getString("telefone"));
            cliente.setEmail(rs.getString("email"));
            cliente.setLogin(rs.getString("login"));
            cliente.setSenha(rs.getString("senha"));
        } catch (Exception e) {
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cliente;
    }
}
