# Sistema de Segurança Web

Aplicação web desenvolvida em Java com JSP, Servlets, Maven e MySQL, com foco em autenticação, controle de acesso e proteção de senhas.

## Tecnologias
Java 17, JSP, Jakarta Servlets 6, Maven, MySQL, Apache Tomcat 10.1+, BCrypt, HTML5 e CSS3.

## Segurança implementada
- Senhas armazenadas com hash BCrypt.
- Login com verificação do hash.
- Sessão HTTP e cookie HttpOnly.
- Perfis ADMIN e USUARIO.
- Proteção de páginas por filtro.
- Bloqueio de usuário comum na área administrativa com HTTP 403.
- PreparedStatement nas consultas ao banco.
- Cabeçalhos básicos de segurança.
- Interface responsiva.

## Banco de dados
Execute `banco.sql` no MySQL. O script não possui usuários com hashes de exemplo não validados.

1. Cadastre um usuário pela aplicação.
2. Para transformá-lo em administrador, execute:
```sql
UPDATE usuario SET perfil='ADMIN' WHERE email='seu-email@exemplo.com';
```

## Execução
1. Configure a senha do MySQL em `Conexao.java`.
2. Execute `banco.sql`.
3. Execute `mvn clean package`.
4. Publique o `.war` em um Tomcat 10.1+.
5. Acesse a aplicação pelo navegador.
6. Cadastre um usuário e realize os testes.

## Entrega
O GitHub deve conter o código-fonte completo, `banco.sql`, README e o PDF do Plano de Testes após a execução local dos cenários.
