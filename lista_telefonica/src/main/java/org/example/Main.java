package org.example;

import org.example.Model.Contato;
import org.example.dao.ContatoDAO;
import org.example.utils.Utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        inicio();
    }
    public static void inicio(){
        System.out.println("-------- Sistema de Lista Telefonica --------");
        System.out.println("1. Cadastrar contato: Nome, Telefone, Email, Observação.");
        System.out.println("2. Listar todos os contatos cadastrados.");
        System.out.println("3. Buscar contato por nome.");
        System.out.println("4. Atualizar dados de um contato (telefone, email, observação).");
        System.out.println("5. Remover contato.");
        System.out.println("6. Sair do sistema.");
        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao){

            case 1:
                cadastrarContato();
                break;

            case 2:
                listarContatos();
                break;

            case 3:
                buscarContatoPorNome();
                break;



        }
    }

    public static void cadastrarContato(){
        System.out.println("-------- Cadastrar Contato --------");
        System.out.println("Digite o nome do contato: ");
        String nome = sc.nextLine();

        System.out.println("Digite o telefone do contato: ");
        String telefone = sc.nextLine();

        System.out.println("Digite o email do contato: ");
        String email = sc.nextLine();

        System.out.println("Digite o observacao do contato: ");
        String observacao = sc.nextLine();

        var contato = new Contato(nome, telefone, email, observacao);
        var contatoDao = new ContatoDAO();

        try {
            contatoDao.InserirContato(contato);
        }catch (SQLException e){
            System.out.println("Ocorreu um erro no banco de dados.");
            e.printStackTrace();
        }
    }

    public static void listarContatos(){
        System.out.println("-------- Lista de Contatos --------");
        List<Contato> contatos = new ArrayList<>();
        var contatoDao = new ContatoDAO();
        try {
            contatos = contatoDao.listarContato();
        } catch (SQLException e){
            System.out.println("Ocorreu um erro no banco da dados");
            e.printStackTrace();
        }

        Utils.exibirContatos(contatos);
    }

    public static void buscarContatoPorNome(){
        System.out.println("-------- Buscar Contato por Nome --------");
        System.out.println("Digite o nome que deseja procurar: ");
        String nome = sc.nextLine();
        List<Contato> contatos = new ArrayList<>();
        try {
            var contatoDao = new ContatoDAO();
            contatos = contatoDao.buscarContatosPorNome(nome);
        }catch (SQLException e){
            System.out.println("Ocorreu um erro no banco de dados");
            e.printStackTrace();
        }
        Utils.exibirContatos(contatos);

    }

}
