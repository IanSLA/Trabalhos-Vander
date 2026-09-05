package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import model.Cliente;
import model.ClienteDAO;

@WebServlet("/cliente")
public class ClienteServlet extends HttpServlet {

    private ClienteDAO clienteDAO;

    @Override
    public void init() {
        clienteDAO = new ClienteDAO();
    }


    // CREATE e READ
    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        Cliente cliente = new Cliente();

        cliente.setNome(request.getParameter("nome"));
        cliente.setEmail(request.getParameter("email"));
        cliente.setTelefone(request.getParameter("telefone"));
        cliente.setCep(request.getParameter("cep"));
        cliente.setLogradouro(request.getParameter("logradouro"));
        cliente.setBairro(request.getParameter("bairro"));
        cliente.setCidade(request.getParameter("cidade"));
        cliente.setUf(request.getParameter("uf"));

        clienteDAO.inserir(cliente);

        response.sendRedirect("cliente");
    }


    // READ
    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        request.setAttribute(
                "clientes",
                clienteDAO.listar()
        );

        request.getRequestDispatcher(
                "clientes.jsp"
        ).forward(request, response);
    }
}