
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
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
        System.out.println(adress);

        while (true) {

            Comunica loco;
            loco = new Comunica(adress, porta);
            loco.start();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Thread2.class.getName()).log(Level.SEVERE, null, ex);
            }

            this.flag = loco.getVerifica();
            if (this.flag == true) {
                continue;
            } else {
                System.out.println("Thread1 esta morta");
            Socket soc;

            ObjectOutputStream escreve;
        
            String msg;
        
            msg = new String("O servidor está fora. Desculpe o transtorno :)!!");

                try {
                    // AQUI VAI O IP DO CLIENTE !!!!!!!
                    soc = new Socket("192.168.9.4", porta-1);
                    
                    escreve = new ObjectOutputStream(soc.getOutputStream());
                    
                    escreve.writeObject(msg);

                    escreve.flush();
                    
                    System.out.println("Enviou a mensagem ao cliente");
                    
                    soc.close();
                } catch (IOException e) {
                    //e.printStackTrace();
                }
                //System.out.println("Saiu do programa");
                //System.exit(0);
            }
        }
    }

}
