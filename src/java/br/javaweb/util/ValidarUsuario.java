/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.javaweb.util;

import br.javaweb.DAO.ValidarUsuarioDao;
import br.javaweb.DAO.FuncoesUsuarioDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jgarcia
 */
public class ValidarUsuario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            boolean a = new ValidarUsuarioDao().getUsuarioSenha(usuario, senha);
            if (a) {
                Usuario login = new FuncoesUsuarioDao().getUsuario(usuario);
                HttpSession sessao = request.getSession();
                sessao.setAttribute("Login", login);
                response.sendRedirect("PaginaInicial");
            } else {
                response.sendRedirect("UsuarioInvalido");
            }
        } catch (JavaWebException ex) {
            Logger.getLogger(ValidarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
