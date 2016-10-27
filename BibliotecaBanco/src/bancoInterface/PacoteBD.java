package bancoInterface;


import bancoInterface.Frase;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gabrielkr
 */
class PacoteBD {
    private Integer acao;
    private String info;
    private Frase obj;

    public PacoteBD(Integer acao, String info, Frase obj) {
        this.acao = acao;
        this.info = info;
        this.obj = obj;
    }

    public Integer getAcao() {
        return acao;
    }

    public void setAcao(Integer acao) {
        this.acao = acao;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Frase getObj() {
        return obj;
    }

    public void setObj(Frase obj) {
        this.obj = obj;
    }
    
    
}
