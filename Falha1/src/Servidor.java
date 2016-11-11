/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author rebonatto
 */
public class Servidor {
    public static void main(String[] args) throws Exception{
        DatagramSocket soc;
        DatagramPacket pct;
        InetAddress adress;
        byte vet[];
        String msg;
        int porta;
        
        
        System.out.println("Servidor");
        porta = 2006;
        soc = new DatagramSocket();
        msg = new String("Teste de falha PORRAAAAA!!");
        vet = new byte[100];
        vet = msg.getBytes();
        adress = InetAddress.getLocalHost();
        
        pct = new DatagramPacket(vet, vet.length, adress, porta);
        
        
        
        
        
//        
//        soc.receive(pct);
//        System.out.println("Recebeu Mensagem");
//        msg = new String(pct.getData());
//        System.out.println("Conteudo: " + msg);
//        
//        msg = msg.toUpperCase();
//        vet = new byte[100];
//        vet = msg.getBytes();
//        adress = pct.getAddress();
//        porta = pct.getPort();
//        pct = new DatagramPacket(vet, vet.length, adress, porta);
//        soc.send(pct);
//        soc.close();                
    }    
}
