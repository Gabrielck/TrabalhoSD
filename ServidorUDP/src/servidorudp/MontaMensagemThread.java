
package servidorudp;

import java.net.DatagramSocket;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matheus Lyra
 */
public class MontaMensagemThread extends Thread{
   
    public void run() {
        SocketManager soc = new SocketManager();
        DatagramSocket socr = null;
        try {
            socr = soc.AbreSocket(2006); 
            while(true)
             soc.GetMessage();
                        
        } catch (Exception ex) {
            if(socr != null)
               try {
                   
                   SocketManager.FechaSocket(socr);
                } catch (Exception ex1) {
                     Logger.getLogger(MontaMensagemThread.class.getName()).log(Level.SEVERE, null, ex1);
                }
            Logger.getLogger(MontaMensagemThread.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
}




