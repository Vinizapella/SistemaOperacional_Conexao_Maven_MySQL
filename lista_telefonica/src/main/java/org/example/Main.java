package org.example;

import org.example.Model.Contato;
import org.example.Dao.ContatoDAO;
import org.example.Utils.Utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            inicio();
        }
    }

    public static void inicio() {
        System.out.println("-------- Sistema de Lista Telefonica --------");
        System.out.println("1. Cadastrar contato");
        System.out.println("2. Listar todos os contatos");
        System.out.println("3. Buscar contato por nome");
        System.out.println("4. Atualizar contato");
        System.out.println("5. Remover contato");
        System.out.println("6. Sair do sistema");
        System.out.print("Escolha uma opção: ");

        int opcao;
        try {
            opcao = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opção inválida. Tente novamente.");
            return;
        }

        switch (opcao) {
            case 1 -> cadastrarContato();
            case 2 -> listarContatos();
            case 3 -> buscarContatoPorNome();
            case 4 -> atualizarContato();
            case 5 -> removerContato();
            case 6 -> {
                System.out.println("Saindo do sistema...");
                System.exit(0);
            }
            default -> System.out.println("Opção inválida. Tente novamente.");
        }
    }


    public static void cadastrarContato() {
        System.out.println("-------- Cadastrar Contato --------");
        Contato contato = capturarDadosContato(false);
        try {
            new ContatoDAO().InserirContato(contato);
            System.out.println("Contato cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro no banco de dados ao cadastrar.");
            e.printStackTrace();
        }
    }

    public static void listarContatos() {
        System.out.println("-------- Lista de Contatos --------");
        List<Contato> contatos = new ArrayList<>();
        try {
            contatos = new ContatoDAO().listarContato();
        } catch (SQLException e) {
            System.out.println("Erro no banco de dados ao listar.");
            e.printStackTrace();
        }
        Utils.exibirContatos(contatos);
    }

    public static void buscarContatoPorNome() {
        System.out.println("-------- Buscar Contato por Nome --------");
        System.out.print("Digite o nome que deseja procurar: ");
        String nome = sc.nextLine();

        List<Contato> contatos = new ArrayList<>();
        try {
            contatos = new ContatoDAO().buscarContatosPorNome(nome);
        } catch (SQLException e) {
            System.out.println("Erro no banco de dados ao buscar.");
            e.printStackTrace();
        }
        Utils.exibirContatos(contatos);
    }

    public static void atualizarContato() {
        System.out.println("-------- Atualizar Contato --------");
        listarContatos();
        System.out.print("Digite o ID do contato que deseja atualizar: ");

        int id;
        try {
            id = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }

        Contato contato = capturarDadosContato(true);
        contato.setId(id);

        try {
            new ContatoDAO().atualizarContato(contato);
            System.out.println("Contato atualizado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar contato.");
            e.printStackTrace();
        }
    }

    public static void removerContato() {
        System.out.println("-------- Remover Contato --------");
        listarContatos();
        System.out.print("Digite o ID do contato que deseja remover: ");

        int id;
        try {
            id = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }

        try {
            new ContatoDAO().removerContato(id);
            System.out.println("Contato removido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao remover contato.");
            e.printStackTrace();
        }
    }

    private static Contato capturarDadosContato(boolean atualizar) {
        String nome = "";
        if (!atualizar) {
            System.out.print("Nome: ");
            nome = sc.nextLine();
        }
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Observação: ");
        String observacao = sc.nextLine();

        return atualizar
                ? new Contato("", telefone, email, observacao)  // nome não altera
                : new Contato(nome, telefone, email, observacao);
    }
}
