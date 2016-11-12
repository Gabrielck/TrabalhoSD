
package clienteudp;

/**
 *
 * @author Jônatas Strapazzon
 */
public class Menu {
    
    public void menu(){
    System.out.println("\tCliente UDP"); //id_menu#qntd_pacotes
    System.out.println("0. Sair");       
    System.out.println("1. Consultar");  //1#nro_pacote#id_msg
    System.out.println("2. Incluir");    //2#nro_pacote#mensagem#id_tipo
    System.out.println("3. Excluir");    //3#nro_pacote#id_msg
    System.out.println("4. Alterar");    //4#nro_pacote#mensagem#id_tipo#id_msg
    System.out.println("5. Consultar mensagens de um grupo");    //5#nro_pacote#id_tipo
    System.out.println("6. Consultar mensagem aleatória");   //6#nro_pacote#id_tipo
    System.out.println("Opcao:"); 
    }
}