
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
public class Comunica extends Thread {
    private int porta;
    private String adress;
    private boolean verifica = false;
    
    public Comunica(String Adress, int porta)
    {
        this.porta = porta;
        this.adress = Adress;
    }
    
    public boolean getVerifica()
    {
        return this.verifica;
    }

    public void run(){
        
        
        Socket soc;
        ObjectOutputStream escreve;
        
        String msg;
        
        msg = new String("Teste!!");
        
        
        
        try {
            
            soc = new Socket(adress, porta);
            escreve = new ObjectOutputStream(soc.getOutputStream());
            escreve.writeObject(msg);
            
            escreve.flush();
            System.out.println("Mandou a mensagem");
            this.verifica = false;
            
            ObjectInputStream in = new ObjectInputStream(soc.getInputStream());
            try {
                
                String saida = (String) in.readObject();
                System.out.println("Recebeu a mensagem");
                this.verifica = true;
                System.out.println(saida);
                
            } catch (IOException iOException) {
            } catch (ClassNotFoundException classNotFoundException) {
            }
            
            soc.close();
            
            System.out.println("Encerrou o socket");
            
        } catch (IOException iOException) {
        }
        

    }

}
