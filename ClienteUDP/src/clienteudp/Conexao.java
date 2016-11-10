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
    String codigo, cod;
    
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
   
    public void SendMsg (String msg) throws Exception{
        soc = new DatagramSocket();
        
        vet = msg.getBytes();
        adress = InetAddress.getLocalHost();
        
        pct = new DatagramPacket(vet, vet.length, adress, porta);
        soc.send(pct);   
     
        soc.close();  
    }
    public void consultar() throws Exception{
        codigo = "1#0#";
        
        System.out.println("Digite o código da mensagem a ser consultada:");
        
        Scanner input = new Scanner(System.in);
        
        msg = codigo + input.next();
        
        SendMsg(msg);
    }   
    
    public void incluir() throws Exception{
        codigo = "2#";
        soc = new DatagramSocket();
        int tamanho, qtde, inicio, fim;
        double aux;
        adress = InetAddress.getLocalHost();
        
        System.out.println("Digite a mensagem que deseja enviar:");
        Scanner input = new Scanner(System.in);
        msg = input.nextLine();
        System.out.println("Digite o código do tipo de mensagem:");
        Scanner input2 = new Scanner(System.in);
        cod = input2.nextLine();
        
        tamanho = msg.length();
        System.out.println("Tamanho da mensagem: " + (tamanho+4));
        
        aux = (tamanho+4) / 10;
        qtde = (int) aux + 1;
        System.out.println("Quantidade de pacotes a enviar: " + qtde + "\n\n"); 
        
        String teste = codigo + qtde;
        vet = teste.getBytes();
        
        pct = new DatagramPacket(vet, vet.length, adress, porta);
        soc.send(pct);
        
        String[] res = new String[qtde];
        inicio = 0;
        fim = 10;
        
        for(int i = 0 ; i < qtde ; i++){  
            if(i == (qtde - 1)){
                fim = tamanho;
            }
            res[i] = String.valueOf(msg.substring(inicio, fim));
            inicio = fim;
            fim =  fim + 10;
            String teste2 = codigo + i + "#" + res[i] + "#" + cod;
            
            System.out.println(teste2);
            
            vet = teste2.getBytes();
            adress = InetAddress.getLocalHost();

            pct = new DatagramPacket(vet, vet.length, adress, porta);
            soc.send(pct);
        }  
        soc.close();
    }
    
    public void excluir() throws Exception{
        codigo = "3#0#";
        
        System.out.println("Digite o código da mensagem a ser excluída:");
        
        Scanner input = new Scanner(System.in);
        
        msg = codigo + input.next();
        
        SendMsg(msg);
    }
    
    public void alterar() throws Exception{
        codigo = "4#";
        
        System.out.println("Digite a mensagem que deseja enviar:");
        
        Scanner input = new Scanner(System.in);
        
        msg = codigo + input.next();
        
        SendMsg(msg);
    }
    
    public void consultar_grupo() throws Exception{
        codigo = "5#";
        
        System.out.println("Digite o código do grupo a ser consultado (1-8):");
        
        SendMsg(msg);
    }
     
    public void consultar_aleatoria() throws Exception{
          
        codigo = "6#";

        System.out.println("Digite o código do grupo a ser consultado (1-8):");

        Scanner input = new Scanner(System.in);

        msg = codigo + input.next();

        SendMsg(msg);
    }
}
