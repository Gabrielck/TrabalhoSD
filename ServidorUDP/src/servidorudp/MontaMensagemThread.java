
package servidorudp;

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
        try {
            soc.AbreSocket(2006); 
            while(true)
             soc.GetMessage();
            
            //System.out.println("Teste Thread");
            
        } catch (Exception ex) {
            Logger.getLogger(MontaMensagemThread.class.getName()).log(Level.SEVERE, null, ex);
        }

       
        
    }
    
     
}




