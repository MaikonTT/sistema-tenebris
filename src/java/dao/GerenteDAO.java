package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;
import util.ConectaBanco;

/**
 *
 * @author Takeshi
 */
public class GerenteDAO {

    private static final String BUSCAR = "SELECT * FROM gerente WHERE login=? AND senha=?;";
    private static final String UPDATE = "UPDATE gerente SET senha=? WHERE id=?;";

    private Object pstmt;
    private Connection conexao;

    public Gerente buscarUsuario(Gerente gerente) {
        try {
            //Conexao
            conexao = ConectaBanco.getConexao();
            //cria comando SQL
            PreparedStatement pstmt = conexao.prepareStatement(BUSCAR);
            pstmt.setString(1, gerente.getLogin());
            pstmt.setString(2, gerente.getSenha());
            //executa
            ResultSet rs = pstmt.executeQuery();
            // como a query ira retornar somente um registro, faremos o NEXT
            rs.next();
            gerente.setId(rs.getInt("id"));
            gerente.setNivel(rs.getString("nivel"));
            gerente.setLogin(rs.getString("login"));
            gerente.setSenha(rs.getString("senha"));
        } catch (Exception e) {
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(GerenteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return gerente;
    }

    public boolean alterar(Gerente gerente) {
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(UPDATE);
            pstmt.setString(1, gerente.getSenha());
            pstmt.setInt(2, gerente.getId());
            pstmt.execute();
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(GerenteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
