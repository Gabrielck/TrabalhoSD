
package servidorudp;

import bancoInterface.Frase;
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
        socket = new DatagramSocket(porta);
        return socket;
    }
    
    public DatagramPacket GetMessage() throws Exception{
        byte vet[] = new byte[100];
        
        Frase frase = new Frase();

        packet = new DatagramPacket(vet, vet.length);
        
        while(true){                
            socket.receive(packet);
            
            if (packet != null){
                int tam, op, cont = 0;
                vet = packet.getData();
                
                op  = (int) vet[0];
                tam = (int) vet[1];
                byte fr[] = new byte[tam];
                
                for(int i = 0; i < tam; i++){
                    socket.receive(packet);
                    if(packet == null) cont++;
                    
                    vet = packet.getData();
                    fr[vet[0]] =(byte) ((vet[1]) << 100);
                }
                
                frase.setFrase(new String(fr));
                
                if(cont > 0){
                   return (DatagramPacket) null;
                }
                /*
                1 - consulta
                2 - inserção 
                3 - delete
                4 - alteracao
                5 - listar tipo
                6 - mensagem aleatoria
                */
                switch(op){ //operações do cliente com o banco
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    default:;
                       
                }
                
                
              // fazer laço pelo numero de vezes que vier na primeira mensagem e ja montar a string antes de chamar a thread
              // abre a thread e monta a mensagem
              return packet;
            }
        }

    }
    
    public void SendMessage(DatagramSocket socket, DatagramPacket pacote) throws Exception{
        String mensagem = new String(pacote.getData());
        byte vet[] = new byte[100];
        
        vet = mensagem.getBytes();
        pacote = new DatagramPacket(vet, vet.length, pacote.getAddress(), pacote.getPort());
        socket.send(pacote);
        //socket.close();            
    }
}

