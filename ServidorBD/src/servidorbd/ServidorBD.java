/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorbd;

import bancoInterface.Conexao;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author gabrielkr
 */
public class ServidorBD {

    private static int cli_porta = 8080; // Porta onde os clientes devem se conectar para acessar o banco

    public static void main(String[] args) throws Exception {
        System.out.println("Servidor de gerenciamento de conexões com o banco de dados");
            while (true) {
                Conexao conexao = Conexao.aguardar_conexao(cli_porta);
                ThreadConexao th = new ThreadConexao(conexao);
                th.run();
            }
    }

}
