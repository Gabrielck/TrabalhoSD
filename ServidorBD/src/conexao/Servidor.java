/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author gabrielkr
 */
public class Servidor {
    
    public static Servidor aguardar_conexao(int porta) throws Exception {
        ServerSocket ss;
        ss = new ServerSocket(porta);
        return new Servidor(ss.accept());
    }
    
    private Socket con;

    public Servidor(Socket con) {
        this.con = con;
    }
    
    public void enviarPacote(PacoteBD pac) {
        
    }
    
    public PacoteBD aguardarPacote() {
        return null;
    }
    
    public void desconectar() {
        
    }
    
}
