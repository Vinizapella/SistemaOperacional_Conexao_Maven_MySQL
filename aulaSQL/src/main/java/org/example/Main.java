package org.example;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Digite seu nome: ");
        String nome = sc.nextLine();

        System.out.println("Digite seu email");
        String email = sc.nextLine();

        var user = new Usuario(nome, email);
        var dao = new UsuarioDAO();
        dao.inserirUsuario(user);
        
        
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