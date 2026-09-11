package br.com.sistema.filter;

import br.com.sistema.model.Usuario;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebFilter(urlPatterns = {"/usuario.jsp", "/admin.jsp"})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession sessao = req.getSession(false);

        Usuario usuario = sessao == null ? null : (Usuario) sessao.getAttribute("usuario");

        if (usuario == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp?acesso=negado");
            return;
        }

        String uri = req.getRequestURI();

        if (uri.endsWith("/admin.jsp") && !"ADMIN".equals(usuario.getPerfil())) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Acesso restrito ao administrador.");
            return;
        }

        chain.doFilter(request, response);
    }
}
