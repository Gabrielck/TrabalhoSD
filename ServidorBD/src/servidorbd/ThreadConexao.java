/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorbd;

import java.net.Socket;
import bancoInterface.Conexao;
import bancoInterface.Frase;
import bancoInterface.PacoteBD;

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
        try {
            System.out.println("Nova conexao estabelecida com "+conexao.getName());
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
            System.out.println("!!! ERRO: "+e.getMessage());
        }
    }

    private PacoteBD acao_inserir(PacoteBD dados) {
        System.out.println("Inseriu frase: "+dados.getObj()[0].getFrase());
        return null;
    }

    private PacoteBD acao_alterar(PacoteBD dados) {
        System.out.println("Alterou frase: "+dados.getObj()[0].getId());
        return null;
    }

    private PacoteBD acao_excluir(PacoteBD dados) {
        return null;
    }

    private PacoteBD acao_consulta(PacoteBD dados) {
        System.out.println("Consultar frase: "+dados.getObj()[0].getId());
        Frase[] f = new Frase[1];
        f[0] = new Frase(5,"Frase no banco",1);
        PacoteBD pac = new PacoteBD();
        pac.setObj(f);
        return pac;
    }

    private PacoteBD acao_lista_tipo(PacoteBD dados) {
        System.out.println("Consultar frase: tipo "+dados.getObj()[0].getTipo());
        Frase[] f = new Frase[2];
        f[0] = new Frase(5,"Frase no banco 1",1);
        f[1] = new Frase(6,"Frase no banco 2",1);
        PacoteBD pac = new PacoteBD();
        pac.setObj(f);
        return pac;
    }

    private PacoteBD acao_mensagem_aleatoria(PacoteBD dados) {
        System.out.println("Consultar frase: tipo "+dados.getObj()[0].getTipo());
        Frase[] f = new Frase[2];
        f[0] = new Frase(1,"Mensagem ALEATORIA",1);
        PacoteBD pac = new PacoteBD();
        pac.setObj(f);
        return pac;
    }

}
