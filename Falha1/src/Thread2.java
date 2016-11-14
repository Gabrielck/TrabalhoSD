
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
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
public class Thread2 extends Thread {

    private boolean flag;
    private int porta = 2007;
    private String adress;

    public void setafalse() {
        this.flag = false;
    }

    public void run() {
        System.out.println("Digite o número do servidor: ");
        Scanner entrada = new Scanner(System.in);
        adress = entrada.nextLine();

        while (true) {

            Comunica loco;
            loco = new Comunica(adress, porta);
            loco.start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Thread2.class.getName()).log(Level.SEVERE, null, ex);
            }

            this.flag = loco.getVerifica();
            if (this.flag == true) {
                continue;
            } else {
                System.out.println("Mata a Thread");
                //Thread.interrupt();
                DatagramSocket soc;
            DatagramPacket pct;
            InetAddress adress1;
            String msg;
            byte vet[] = new byte[100];
            msg = new String("O servidor caiu. Desculpe o transtorno :)!!");
            vet = msg.getBytes();
                try {
                    
                    soc = new DatagramSocket();
                    adress1 = InetAddress.getByName(this.adress);
                    
                    pct = new DatagramPacket(vet, vet.length, adress1, this.porta);
                    soc.send(pct);
                } catch (IOException iOException) {
                }
            }
        }
    }

}
