
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

    public void ThreadServer(String adress, int porta) throws UnknownHostException, SocketException, IOException {
        DatagramSocket soc;
        DatagramPacket pct;
        InetAddress adress1;
        String msg;
        byte vet[] = new byte[100];
        msg = new String("Teste de falha PORRAAAAA!!");
        vet = msg.getBytes();

        soc = new DatagramSocket();
        adress1 = InetAddress.getByName(adress);

        try {
            soc = new DatagramSocket();
        } catch (SocketException ex) {
            System.out.println("Não conseguiu criar o socket");
        }

        pct = new DatagramPacket(vet, vet.length, adress1, porta);

        while (true) {
            soc.send(pct);
            soc.receive(pct);
        }
    }

}
