package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import model.Cliente;
import model.ClienteDAO;

@WebServlet("/editar")
public class EditarClienteServlet extends HttpServlet {

    private ClienteDAO clienteDAO;

    @Override
    public void init() {
        clienteDAO = new ClienteDAO();
    }


    // Abre a tela de edição
    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(
                request.getParameter("id")
        );

        Cliente cliente = clienteDAO.buscarPorId(id);

        request.setAttribute(
                "cliente",
                cliente
        );

        request.getRequestDispatcher(
                "editar.jsp"
        ).forward(request, response);
    }


    // Salva as alterações
    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        Cliente cliente = new Cliente();

        cliente.setId(
                Integer.parseInt(
                        request.getParameter("id")
                )
        );

        cliente.setNome(
                request.getParameter("nome")
        );

        cliente.setEmail(
                request.getParameter("email")
        );

        cliente.setTelefone(
                request.getParameter("telefone")
        );

        cliente.setCep(
                request.getParameter("cep")
        );

        cliente.setLogradouro(
                request.getParameter("logradouro")
        );

        cliente.setBairro(
                request.getParameter("bairro")
        );

        cliente.setCidade(
                request.getParameter("cidade")
        );

        cliente.setUf(
                request.getParameter("uf")
        );

        clienteDAO.atualizar(cliente);

        response.sendRedirect("cliente");
    }
}