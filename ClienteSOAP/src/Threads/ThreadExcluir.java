
package Threads;


import ClienteSOAP.ClienteUI;
import ClienteSOAP.Controle;
import ClienteSOAP.Frase;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadExcluir extends Thread 
{
    ClienteUI UI;
    Controle control;
    int id;
    
    public ThreadExcluir(int i, ClienteUI U, Controle c)
    {
        this.UI = U;
        control = c;
        id = i;
    }
    
    public void run()
    {
        UI.bloquear(false);
        try {
            sleep(3000);
        } catch (InterruptedException ex) {
            //Logger.getLogger(ThreadConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean f = control.excluir(id);
        if(f)
        {
            UI.setStatus("OK");
        }
        else
            UI.setStatus("ERRO");
        UI.bloquear(true);
    }
}
