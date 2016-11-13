
package servidorudp;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author rebonatto
 */
public class Cliente2 {
    public static void main(String[] args) throws Exception{
        Socket soc;
        ObjectOutputStream escreve;
        String host = new String("localhost");
        //String host = new String("192.168.9.4");
        String msg = new String("Não!!!");
        int porta = 2007;
        System.out.println("Cliente");
        while(true){
        soc = new Socket(host, porta);
        escreve = new ObjectOutputStream(soc.getOutputStream());
        escreve.writeObject(msg);
        escreve.flush();
        
        ObjectInputStream recebe = new ObjectInputStream(soc.getInputStream());
        msg = (String) recebe.readObject();
        System.out.println("Recebido: " + msg);
        
        soc.close();
        }
    }
}
