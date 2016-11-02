
package Threads;


import ClienteSOAP.ClienteUI;
import proxy.Controle;
import proxy.Frase;
import java.util.List;
import javax.swing.JOptionPane;

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
            JOptionPane.showMessageDialog(null, "Erro na operação lista-tipo");
            UI.setStatus("ERRO");
        }
        
        UI.setSaida("");
        
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
