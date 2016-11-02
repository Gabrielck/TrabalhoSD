
package Threads;


import ClienteSOAP.ClienteUI;
import proxy.Controle;
import proxy.Frase;
import com.sun.istack.internal.FragmentContentHandler;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadLista extends Thread 
{
    ClienteUI UI;
    Controle control;
    int categoria;
    
    public ThreadLista(int i, ClienteUI U, Controle c)
    {
        this.UI = U;
        control = c;
        categoria = i;
    }
    
    public void run()
    {
        UI.bloquear(false);
        try {
            sleep(3000);
        } catch (InterruptedException ex) {
            //Logger.getLogger(ThreadConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Frase> f = control.listaTipo(categoria).getItem();
        if(f.size() == 0)
        {
            UI.setStatus("ERRO");
        }
        
        for(int k = 0; k < f.size(); k++)
        {
            Frase temp = f.get(k);
            UI.adicionarSaida(temp.getFrase());
            UI.adicionarSaida("\n-----------\n");
        }
        UI.setStatus("OK");
        UI.bloquear(true);
    }
}
