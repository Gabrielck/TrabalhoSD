/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorudp;

import bancoInterface.BancoDeDados;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import servidorudp.SocketManager;

/**
 *
 * @author Matheus Lyra
 */
public class ServidorUDP {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
    SocketManager soc = new SocketManager();
    soc.AbreSocket(2006);
    
    while(true){
      
      //if(soc.GetMessage()) abrethread p/ montar mensagem
    }
    
    }
    
}
