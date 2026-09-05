<%@ page import="java.util.List" %>
<%@ page import="model.Cliente" %>

<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Clientes Cadastrados</title>
    <link rel="stylesheet" href="style.css">
</head>

<body>

<header>
    <h1>Sistema de Gestão</h1>
    <p>Clientes Cadastrados</p>
</header>

<nav>
    <ul>
        <li><a href="index.html">Cadastro</a></li>
        <li><a href="cliente">Dados</a></li>
    </ul>
</nav>

<main>

<section>

    <h2>Clientes Cadastrados</h2>

    <table>

        <thead>
            <tr>
                <th>Nome</th>
                <th>E-mail</th>
                <th>Telefone</th>
                <th>Cidade</th>
                <th>Ações</th>
            </tr>
        </thead>

        <tbody>

        <%
            List<Cliente> clientes =
                (List<Cliente>) request.getAttribute("clientes");

            for (Cliente cliente : clientes) {
        %>

            <tr>
                <td><%= cliente.getNome() %></td>
                <td><%= cliente.getEmail() %></td>
                <td><%= cliente.getTelefone() %></td>
                <td><%= cliente.getCidade() %></td>

                <td>
                    <a href="editar?id=<%= cliente.getId() %>">
                        Editar
                    </a>

                    |

                    <a href="excluir?id=<%= cliente.getId() %>"
                       onclick="return confirm('Deseja excluir este cliente?');">
                        Excluir
                    </a>
                </td>
            </tr>

        <%
            }
        %>

        </tbody>

    </table>

</section>

</main>

<footer>
    <p>Desenvolvido para a disciplina de Desenvolvimento Web.</p>
</footer>

</body>
</html>