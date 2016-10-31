
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
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
    SocketManager soc = new SocketManager();
    soc.AbreSocket(2006);
    
    soc.GetMessage();
    
    }
    
}
