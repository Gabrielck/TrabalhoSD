/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteudp;
import java.util.Scanner;

/**
 *
 * @author Jônatas Strapazzon
 */
        
public class ClienteUDP {

    public static void main(String[] args) throws Exception {
        
        Conexao selec = new Conexao();   
        selec.AbreSocket();
       //selec.SendMsg("100");
        
        int opcao;
        Scanner entrada = new Scanner(System.in);
        
        while(true){

            selec.menu();
            opcao = entrada.nextInt();

            switch (opcao) 
            {
                case 0:
                    selec.finalizar();
                    return;

                case 1:
                    selec.consultar();
                    break;

                case 2:
                    selec.incluir();
                    break;

                case 3:
                    selec.excluir();
                    break;

                case 4:
                    selec.alterar();
                    break;

                case 5:
                    selec.consultar_grupo();
                    break;

                case 6:
                    selec.consultar_aleatoria();
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}