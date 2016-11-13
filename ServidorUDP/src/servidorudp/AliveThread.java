
package servidorudp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matheus Lyra
 */
public class AliveThread extends Thread{
   
    public void run() {

        ServerSocket ss;
        Socket soc;
        ObjectInputStream recebe;
        String msg = new String();
        int porta = 2007;
        System.out.println("Servidor");
        try {
            ss = new ServerSocket(porta);       
            
            while(true){
                soc = ss.accept();
                System.out.println("Aguardando conexoes");
                ObjectOutputStream escreve = new ObjectOutputStream(soc.getOutputStream());
                escreve.writeObject("A'm Alive");
                System.out.println("    A'm Alive");
                escreve.flush();  
                Thread.sleep(5000);
            }
            
                //soc.close();
      
        } catch (IOException ex) {
            Logger.getLogger(AliveThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(AliveThread.class.getName()).log(Level.SEVERE, null, ex);
        }



    }
    
     
}