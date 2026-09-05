CREATE DATABASE IF NOT EXISTS sistema_gestao;

USE sistema_gestao;

CREATE TABLE IF NOT EXISTS clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefone VARCHAR(20),
    cep VARCHAR(9),
    logradouro VARCHAR(150),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    uf VARCHAR(2)
);