/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.javaweb.DAO;

import br.javaweb.util.JavaWebException;
import br.javaweb.util.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jgarcia
 */
public class FuncoesUsuarioDao {
    
    private final String SELECT_LOGIN_QUERY = "select * from fisioterapia where login = ? ";
    
    public Usuario getUsuario(String usuario) throws JavaWebException {
        Connection conn = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        
        Usuario user = new Usuario();
        try {
            conn = GerenciadorConexoes.getConexao();
            prepStmt = conn.prepareStatement(SELECT_LOGIN_QUERY);
            prepStmt.setString(1, usuario);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                user.setUsuario(rs.getString("usuario"));
            }
        } catch (SQLException e) {
            String msg = e.getMessage();
            JavaWebException ge = new JavaWebException(msg, e);
            throw ge;
        } finally {
            GerenciadorConexoes.closeAll(conn, prepStmt, rs);
        }
        
        return user;
    }
    
}
