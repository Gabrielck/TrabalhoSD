
package clienteudp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author Jônatas Strapazzon
 */


public class Conexao {
    
    DatagramSocket soc;
    DatagramPacket pct;   
    int porta = 2006;
    InetAddress adress;
    
    public DatagramSocket AbreSocket() throws Exception{
        soc = new DatagramSocket();
        adress = InetAddress.getByName("192.168.9.3");
        return soc;
    }
    
    //Função para enviar pacote para o servidor
    public void sendMsg (String msg) throws Exception{
        byte vet[];
        vet = msg.getBytes();

        pct = new DatagramPacket(vet, vet.length, adress, porta);
        soc.send(pct);   
    } 
    
    //Função para receber pacotes, se a frase for muito grande recebe vários pacotes
    public void recivePartMsg (DatagramPacket pct) throws Exception{
        while(true)
        {
            int tam;
            byte vet[] = null;
            
            vet = reciveMsg(pct); 
            
            if(new String(vet).contains("9#9"))
            {
                System.out.println("Perca de pacote!!" + "\n\n");
                break;     
            }
            else{  
                if(new String(vet).contains("0#0")) //Se o servidor mandar o código "0#0" no 1º pacote significa que a frase é nula
                {
                    System.out.println("Resposta Servidor: Mensagem nula!" + "\n\n");
                    break;
                }
                else
                {
                    String[] tamanho = new String (vet).split("#"); //Quebra o pacote onde tiver o símbolo "#" e armazena em um vetor de String

                    tam = Integer.parseInt(tamanho[1].trim()); //Para saber a quantidade de pacotes que virão     

                    String s = new String();
                    for(int i = 0; i < tam; i++)
                    {       
                        vet = reciveMsg(pct);
                        s += new String(vet).substring(4, (vet.length-2)); // -2 por causa do espaço no início e fim da string
                    }

                    //Mostra resposta do servidor com codificação UTF-8
                    System.out.println("Resposta do Servidor: " + new String(s.getBytes(), "UTF-8") + "\n\n");
                    break;
            }
            }
        }
    }
    
    //Função para item 5 do menu de opções (receber lista de mensagens de um tipo)
    public void reciveList (DatagramPacket pct) throws Exception{
        int tam;
        byte vet[];
        
        vet = reciveMsg(pct);
        String[] vs = new String (vet).split("#");
        
        tam = Integer.parseInt(vs[1].trim());
        
        for(int i = 0; i < tam; i++)
        {
            System.out.println("Item: " + (i+1));
            recivePartMsg(pct); //Chama a função para receber os pacotes de cada item da lista 
        }
        
    }
    
    //Função para receber um pacote
    public byte[] reciveMsg(DatagramPacket pct) throws Exception{
        byte vet[] = new byte[110];
        pct = new DatagramPacket(vet, vet.length);
         
        soc.receive(pct);

        vet = pct.getData();
            
        return vet;
    }
    
    //Função da operação finalizar 
    public void finalizar() throws Exception{ 
        String id_menu = "0#0#@@@"; //Código padrão enviado ao servidor 

        sendMsg(id_menu); //Envia pacote ao servidor
        
        soc.close(); //Fecha o socket

        System.out.println("Enviado: " + id_menu);
        System.out.println("Conexão finalizada!");
    }
    
    //Função da operação consultar
    public void consultar(String cod_msg) throws Exception{
        String msg, id_menu = "1#1#@@@";

        sendMsg(id_menu);
        System.out.println("Cabeçalho: " + id_menu);
        
        id_menu = "1#1";
  
        msg = id_menu + "#" + cod_msg;    

        sendMsg(msg);
        System.out.println("Enviado: " + msg + "\n\n");

        //resposta do servidor
        recivePartMsg(pct);
    }   
    
    //Função da operação incluir
    public void incluir(String cod_msg, String cod_tipo) throws Exception{
        String msg, msg2, id_tipo, id_aux, id_menu = "2#";      
        int tam, qtde, inicio= 0, fim= 100;
        double aux;  

        msg = cod_msg;
        id_tipo = cod_tipo;

        tam = msg.length();
        aux = (tam) / 100;
        qtde = (int) aux + 1; //Para saber a quantidade de pacotes que serão enviados ao servidor

        id_aux = id_menu + qtde + "#@@@";
        
        sendMsg(id_aux);
        System.out.println("Cabeçalho: " + id_aux);
        
        String[] res = new String[qtde];
        for(int i = 0 ; i < qtde ; i++) //Se a mensagem for muito grande ela é quebrada em vários pacotes e enviados 1 a 1
        {  
            if(i == (qtde - 1))
            {
                fim = tam;
            }          
            res[i] = String.valueOf(msg.substring(inicio, fim));
            inicio = fim;
            fim =  fim + 100; //Tamanho dos pacotes é limitado a 100 caracteres para a parte da mensagem, o restante são para os caracteres especiais enviados como código padrão ao servidor
            msg2 = id_menu + (i+1) + "#" + res[i] + "#" + id_tipo;

            sendMsg(msg2);
            System.out.println("Enviado: " + msg2);
        } 
    }
    
    //Função da operação excluir
    public void excluir(String cod_msg) throws Exception{
        String msg, id_menu = "3#1#@@@";
        byte vet[];

        sendMsg(id_menu);
        System.out.println("Cabeçalho: " + id_menu);
        
        id_menu = "3#1";

        msg = id_menu + "#" + cod_msg;

        sendMsg(msg);
        System.out.println("Enviado: " + msg);
        
        //resposta do servidor
        vet = reciveMsg(pct);
        System.out.println("Resposta Servidor: " + new String(vet) + "\n\n");
    }
    
    //Função da operação alterar
    public void alterar(String mensagem, String cod_msg, String cod_tipo) throws Exception{
        String msg, msg2, id_msg, id_tipo, id_aux, id_menu = "4#";
        int tam, qtde, inicio= 0, fim= 100;
        double aux;
        byte vet[];

        msg = mensagem;
        id_msg = cod_msg;
        id_tipo = cod_tipo;

        tam = msg.length();
        aux = (tam) / 100;
        qtde = (int) aux + 1;
        
        id_aux = id_menu + qtde + "#@@@";
        
        sendMsg(id_aux);
        System.out.println("Cabeçalho: " + id_aux);
        
        String[] res = new String[qtde];
        for(int i = 0 ; i < qtde ; i++)
        {  
            if(i == (qtde - 1))
            {
                fim = tam;
            }
            res[i] = String.valueOf(msg.substring(inicio, fim));
            inicio = fim;
            fim =  fim + 100;
            msg2 = id_menu + (i+1) + "#" + res[i] + "#" + id_msg + "#" + id_tipo;

            sendMsg(msg2);
            System.out.println("Enviado: " + msg2);
        }

        //resposta do servidor
        vet = reciveMsg(pct);
        System.out.println("Resposta Servidor: " + new String(vet) + "\n\n");
    }
    
    //Função da operação consultar mensagens de um grupo/tipo
    public void consultar_grupo(String id_tipo) throws Exception{
        String msg, id_menu = "5#1#@@@";

        sendMsg(id_menu);
        System.out.println("Cabeçalho: " + id_menu);
        
        id_menu = "5#1";

        msg = id_menu + "#" + id_tipo;

        sendMsg(msg);
        System.out.println("Enviado: " + msg);
        
        //resposta do servidor
        reciveList(pct);
    }
    
    //Função da operação consultar mensagem aleatória
    public void consultar_aleatoria(String id_tipo) throws Exception{
        String msg, id_menu = "6#1#@@@";

        sendMsg(id_menu);
        System.out.println("Cabeçalho: " + id_menu);
        
        id_menu = "6#1";

        msg = id_menu + "#" + id_tipo;

        sendMsg(msg);
        System.out.println("Enviado: " + msg);

        //resposta do servidor
        recivePartMsg(pct);
    }
}