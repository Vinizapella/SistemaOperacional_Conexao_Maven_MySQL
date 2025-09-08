package org.example.Dao;

import org.example.Model.Contato;
import org.example.Database.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {

    public void InserirContato(Contato contato) throws SQLException {
        String query = """
           INSERT INTO contato (nome, telefone, email, observacao)
           VALUES (?, ?, ?, ?)
        """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getTelefone());
            stmt.setString(3, contato.getEmail());
            stmt.setString(4, contato.getObservacao());
            stmt.executeUpdate();
        }
    }

    public List<Contato> listarContato() throws SQLException {
        List<Contato> contatos = new ArrayList<>();
        String query = """
             SELECT id, nome, telefone, email, observacao
             FROM contato
        """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");          // <-- getString
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");
                String observacao = rs.getString("observacao");
                contatos.add(new Contato(id, nome, telefone, email, observacao));
            }
        }
        return contatos;
    }

    public List<Contato> buscarContatosPorNome(String nome) throws SQLException {
        List<Contato> contatos = new ArrayList<>();
        String query = """
            SELECT id, nome, telefone, email, observacao
            FROM contato
            WHERE nome LIKE ?
        """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, "%" + nome + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nomeBanco = rs.getString("nome");  // <-- getString
                    String telefone = rs.getString("telefone");
                    String email = rs.getString("email");
                    String observacao = rs.getString("observacao");
                    contatos.add(new Contato(id, nomeBanco, telefone, email, observacao));
                }
            }
        }
        return contatos;
    }

    public void atualizarContato(Contato contato) throws SQLException {
        String query = """
            UPDATE contato
            SET telefone = ?, email = ?, observacao = ?
            WHERE id = ?
        """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, contato.getTelefone());
            stmt.setString(2, contato.getEmail());
            stmt.setString(3, contato.getObservacao());
            stmt.setInt(4, contato.getId());
            stmt.executeUpdate();
        }
    }

    public void removerContato(int id) throws SQLException {
        String query = "DELETE FROM contato WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
