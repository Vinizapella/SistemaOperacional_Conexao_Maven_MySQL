package org.example.utils;

import org.example.Model.Contato;

import java.util.List;

public class Utils {

    public static void exibirContatos(List<Contato> contatos){
        if(!contatos.isEmpty()){
            for (Contato contato:contatos){
                System.out.println("ID: "+contato.getId());
                System.out.println("NOME: "+ contato.getNome());
                System.out.println("TELEFONE: "+ contato.getTelefone());
                System.out.println("EMAIL: "+ contato.getEmail());
                System.out.println("OBSERVACAO: "+contato.getObservacao());
            }
        }else{
            System.out.println("Nenhum contato encontrado.");
        }

    }
}
