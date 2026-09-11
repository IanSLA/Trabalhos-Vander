package br.com.sistema.controller;

import br.com.sistema.dao.UsuarioDAO;
import br.com.sistema.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UsuarioDAO dao = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        if (email == null || senha == null || email.isBlank() || senha.isBlank()) {
            request.setAttribute("erro", "Preencha e-mail e senha.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        try {
            Usuario usuario = dao.autenticar(email.trim(), senha);

            if (usuario == null) {
                request.setAttribute("erro", "E-mail ou senha inválidos.");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }

            HttpSession antiga = request.getSession(false);
            if (antiga != null) antiga.invalidate();

            HttpSession sessao = request.getSession(true);
            sessao.setAttribute("usuario", usuario);
            sessao.setMaxInactiveInterval(30 * 60);

            if ("ADMIN".equals(usuario.getPerfil())) {
                response.sendRedirect(request.getContextPath() + "/admin.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/usuario.jsp");
            }
        } catch (Exception e) {
            throw new ServletException("Erro ao autenticar usuário.", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
}
