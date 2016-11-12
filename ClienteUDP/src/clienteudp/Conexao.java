
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
    int porta = 2006;
    InetAddress adress;
    String msg, msg2, id_menu, id_tipo, id_msg;
    
    public DatagramSocket AbreSocket() throws Exception{
        soc = new DatagramSocket();
        adress = InetAddress.getLocalHost();
        return soc;
    }
    
    public void sendMsg (String msg) throws Exception{
        vet = msg.getBytes();

        pct = new DatagramPacket(vet, vet.length, adress, porta);
        soc.send(pct);   
    } 
    
    public void recivePartMsg (DatagramPacket pct) throws Exception{
        vet = new byte[110];
        pct = new DatagramPacket(vet, vet.length);
        
        while(true){        
            
            
            soc.receive(pct);
            
            int tam = 0, op, tipo;
            
            vet = pct.getData();
            
            System.out.println("Vet: " + new String(vet));
 
            if(new String(vet).contains("0#0"))
            {
                System.out.println("Resposta Servidor: Mensagem nula!" + "\n\n");
                break;
            }
            else{

                String[] vs = new String (vet).split("#");
           
                System.out.println("Quantidade de pacotes a receber: " + vs[1]);

                op  = Integer.parseInt(vs[0].trim());
                tam = Integer.parseInt(vs[1].trim());

                String fr[] = new String[tam]; // Frase de Retorno
                String vetorAbertura[] = new String[5]; // 5 é o tamanho máximo indices do vetor de opções        

                for(int i = 0; i < tam; i++)
                {
                    vet = new byte[115];
                    pct = new DatagramPacket(vet, vet.length);

                    soc.receive(pct);

                    vet = pct.getData();  
                    vetorAbertura = new String (vet).split("#");

                    if(Integer.parseInt(vetorAbertura[0].trim()) != op){
                        continue;
                    }

                    fr[(Integer.parseInt(vetorAbertura[1].trim()))-1] = vetorAbertura[2].trim(); //"-1" é porque é mandado o n° de partições e não a posição 
                    System.out.println("Vetor Abertura: " + fr[i]);
                }

                String fraseCompleta = new String();
                for(int i = 0; i < fr.length; i++)
                {
                fraseCompleta += fr[i];
                }

                System.out.println("Resposta do Servidor: " + fraseCompleta + "\n\n");
                break;
            }
        }
    }
    
    public void reciveMsg (DatagramPacket pct) throws Exception{
        vet = new byte[110];
        pct = new DatagramPacket(vet, vet.length);
         
        soc.receive(pct);

        vet = pct.getData();
            
        System.out.println("Resposta Servidor: " + new String(vet) + "\n\n");
    }
    
    public void finalizar() throws Exception{ 
        id_menu = "0#0";

        sendMsg(id_menu);

        soc.close();

        System.out.println("Enviado: " + id_menu);
        System.out.println("Conexão finalizada!");
    }
    
    public void consultar() throws Exception{
        id_menu = "1#1";

        sendMsg(id_menu);

        System.out.println("Enviado: " + id_menu);

        System.out.println("Digite o código da mensagem a ser consultada:");

        Scanner input = new Scanner(System.in);

        msg = id_menu + "#" + input.next();

        System.out.println("Enviado: " + msg + "\n\n");

        sendMsg(msg);

        //resposta do servidor
        recivePartMsg(pct);
    }   
    
    public void incluir() throws Exception{
        id_menu = "2#";      

        int tamanho, qtde, inicio= 0, fim= 100;
        double aux;
        String id_aux;   

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
        sendMsg(id_aux);

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

            sendMsg(msg2);
            }  
        System.out.println("\n\n");
    }

    public void excluir() throws Exception{
        id_menu = "3#1";

        System.out.println("Cabeçalho: " + id_menu);
        sendMsg(id_menu);

        System.out.println("Digite o código da mensagem a ser excluída:");

        Scanner input = new Scanner(System.in);

        msg = id_menu + "#" + input.next();

        System.out.println("Enviado: " + msg);

        sendMsg(msg);

        //resposta do servidor
        reciveMsg(pct);
    }
    
    public void alterar() throws Exception{
        id_menu = "4#";
        int tamanho, qtde, inicio= 0, fim= 100;
        double aux;
        String id_aux;

        System.out.println("Digite a mensagem que deseja enviar:");
        Scanner input = new Scanner(System.in);
        msg = input.nextLine();
        System.out.println("Digite o código da mensagem que deseja alterar:");
        Scanner input3 = new Scanner(System.in);
        id_msg = input3.nextLine();
        System.out.println("Digite o código do grupo que deseja alterar: (1-8)");
        Scanner input2 = new Scanner(System.in);
        id_tipo = input2.nextLine();

        tamanho = msg.length();
        System.out.println("Tamanho da mensagem: " + (tamanho));

        aux = (tamanho) / 100;
        qtde = (int) aux + 1;
        System.out.println("Quantidade de pacotes a enviar: " + qtde); 

        id_aux = id_menu + qtde;
        System.out.println("Cabeçalho: " + id_aux);
        sendMsg(id_aux);

        String[] res = new String[qtde];

        for(int i = 0 ; i < qtde ; i++){  
            if(i == (qtde - 1)){
                fim = tamanho;
            }
            //System.out.println("Fim: "+fim);
            res[i] = String.valueOf(msg.substring(inicio, fim));
            inicio = fim;
            fim =  fim + 100;
            msg2 = id_menu + (i+1) + "#" + res[i] + "#" + id_msg + "#" + id_tipo;

            System.out.println("Enviado: " + msg2);

            sendMsg(msg2);
            }

            //resposta do servidor
            reciveMsg(pct);
    }
    
    public void consultar_grupo() throws Exception{
        id_menu = "5#1";

        System.out.println("Cabeçalho: " + id_menu);
        sendMsg(id_menu);

        System.out.println("Digite o código do grupo a ser consultado (1-8):");

        Scanner input = new Scanner(System.in);

        msg = id_menu + "#" + input.next();

        System.out.println("Enviado: " + msg);

        sendMsg(msg);

        //resposta do servidor
        recivePartMsg(pct);
    }

    public void consultar_aleatoria() throws Exception{
        id_menu = "6#1";

        System.out.println("Cabeçalho: " + id_menu);
        sendMsg(id_menu);

        System.out.println("Digite o código do grupo a ser consultado (1-8):");

        Scanner input = new Scanner(System.in);

        msg = id_menu + "#" + input.next();

        System.out.println("Enviado: " + msg);

        sendMsg(msg);

        //resposta do servidor
        recivePartMsg(pct);
    }
}
