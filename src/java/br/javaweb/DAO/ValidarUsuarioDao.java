/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.javaweb.DAO;

import br.javaweb.util.JavaWebException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jgarcia
 */
public class ValidarUsuarioDao {
    
    private final String SELECT_USUARIO_QUERY = "select Usuario,Senha from User where usuario = ? and senha = ?";
    
    public boolean getUsuarioSenha(String Usuario, String Senha) throws JavaWebException{
        Connection conn = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;        
        try {
            conn = GerenciadorConexoes.getConexao();
            prepStmt = conn.prepareStatement(SELECT_USUARIO_QUERY);
            prepStmt.setString(1, Usuario);
            prepStmt.setString(2, Senha);
            rs = prepStmt.executeQuery();
            while(rs.next()){
                String login = rs.getString("usuario");
                String password = rs.getString("senha");
                if(Usuario.equals(Usuario) && Senha.equals(Senha)){ 
                    return true;
                    
                }else{
                    return false;
                }
            }
        } catch (SQLException e) {
            String msg = e.getMessage();
            JavaWebException ge = new JavaWebException(msg, e);
            throw ge;
        } finally {
            GerenciadorConexoes.closeAll(conn, prepStmt, rs);
        }
        return false;
    }
    
}
