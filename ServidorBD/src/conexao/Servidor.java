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
    
    /**
     * Estabelece comunicação com o servid
     * @param porta
     * @return
     * @throws Exception 
     */
    public static Socket aguardar_conexao(int porta) throws Exception {
        ServerSocket ss;
        ss = new ServerSocket(porta);
        return ss.accept();
    }
}
