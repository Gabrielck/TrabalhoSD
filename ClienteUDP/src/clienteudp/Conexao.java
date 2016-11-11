/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteudp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author Jônatas Strapazzon
 */


public class Conexao {
    
    DatagramSocket soc;
    DatagramPacket pct;
        
    byte vet[];
    String msg,msg2;
    int porta = 2006;
    InetAddress adress;
    String id_menu, id_tipo, id_msg;
    
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
    
    public DatagramSocket AbreSocket() throws Exception{
        soc = new DatagramSocket();
        adress = InetAddress.getLocalHost();
        return soc;
    }
    
    public void SendMsg (String msg) throws Exception{
        vet = msg.getBytes();
        System.out.println("Teste-Enviando: "+new String(vet));
        
        pct = new DatagramPacket(vet, vet.length, adress, porta);
        soc.send(pct);   
    } 
    
    public void finalizar() throws Exception{ 
        id_menu = "0#0";
        
        SendMsg(id_menu);
        
        soc.close();
        
        System.out.println("Enviado: " + id_menu);
        System.out.println("Conexão finalizada!");
    }
    
    public void consultar() throws Exception{
        id_menu = "1#1";
        
        SendMsg(id_menu);
        
        System.out.println("Enviado: " + id_menu);
        
        System.out.println("Digite o código da mensagem a ser consultada:");
        
        Scanner input = new Scanner(System.in);
        
        msg = id_menu + "#" + input.next();
        
        System.out.println("Enviado: " + msg + "\n\n");

        SendMsg(msg);

    }   
    
    public void incluir() throws Exception{
        id_menu = "2#";
        int tamanho, qtde, inicio= 0, fim= 100;
        double aux;
        String id_aux;
        adress = InetAddress.getLocalHost();
        
        
        System.out.println("Digite a mensagem que deseja enviar:");
        Scanner input = new Scanner(System.in);
        msg = input.nextLine();
        System.out.println("Digite o código do tipo de mensagem: (1-8)");
        Scanner input2 = new Scanner(System.in);
        id_tipo = input2.nextLine();
        
        tamanho = msg.length();
        System.out.println("Tamanho da mensagem: " + (tamanho));
        
        aux = (tamanho) / 100;
        qtde = (int) aux + 1;
        System.out.println("Quantidade de pacotes a enviar: " + qtde); 
        
        id_aux = id_menu + qtde;
        System.out.println("Cabeçalho: " + id_aux);
        SendMsg(id_aux);
        
        String[] res = new String[qtde];

        //System.out.println("qtde: "+qtde);
        for(int i = 0 ; i < qtde ; i++){  
            if(i == (qtde - 1)){
                fim = tamanho;
            }
            //System.out.println("Fim: "+fim);
            res[i] = String.valueOf(msg.substring(inicio, fim));
            inicio = fim;
            fim =  fim + 100;
            msg2 = id_menu + (i+1) + "#" + res[i] + "#" + id_tipo;
            
            System.out.println("Enviado: " + msg2);
            
            SendMsg(msg2);
            
        }  
        System.out.println("\n\n");
    }
    
    public void excluir() throws Exception{
        id_menu = "3#1";
        
        System.out.println("Cabeçalho: " + id_menu);
        SendMsg(id_menu);
        
        System.out.println("Digite o código da mensagem a ser excluída:");
        
        Scanner input = new Scanner(System.in);
        
        msg = id_menu + "#" + input.next();
        
        System.out.println("Enviado: " + msg);
        
        SendMsg(msg);
        
        //resposta do servidor
//        pct = new DatagramPacket(vet, vet.length);
//        soc.receive(pct);
//        System.out.println("Voltou: " + new String(pct.getData()) );
    }
    
    public void alterar() throws Exception{
        id_menu = "4#";
        int tamanho, qtde, inicio= 0, fim= 100;
        double aux;
        String id_aux;
        adress = InetAddress.getLocalHost();
        
        System.out.println("Digite a mensagem que deseja enviar:");
        Scanner input = new Scanner(System.in);
        msg = input.nextLine();
        System.out.println("Digite o código do grupo que deseja alterar: (1-8)");
        Scanner input2 = new Scanner(System.in);
        id_tipo = input2.nextLine();
        System.out.println("Digite o código da mensagem que deseja alterar:");
        Scanner input3 = new Scanner(System.in);
        id_msg = input3.nextLine();
        
        tamanho = msg.length();
        System.out.println("Tamanho da mensagem: " + (tamanho));
        
        aux = (tamanho) / 100;
        qtde = (int) aux + 1;
        System.out.println("Quantidade de pacotes a enviar: " + qtde); 
        
        id_aux = id_menu + qtde;
        System.out.println("Cabeçalho: " + id_aux);
        SendMsg(id_aux);
        
        String[] res = new String[qtde];
        
        for(int i = 0 ; i < qtde ; i++){  
            if(i == (qtde - 1)){
                fim = tamanho;
            }
            //System.out.println("Fim: "+fim);
            res[i] = String.valueOf(msg.substring(inicio, fim));
            inicio = fim;
            fim =  fim + 100;
            msg2 = id_menu + (i+1) + "#" + res[i] + "#" + id_tipo + "#" + id_msg;
            
            System.out.println("Enviado: " + msg2);
            
            SendMsg(msg2);
        }  
    }
    
    public void consultar_grupo() throws Exception{
        id_menu = "5#1";
        
        System.out.println("Cabeçalho: " + id_menu);
        SendMsg(id_menu);
        
        System.out.println("Digite o código do grupo a ser consultado (1-8):");
        
        Scanner input = new Scanner(System.in);
        
        msg = id_menu + "#" + input.next();
        
        System.out.println("Enviado: " + msg);

        SendMsg(msg);
        
    }
     
    public void consultar_aleatoria() throws Exception{
        id_menu = "6#1";
        
        System.out.println("Cabeçalho: " + id_menu);
        SendMsg(id_menu);
        
        System.out.println("Digite o código do grupo a ser consultado (1-8):");

        Scanner input = new Scanner(System.in);

        msg = id_menu + "#" + input.next();
        
        System.out.println("Enviado: " + msg);

        SendMsg(msg);
    }
}
