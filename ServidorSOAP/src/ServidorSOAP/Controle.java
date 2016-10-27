
package ServidorSOAP;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import BD.BancoDeDados;
import BD.Frase;
import java.util.Random;
import javax.swing.JOptionPane;

@WebService(endpointInterface = "ServidorSOAP.Controle")
@SOAPBinding(style = SOAPBinding.Style.RPC)

public class Controle {
    @WebMethod
    public Frase Consultar(int id)
    {
        Frase f = new Frase();
        try
        {
            f = BancoDeDados.consulta(id);
        }
        catch (Exception E)
        {
            JOptionPane.showMessageDialog(null, "Erro em consulta");
            E.printStackTrace();
            f.setId(-1);
            return f;
        }
        return f;
    }
    
    @WebMethod
    public boolean adicionar(String mensagem, int tipo)
    {
        try
        {
            BancoDeDados.inserir(mensagem, tipo);
        }
        catch(Exception E)
        {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar");
            E.printStackTrace();
            return false;
        }
        
        return true;
    }
    
    @WebMethod
    public boolean alterar(int id, String mensagem, int tipo)
    {
        try
        {
            BancoDeDados.alterar(id, mensagem, tipo);
        }
        catch(Exception E)
        {
            JOptionPane.showMessageDialog(null, "Erro ao alterar");
            E.printStackTrace();
            return false;
        }
        return true;
    }
    
    @WebMethod
    public boolean excluir(int id)
    {
        try
        {
            BancoDeDados.exluir(id);
        }
        catch(Exception E)
        {
            JOptionPane.showMessageDialog(null, "Erro ao excluir");
            E.printStackTrace();
            return false;
        }
        return true;
    }
    
    @WebMethod
    public Frase[] listaTipo(int categoria)
    {
        try
        {
             
            return BancoDeDados.lista_tipo(categoria);
        }
        catch(Exception E)
        {
            JOptionPane.showMessageDialog(null, "Erro na operação lista-tipo");
            E.printStackTrace();
            Frase[]f = new Frase[0];
            return f; 
        }
    }
    
    @WebMethod
    public Frase mensagemAleatoria(int categoria)
    {
        try
        {
            Frase[] frases = BancoDeDados.lista_tipo(categoria);
            Random R = new Random();
            return frases[R.nextInt(frases.length)];
        }
        catch(Exception E)
        {
            JOptionPane.showMessageDialog(null, "Erro na operação mensagem aleatória");
            Frase f = new Frase();
            E.printStackTrace();
            f.setId(-1);
            return f;
        }
    }
}
