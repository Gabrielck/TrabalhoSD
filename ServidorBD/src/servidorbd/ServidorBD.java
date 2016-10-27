/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorbd;

import conexao.Servidor;
import java.net.Socket;

/**
 *
 * @author gabrielkr
 */
public class ServidorBD {
    
    private static int cli_porta = 2006; // Porta onde os clientes devem se conectar para acessar o banco

    public static void main(String[] args) {
        while(true)
        {
            try {
            Socket conexao = Servidor.aguardar_conexao(cli_porta);
            } catch(Exception e) {
                System.out.println("Erro inesperado: "+e.getMessage());
            }
        }
    }
    
}
