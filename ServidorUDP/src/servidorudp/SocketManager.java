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
 * @author Matheus Lyra
 */
public class SocketManager {
    
     DatagramPacket pacote;
     DatagramSocket socket;

    //--------------------------------------
    public DatagramSocket getSocket() {
        return socket;
    }

    public void setSocket(DatagramSocket socket) {
        this.socket = socket;
    }

    public DatagramPacket getPacote() {
        return pacote;
    }

    public void setPacote(DatagramPacket pacote) {
        this.pacote = pacote;
    }
    //----------------------------------------   
       
    public DatagramSocket AbreSocket(int porta) throws Exception{
        this.socket = new DatagramSocket(porta);
        return socket;
    }
    
    public DatagramPacket GetMessage() throws Exception{
        byte vet[] = new byte[100];
        this.pacote = new DatagramPacket(vet, vet.length);
        this.socket.receive(pacote);
        return pacote;
    }
    
    public void SendMessage(DatagramSocket socket, DatagramPacket pacote) throws Exception{
        // alterar tipo da variavel que recebe provavelmente para um objeto a ser definido com o lider 
        String mensagem = new String(pacote.getData());
        byte vet[] = new byte[100];
        
        vet = mensagem.getBytes();
        pacote = new DatagramPacket(vet, vet.length, pacote.getAddress(), pacote.getPort());
        socket.send(pacote);
        //socket.close();            
    }
}

