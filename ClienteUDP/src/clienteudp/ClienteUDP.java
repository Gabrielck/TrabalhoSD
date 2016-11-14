
package clienteudp;

/**
 *
 * @author Jônatas Strapazzon
 */
        
public class ClienteUDP {

    public static void main(String[] args) throws Exception {
        
        Conexao selec = new Conexao();   
        selec.AbreSocket(); //Abre socket com o servidor para enviar os pacotes
          
        Menu op = new Menu();
        
        int opcao;
        
        op.menu();
        opcao = Integer.parseInt(args[0]); //Opção que será escolhida pelo usuário

        switch (opcao) //Chama função de acordo com o que o usuário digitou
        {
            case 0:
                selec.finalizar();
                return;

            case 1:
                selec.consultar(args[1]);
                break;

            case 2:
                selec.incluir(args[1], args[2]);
                break;

            case 3:
                selec.excluir(args[1]);
                break;

            case 4:
                selec.alterar(args[1], args[2], args[3]);
                break;

            case 5:
                selec.consultar_grupo(args[1]);
                break;

            case 6:
                selec.consultar_aleatoria(args[1]);
                break;

            case 7: 
                op.TipoMsg();
                break;

            default:
                System.out.println("Opção inválida!");
        }     
    }
}