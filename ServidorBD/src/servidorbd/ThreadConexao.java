/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorbd;

import java.net.Socket;
import conexao.Servidor;

/**
 *
 * @author gabrielkr
 */
public class ThreadConexao extends Thread {
    
    private Servidor conexao;

        public ThreadConexao(Servidor conexao) {
        this.conexao = conexao;
    }

    @Override
    public void run() {
        System.out.println("Conexão estabelecida");
        throw new UnsupportedOperationException("Não implementado.");
    }
    
}
