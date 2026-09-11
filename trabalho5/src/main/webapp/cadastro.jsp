<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<header class="topo">
    <div class="logo">CLIENTE PARCEIRO</div>
</header>
<main class="container">
    <section class="card form-card">
        <h1>Novo usuário</h1>

        <% if (request.getAttribute("erro") != null) { %>
            <div class="alert erro"><%= request.getAttribute("erro") %></div>
        <% } %>

        <form action="cadastro" method="post">
            <label for="nome">Nome</label>
            <input id="nome" name="nome" type="text" required maxlength="100">

            <label for="email">E-mail</label>
            <input id="email" name="email" type="email" required maxlength="150">

            <label for="senha">Senha</label>
            <input id="senha" name="senha" type="password" required minlength="6">

            <button type="submit" class="btn">Cadastrar</button>
        </form>

        <p class="link-area"><a href="login.jsp">Voltar ao login</a></p>
    </section>
</main>
</body>
</html>
