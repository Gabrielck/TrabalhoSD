
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public Comunica(String Adress, int porta)
    {
        this.porta = porta;
        this.adress = Adress;
    }
    

    public void run(){
        
        
        DatagramSocket soc;
        DatagramPacket pct;
        InetAddress adress1;
        String msg;
        byte vet[] = new byte[100];
        msg = new String("Teste de falha PORRAAAAA!!");
        vet = msg.getBytes();
        
        
        try {
            boolean verifica = false;
            soc = new DatagramSocket();
            adress1 = InetAddress.getByName(this.adress);
            
            
            pct = new DatagramPacket(vet, vet.length, adress1, this.porta);
            
            while (true) {
                System.out.println("Enviou o pacote");
                soc.send(pct);
                verifica = false;
                soc.receive(pct);
                verifica = true;
                Thread.sleep(3000);
                System.out.println("Recebeu o pacote");
                soc.close();
                System.out.println("Encerrou o socket");
            }
        } catch (IOException iOException) {
        } catch (InterruptedException interruptedException) {
        }
        

    }

}
