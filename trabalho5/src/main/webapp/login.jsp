<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<header class="topo">
    <div class="logo">CLIENTE PARCEIRO</div>
</header>
<main class="container">
    <section class="card form-card">
        <h1>Login</h1>

        <% if (request.getAttribute("erro") != null) { %>
            <div class="alert erro"><%= request.getAttribute("erro") %></div>
        <% } %>
        <% if ("1".equals(request.getParameter("logout"))) { %>
            <div class="alert sucesso">Sessão encerrada com sucesso.</div>
        <% } %>
        <% if ("1".equals(request.getParameter("cadastro"))) { %>
            <div class="alert sucesso">Cadastro realizado. Faça login.</div>
        <% } %>
        <% if ("negado".equals(request.getParameter("acesso"))) { %>
            <div class="alert erro">Faça login para acessar uma página protegida.</div>
        <% } %>

        <form action="login" method="post">
            <label for="email">E-mail</label>
            <input id="email" name="email" type="email" required maxlength="150">

            <label for="senha">Senha</label>
            <input id="senha" name="senha" type="password" required minlength="6">

            <button type="submit" class="btn">Entrar</button>
        </form>

        <p class="link-area"><a href="cadastro.jsp">Ainda não tenho cadastro</a></p>
    </section>
</main>
</body>
</html>
