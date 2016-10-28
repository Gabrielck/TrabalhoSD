/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancoInterface;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author gabrielkr
 */
public class Conexao {
    private static ServerSocket ss = null;
    
    public static Conexao aguardar_conexao(int porta) throws Exception {
        if(ss==null)
            ss = new ServerSocket(porta);
        return new Conexao(ss.accept());
    }
    
    public static Conexao conectar(String host, int porta) throws Exception {
        InetAddress ip = InetAddress.getByName(host);
        Socket soc = new Socket(ip, porta);
        return new Conexao(soc);
    }
    
    private Socket con;
    private ObjectOutputStream saida;
    private ObjectInputStream entrada;

    public Conexao(Socket con) throws Exception {
        this.con = con;
    }
    
    public void enviarPacote(PacoteBD pac) throws Exception {
        saida = new ObjectOutputStream(con.getOutputStream());
        saida.writeObject(pac);
        saida.flush();
    }
    
    public PacoteBD aguardarPacote() throws Exception {
        entrada = new ObjectInputStream(con.getInputStream());
        return (PacoteBD)entrada.readObject();
    }
    
    public void desconectar() throws Exception {
        con.close();
    }
    
    public String getName() throws Exception {
        return con.getInetAddress().getHostAddress();
    }
}
