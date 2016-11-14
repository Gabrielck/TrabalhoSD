
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
        //adress = InetAddress.getByName("192.168..");
        adress = InetAddress.getLocalHost();
        return soc;
    }
    
    public void sendMsg (String msg) throws Exception{
        byte vet[];
        vet = msg.getBytes();

        pct = new DatagramPacket(vet, vet.length, adress, porta);
        soc.send(pct);   
    } 
    
    public void recivePartMsg (DatagramPacket pct) throws Exception{
        while(true)
        {
            int tam;
            byte vet[];
            
            vet = reciveMsg(pct); 
            
            if(new String(vet).contains("0#0"))
            {
                System.out.println("Resposta Servidor: Mensagem nula!" + "\n\n");
                break;
            }
            else{
                String[] tamanho = new String (vet).split("#");
                
                tam = Integer.parseInt(tamanho[1].trim());
                
                String frase[] = new String[5]; // 5 é o tamanho máximo indices do vetor de opções        
                
                String s = new String();
                for(int i = 0; i < tam; i++)
                {       
                    vet = reciveMsg(pct);
                    frase = new String (vet).split("#");
                    s += frase[2].substring(0, (frase[2].length()-1));            
                }

                System.out.println("Resposta do Servidor: " + new String(s.getBytes(), "UTF-8") + "\n\n");
                break;
            }
        }
    }
    
    public void reciveList (DatagramPacket pct) throws Exception{
        int tam;
        byte vet[];
        
        vet = reciveMsg(pct);
        String[] vs = new String (vet).split("#");
        
        tam = Integer.parseInt(vs[1].trim());
        
        for(int i = 0; i < tam; i++)
        {
            System.out.println("Item: " + (i+1));
            recivePartMsg(pct);
        }
        
    }
    
    public byte[] reciveMsg(DatagramPacket pct) throws Exception{
        byte vet[] = new byte[110];
        pct = new DatagramPacket(vet, vet.length);
         
        soc.receive(pct);

        vet = pct.getData();
            
        return vet;
    }
    
    public void finalizar() throws Exception{ 
        String id_menu = "0#0#@@@";

        sendMsg(id_menu);
        
        soc.close();

        System.out.println("Enviado: " + id_menu);
        System.out.println("Conexão finalizada!");
    }
    
    public void consultar(String cod_msg) throws Exception{
        String msg, id_menu = "1#1#@@@";

        sendMsg(id_menu);
        System.out.println("Enviado: " + id_menu);
        
        id_menu = "1#1";
  
        msg = id_menu + "#" + cod_msg;    

        sendMsg(msg);
        System.out.println("Enviado: " + msg + "\n\n");

        //resposta do servidor
        recivePartMsg(pct);
    }   
    
    public void incluir(String cod_msg, String cod_tipo) throws Exception{
        String msg, msg2, id_tipo, id_aux, id_menu = "2#";      
        int tam, qtde, inicio= 0, fim= 100;
        double aux;  

        msg = cod_msg;
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
            msg2 = id_menu + (i+1) + "#" + res[i] + "#" + id_tipo;

            sendMsg(msg2);
            System.out.println("Enviado: " + msg2);
        } 
    }

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