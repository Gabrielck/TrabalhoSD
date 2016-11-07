
import bancoInterface.*;


/*
 * To change this license header, choose License Headesrs in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * \
 *
 * @author gabrielkr
 */
public class ClienteTeste {

    public static void main(String[] args) throws Exception {
        System.out.println("asdfasdf");
        //BancoDeDados.inserir("Frase de teste", 2);
        
        if (BancoDeDados.alterar(10, "aaa", 1)) {
            System.out.println("Frase existe");
        } else {
            System.out.println("Frase nao existe!");
        }

        Frase a = BancoDeDados.consulta(150);
        if (a != null) {
            System.out.println(a.getFrase());
        } else {
            System.out.println("Frase não existe para consulta");
        }

        System.out.println("Frase aleatoria do tipo 2");
        Frase b = BancoDeDados.mensagem(15);
        if (b != null) {
            System.out.println(b.getFrase());
        } else {
            System.out.println("Não existe frase do tipo 2!");
        }

        System.out.println("Lista todas as frases do tipo 2");
        Frase c[] = BancoDeDados.lista_tipo(1);
        if (c != null) {
            for (Frase x : c) {
                System.out.println(x.getFrase());
            }
        }
        
        System.out.println("Excluir frase");
        if(BancoDeDados.exluir(26))
            System.out.println("Frase excluida");
        else
            System.out.println("Codigo invalido!");

    }
}
