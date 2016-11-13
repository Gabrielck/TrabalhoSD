
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
    byte vet[];
    int porta = 2006;
    InetAddress adress;
    String msg, msg2, id_menu, id_tipo, id_msg, id_aux;
    
    public DatagramSocket AbreSocket() throws Exception{
        soc = new DatagramSocket();
        //adress = InetAddress.getByName("192.168..");
        adress = InetAddress.getLocalHost();
        return soc;
    }
    
    public void sendMsg (String msg) throws Exception{
        vet = msg.getBytes();

        pct = new DatagramPacket(vet, vet.length, adress, porta);
        soc.send(pct);   
    } 
    
    public void recivePartMsg (DatagramPacket pct) throws Exception{
        while(true){
            int tam;
            
            vet = reciveMsg(pct); 
            
            if(new String(vet).contains("0#0"))
            {
                System.out.println("Resposta Servidor: Mensagem nula!" + "\n\n");
                break;
            }
            else{
                String[] vs = new String (vet).split("#");
                
                tam = Integer.parseInt(vs[1].trim());

                String fr[] = new String[tam]; // Frase de Retorno
                String vetorAbertura[] = new String[5]; // 5 é o tamanho máximo indices do vetor de opções        

                for(int i = 0; i < tam; i++)
                {                  
                    vet = reciveMsg(pct);
                    vetorAbertura = new String (vet).split("#");

                    fr[(Integer.parseInt(vetorAbertura[1].trim()))-1] = vetorAbertura[2].trim(); //"-1" é porque é mandado o n° de partições e não a posição 
                }

                String fraseCompleta = new String();
                for(int i = 0; i < fr.length; i++)
                {
                    fraseCompleta += fr[i];
                }

                System.out.println("Resposta do Servidor: " + new String(fraseCompleta.getBytes(), "UTF-8") + "\n\n");
                break;
            }
        }
    }
    
    public void reciveList (DatagramPacket pct) throws Exception{
        int tam;

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
        vet = new byte[110];
        pct = new DatagramPacket(vet, vet.length);
         
        soc.receive(pct);

        vet = pct.getData();
            
        return vet;
    }
    
    public void finalizar() throws Exception{ 
        id_menu = "0#0#@@@";

        sendMsg(id_menu);
        
        soc.close();

        System.out.println("Enviado: " + id_menu);
        System.out.println("Conexão finalizada!");
    }
    
    public void consultar(String cod_msg) throws Exception{
        id_menu = "1#1#@@@";

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
        id_menu = "2#";      

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
        for(int i = 0 ; i < qtde ; i++){  
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
        id_menu = "3#1#@@@";

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
        id_menu = "4#";
        
        int tam, qtde, inicio= 0, fim= 100;
        double aux;

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
        for(int i = 0 ; i < qtde ; i++){  
            if(i == (qtde - 1)){
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
        reciveMsg(pct);
        System.out.println("Resposta Servidor: " + new String(vet) + "\n\n");
    }
    
    public void consultar_grupo(String id_tipo) throws Exception{
        id_menu = "5#1#@@@";

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
        id_menu = "6#1#@@@";

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