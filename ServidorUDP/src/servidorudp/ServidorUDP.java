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
import servidorudp.SocketManeger;

/**
 *
 * @author Matheus Lyra
 */
public class ServidorUDP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
    SocketManeger socket = new SocketManeger();
    socket.AbreSocket(2006);
    }
    
}
