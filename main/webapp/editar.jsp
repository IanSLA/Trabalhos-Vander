<%@ page import="model.Cliente" %>

<%
    Cliente cliente =
        (Cliente) request.getAttribute("cliente");
%>

<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Cliente</title>
    <link rel="stylesheet" href="style.css">
</head>

<body>

<header>
    <h1>Sistema de Gestão</h1>
    <p>Editar Cliente</p>
</header>

<main>

<section>

    <h2>Alterar Dados</h2>

    <form action="editar" method="post">

        <input type="hidden"
               name="id"
               value="<%= cliente.getId() %>">

        <div class="campo">
            <label for="nome">Nome</label>
            <input type="text"
                   id="nome"
                   name="nome"
                   value="<%= cliente.getNome() %>"
                   required>
        </div>

        <div class="campo">
            <label for="email">E-mail</label>
            <input type="email"
                   id="email"
                   name="email"
                   value="<%= cliente.getEmail() %>"
                   required>
        </div>

        <div class="campo">
            <label for="telefone">Telefone</label>
            <input type="tel"
                   id="telefone"
                   name="telefone"
                   value="<%= cliente.getTelefone() %>">
        </div>

        <div class="campo">
            <label for="cep">CEP</label>
            <input type="text"
                   id="cep"
                   name="cep"
                   value="<%= cliente.getCep() %>">
        </div>

        <div class="campo">
            <label for="logradouro">Rua</label>
            <input type="text"
                   id="logradouro"
                   name="logradouro"
                   value="<%= cliente.getLogradouro() %>">
        </div>

        <div class="campo">
            <label for="bairro">Bairro</label>
            <input type="text"
                   id="bairro"
                   name="bairro"
                   value="<%= cliente.getBairro() %>">
        </div>

        <div class="campo">
            <label for="cidade">Cidade</label>
            <input type="text"
                   id="cidade"
                   name="cidade"
                   value="<%= cliente.getCidade() %>">
        </div>

        <div class="campo">
            <label for="uf">Estado</label>
            <input type="text"
                   id="uf"
                   name="uf"
                   value="<%= cliente.getUf() %>">
        </div>

        <button type="submit">
            Salvar Alterações
        </button>

    </form>

</section>

</main>

</body>
</html>