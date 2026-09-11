package br.com.sistema.dao;

import br.com.sistema.model.Usuario;
import br.com.sistema.util.Conexao;
import br.com.sistema.util.PasswordUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public Usuario autenticar(String email, String senha) throws SQLException {
        String sql = "SELECT id, nome, email, senha, perfil FROM usuario WHERE email = ?";
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next() && PasswordUtil.verificar(senha, rs.getString("senha"))) {
                    return new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        null,
                        rs.getString("perfil")
                    );
                }
            }
        }
        return null;
    }

    public boolean cadastrar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (nome, email, senha, perfil) VALUES (?, ?, ?, ?)";
        String hash = PasswordUtil.gerarHash(usuario.getSenha());

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, hash);
            ps.setString(4, usuario.getPerfil());
            return ps.executeUpdate() > 0;
        }
    }

    public List<Usuario> listar() throws SQLException {
        String sql = "SELECT id, nome, email, perfil FROM usuario ORDER BY id";
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                usuarios.add(new Usuario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    null,
                    rs.getString("perfil")
                ));
            }
        }
        return usuarios;
    }
}
