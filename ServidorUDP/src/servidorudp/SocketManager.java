
package servidorudp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author Matheus Lyra
 */
public class SocketManager {
    
     DatagramPacket packet;
     DatagramSocket socket;

    public DatagramSocket AbreSocket(int porta) throws Exception{
        this.socket = new DatagramSocket(porta);
        return socket;
    }
    
    public DatagramPacket GetMessage() throws Exception{
        byte vet[] = new byte[100];
        packet = new DatagramPacket(vet, vet.length);
        
        while(true){                
            socket.receive(packet);
            if (packet != null){
              // abre a thread e monta a mensagem
              return packet;
            }
        }

    }
    
    public void SendMessage(DatagramSocket socket, DatagramPacket pacote) throws Exception{
        // alterar tipo da variavel que recebe provavelmente para um objeto a ser definido com o lider 
        String mensagem = new String(pacote.getData());
        byte vet[] = new byte[100];
        
        vet = mensagem.getBytes();
        pacote = new DatagramPacket(vet, vet.length, pacote.getAddress(), pacote.getPort());
        socket.send(pacote);
        //socket.close();            
    }
}

