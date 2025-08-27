package exerciseTwo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProdutoDAO {
    public void inserirProduto(Produto produto){
        String query = "INSERT INTO produtos (nome, quantidade, preco) VALUE(?, ?, ?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getPreco());
            stmt.setString(3, produto.getQuantidade());
            stmt.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
