package org.example.dao;

import org.example.Model.Contato;
import org.example.database.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {

    public void InserirContato(Contato contato) throws SQLException {
        String query = """
           INSERT INTO contato
                (nome
                , telefone
                , email
                , observacao)
           VALUES
                 (?
                 , ?
                 , ?
                 , ?)
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
             SELECT id 
                , nome
                , telefone
                , email
                , observacao
             FROM contato
             """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getNString("nome");
                String telefone = rs.getNString("telefone");
                String email = rs.getNString("email");
                String observacao = rs.getNString("observacao");

                var contato = new Contato(id, nome, telefone, email, observacao);
                contatos.add(contato);

            }
        }
        return contatos;
    }

    public List<Contato> buscarContatosPorNome(String nome) throws SQLException {
        List<Contato> contatos = new ArrayList<>();
        String query = """
                    SELECT id
                            ,nome
                            ,telefone
                            ,email
                            ,observacao
                    FROM contato
                    WHERE nome LIKE ?
                """;

        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1,"%"+nome+"%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                String nomeBanco = rs.getNString("nome");
                String telefone = rs.getNString("telefone");
                String email = rs.getNString("email");
                String observacao = rs.getNString("observacao");

                var contato = new Contato(id, nomeBanco, telefone, email, observacao);
                contatos.add(contato);
            }
            return contatos;
        }
    }


    }
