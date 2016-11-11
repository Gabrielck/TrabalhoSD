/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servidorudp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author rebonatto
 */
public class Cliente1 {
    public static void main(String[] args) throws Exception{
        DatagramSocket soc;
        DatagramPacket pct;
        byte vet[];
        String msg;
        int porta = 2006;
        InetAddress adress;
        
        System.out.println("Cliente");
        soc = new DatagramSocket();
        msg = new String("1#1");//consulta
        //msg = new String("2#1");//inserção
        //msg = new String("3#1");//delete
        //msg = new String("4#1");  //alterção        
        //msg = new String("5#1");  //listar tipo
        //msg = new String("#1");  // aleatória
        vet = msg.getBytes();
        adress = InetAddress.getLocalHost();
        pct = new DatagramPacket(vet, vet.length, adress, porta);
        soc.send(pct);
        System.out.println("Enviou mensagem");
       
        System.out.println("Cliente");
        soc = new DatagramSocket();
        msg = new String("1#1#118");//consulta //consulta  // 1#nroPacote#codfrase
        //msg = new String("2#1#TESTE LYRA 2#5");//inserção  // 2#nroPacote#frase#tipo 
        //msg = new String("3#1#121");             //delete  // 3#nroPacote#codfrase
        //msg = new String("4#1#teste alteração#122#5"); //alteração    // 4#nroPacote#frase#codfrase#tipo
        //msg = new String("5#1#5"); //listar tipo    // 5#nroPacote#tipo 
        //msg = new String("6#1#0"); //aleatoria      // 6#nroPacote#tipo
        vet = msg.getBytes();
        adress = InetAddress.getLocalHost();
        pct = new DatagramPacket(vet, vet.length, adress, porta);
        soc.send(pct);
        System.out.println("Enviou mensagem");
        
       /* pct = new DatagramPacket(vet, vet.length);
        soc.receive(pct);
        System.out.println("Voltou: " + new String(pct.getData()) );*/
        //soc.close();
    }    
}
