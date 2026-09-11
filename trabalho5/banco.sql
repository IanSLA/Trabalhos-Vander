CREATE DATABASE IF NOT EXISTS sistema_seguranca
  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE sistema_seguranca;

CREATE TABLE IF NOT EXISTS usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL,
    perfil ENUM('ADMIN', 'USUARIO') NOT NULL DEFAULT 'USUARIO',
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- O cadastro pela aplicação gera o hash BCrypt automaticamente.
-- Depois de cadastrar um usuário, para torná-lo administrador:
-- UPDATE usuario SET perfil='ADMIN' WHERE email='seu-email@exemplo.com';
