
package servidorudp;

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
/*
        ServerSocket ss;
        Socket soc;
        ObjectInputStream recebe;
        String msg = new String();
        int porta = 2006;
        System.out.println("Servidor");
        ss = new ServerSocket(porta);
        System.out.println("Aguardando conexoes");
        soc = ss.accept();
        recebe = new ObjectInputStream(soc.getInputStream());
        msg = (String) recebe.readObject();
        System.out.println("Recebido: " + msg);
                
        ObjectOutputStream escreve = new ObjectOutputStream(soc.getOutputStream());
        escreve.writeObject(msg.toUpperCase());
        escreve.flush();
        
        soc.close();*/


    }
    
     
}