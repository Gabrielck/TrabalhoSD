
package servidorudp;

import bancoInterface.BancoDeDados;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import servidorudp.SocketManager;

/**
 *
 * @author Matheus Lyra
 */
public class ServidorUDP {

    /**
     * 
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        //BancoDeDados.inserir("lyra", 5);
        //SocketManager soc = new SocketManager();
        //soc.AbreSocket(2006);
        System.out.println("Servidor");
        MontaMensagemThread cliente = new MontaMensagemThread();
        cliente.start();

       // soc.GetMessage();
        //enviar mensagem com o retorno que vai vir da linha de cima
        //dividir mensagem antes de mandar (criar método pra isso)
    
    }
    
}
