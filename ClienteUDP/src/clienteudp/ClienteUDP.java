
package clienteudp;
import java.net.DatagramSocket;
import java.util.Scanner;

/**
 *
 * @author Jônatas Strapazzon
 */
        
public class ClienteUDP {

    public static void main(String[] args) throws Exception {
        
        Conexao selec = new Conexao();   
        selec.AbreSocket();
          
        Menu op = new Menu();
        
        int opcao;
        Scanner entrada = new Scanner(System.in);
        
        while(true){
            op.menu();
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