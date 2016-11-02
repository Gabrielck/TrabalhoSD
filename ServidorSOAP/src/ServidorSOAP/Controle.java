
package ServidorSOAP;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import bancoInterface.BancoDeDados;
import bancoInterface.Frase;
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
            BancoDeDados.consulta(id);
            BancoDeDados.alterar(id, mensagem, tipo);
        }
        catch(Exception E)
        {
            
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
            BancoDeDados.consulta(id);
            BancoDeDados.exluir(id);
        }
        catch(Exception E)
        {
            
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
             if(categoria < 1 || categoria > 8)
                 throw new Exception();
            return BancoDeDados.lista_tipo(categoria);
        }
        catch(Exception E)
        {
            
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
            if(categoria < 1 || categoria > 8)
                 throw new Exception();
            
            Frase[] frases = BancoDeDados.lista_tipo(categoria);
            Random R = new Random();
            int frasesl = frases.length;
            int aleatorio = R.nextInt(frases.length); 
            return frases[aleatorio];
        }
        catch(Exception E)
        {
            
            Frase f = new Frase();
            E.printStackTrace();
            f.setId(-1);
            return f;
        }
    }
}
