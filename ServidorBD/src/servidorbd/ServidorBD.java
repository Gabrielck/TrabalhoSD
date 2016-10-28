/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorbd;

import conexao.Conexao;
import java.net.Socket;

/**
 *
 * @author gabrielkr
 */
public class ServidorBD {
    
    private static int cli_porta = 2006; // Porta onde os clientes devem se conectar para acessar o banco

    public static void main(String[] args) {
        System.out.println("Servidor de gerenciamento de conexões com o banco de dados");
        while(true)
        {
            try {
                Conexao conexao = Conexao.aguardar_conexao(cli_porta);
                ThreadConexao th = new ThreadConexao(conexao);
                th.run();
            } catch(Exception e) {
                System.out.println("Erro inesperado: "+e.getMessage());
            }
        }
    }
    
}
