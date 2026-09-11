<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="br.com.sistema.model.Usuario" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Área Administrativa</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<header class="topo">
    <div class="logo">CLIENTE PARCEIRO</div>
    <a class="btn secundario" href="logout">Sair</a>
</header>
<main class="container">
    <section class="card">
        <h1>Área Administrativa</h1>
        <p>Administrador autenticado: <strong><%= usuario.getNome() %></strong>.</p>
        <p>Esta área é restrita ao perfil ADMIN.</p>
        <div class="badge">ACESSO ADMINISTRATIVO</div>
    </section>
</main>
</body>
</html>
