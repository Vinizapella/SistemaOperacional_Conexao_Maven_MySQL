package exerciseOne;

import org.example.Conexao;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leia = new Scanner(System.in);

        System.out.println("Digite o seu nome");
        String nome = leia.nextLine();

        System.out.println("Digite a sua matricula");
        String matricula = leia.nextLine();

        System.out.println("Digite o seu curso");
        String curso = leia.nextLine();

        var aluno = new Aluno(nome, matricula, curso);
        var dao = new AlunoDAO();
        dao.inserirAluno(aluno);

        try (Connection conn = Conexao.conectar()) {
            if (conn != null) {
                System.out.println("Conexão realizada com sucesso!");
            } else {
                System.out.println("Falha na conexão.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
