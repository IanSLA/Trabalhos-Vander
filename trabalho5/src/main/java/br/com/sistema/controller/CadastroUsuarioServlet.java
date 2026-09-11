package br.com.sistema.controller;

import br.com.sistema.dao.UsuarioDAO;
import br.com.sistema.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/cadastro")
public class CadastroUsuarioServlet extends HttpServlet {
    private final UsuarioDAO dao = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        if (nome == null || email == null || senha == null ||
            nome.isBlank() || email.isBlank() || senha.length() < 6) {
            request.setAttribute("erro", "Preencha os campos. A senha deve ter pelo menos 6 caracteres.");
            request.getRequestDispatcher("/cadastro.jsp").forward(request, response);
            return;
        }

        Usuario u = new Usuario();
        u.setNome(nome.trim());
        u.setEmail(email.trim());
        u.setSenha(senha);
        u.setPerfil("USUARIO");

        try {
            dao.cadastrar(u);
            response.sendRedirect(request.getContextPath() + "/login.jsp?cadastro=1");
        } catch (Exception e) {
            request.setAttribute("erro", "Não foi possível cadastrar. Verifique se o e-mail já existe.");
            request.getRequestDispatcher("/cadastro.jsp").forward(request, response);
        }
    }
}
