/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorbd;

import banco.Postgres;
import java.net.Socket;
import bancoInterface.Conexao;
import bancoInterface.Frase;
import bancoInterface.PacoteBD;
import java.sql.PreparedStatement;

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
            System.out.println("Nova conexao estabelecida com " + conexao.getName());
            PacoteBD dados = conexao.aguardarPacote();
            PacoteBD retorno = null;
            switch (dados.getAcao()) {
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
        } catch (Exception e) {
            System.out.println("!!! ERRO: " + e.getMessage());
        }
        try {
            conexao.desconectar();
        } catch (Exception e) {
        }
    }

    private PacoteBD acao_inserir(PacoteBD dados) throws Exception {
        Frase fr = dados.getObj()[0];
        System.out.println("Inseriu frase: " + fr.getFrase());
        PreparedStatement ps = Postgres.getSatement("insert into frases (frase,tipo) values (?,?)");
        ps.setString(1, fr.getFrase());
        ps.setInt(2, fr.getTipo());
        int success = Postgres.executeQueryUpdate(ps);
        return new PacoteBD(success,null,null);
    }

    private PacoteBD acao_alterar(PacoteBD dados) throws Exception {
        Frase fr = dados.getObj()[0];
        System.out.println("Alterou frase: " + fr.getId());
        PreparedStatement ps = Postgres.getSatement("update frases set frase = ?, tipo = ? where codigo = ?");
        ps.setString(1, fr.getFrase());
        ps.setInt(2, fr.getTipo());
        ps.setInt(3, fr.getId());
        int success = Postgres.executeQueryUpdate(ps);
        return new PacoteBD(success,null,null);
    }

    private PacoteBD acao_excluir(PacoteBD dados) throws Exception {
        Frase fr = dados.getObj()[0];
        System.out.println("Excluiu frase: " + fr.getId());
        PreparedStatement ps = Postgres.getSatement("delete from frases where codigo = ?");
        ps.setInt(1, fr.getId());
        int success = Postgres.executeQueryUpdate(ps);
        return new PacoteBD(success,null,null);
    }

    private PacoteBD acao_consulta(PacoteBD dados) throws Exception {
        Frase fr = dados.getObj()[0];
        System.out.println("Consultar frase: " + fr.getId());
        PreparedStatement ps = Postgres.getSatement("select * from frases where codigo = ?");
        ps.setInt(1, fr.getId());
        Frase[] f = Postgres.executeQueryFrase(ps);
        return new PacoteBD(null,null,f);
    }

    private PacoteBD acao_lista_tipo(PacoteBD dados) throws Exception {
        Frase fr = dados.getObj()[0];
        System.out.println("Consultar frase: tipo " + fr.getTipo());
        PreparedStatement ps = Postgres.getSatement("select * from frases where tipo = ?");
        ps.setInt(1, fr.getTipo());
        Frase[] f = Postgres.executeQueryFrase(ps);
        return new PacoteBD(null,null,f);
    }

    private PacoteBD acao_mensagem_aleatoria(PacoteBD dados) throws Exception {
        Frase fr = dados.getObj()[0];
        System.out.println("Consultar frase: tipo " + fr.getTipo());
        PreparedStatement ps = Postgres.getSatement("select * from frases where tipo = ? order by RANDOM() limit 1");
        ps.setInt(1, fr.getTipo());
        Frase[] f = Postgres.executeQueryFrase(ps);
        return new PacoteBD(null,null,f);
    }

}
