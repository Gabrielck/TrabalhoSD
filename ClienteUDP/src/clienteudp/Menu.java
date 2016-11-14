
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
    System.out.println("4. Alterar");    //4#nro_pacote#mensagem#id_msg#id_tipo
    System.out.println("5. Consultar mensagens de um grupo");    //5#nro_pacote#id_tipo
    System.out.println("6. Consultar mensagem aleatória");   //6#nro_pacote#id_tipo
    System.out.println("7. Consultar tipos de mensagens");   
    System.out.println("Opcao:\n\n"); 
    }
    
    public void TipoMsg(){
        System.out.println("1 -> Motivação");    
        System.out.println("2 -> Felicitações");  
        System.out.println("3 -> Saudade");
        System.out.println("4 -> Superação de problemas");  
        System.out.println("5 -> Amor"); 
        System.out.println("6 -> Experiências de Vida");
        System.out.println("7 -> Caráter"); 
        System.out.println("8 -> Top (as mais requisitadas)\n\n");   
    }
}