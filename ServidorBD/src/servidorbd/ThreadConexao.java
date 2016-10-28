/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorbd;

import java.net.Socket;
import conexao.Conexao;
import conexao.PacoteBD;

/**
 *
 * @author gabrielkr
 */
public class ThreadConexao extends Thread {
    
    private Conexao conexao;

    public ThreadConexao(Conexao conexao) {
        this.conexao = conexao;
    }

    @Override
    public void run() {
        System.out.println("Conexão estabelecida");
        try {
            PacoteBD dados = conexao.aguardarPacote();
            PacoteBD retorno = null;
            switch(dados.getAcao())
            {
                case 1:
                    retorno = acao_inserir(dados);
                    break;
                case 2:
                    retorno = acao_alterar(dados);
                    break;
                case 3:
                    retorno = acao_excluir(dados);
                    break;
                case 4:
                    retorno = acao_consulta(dados);
                    break;
                case 5:
                    retorno = acao_lista_tipo(dados);
                    break;
                case 6:
                    retorno = acao_mensagem_aleatoria(dados);
                    break;
            }
            conexao.enviarPacote(retorno);
            conexao.desconectar();
        } catch(Exception e)  {
            System.out.println("Erro inesperado: "+e.getMessage());
        }
    }

    private PacoteBD acao_inserir(PacoteBD dados) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private PacoteBD acao_alterar(PacoteBD dados) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private PacoteBD acao_excluir(PacoteBD dados) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private PacoteBD acao_consulta(PacoteBD dados) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private PacoteBD acao_lista_tipo(PacoteBD dados) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private PacoteBD acao_mensagem_aleatoria(PacoteBD dados) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
