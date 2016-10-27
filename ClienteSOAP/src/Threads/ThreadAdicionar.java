
package Threads;


import ClienteSOAP.ClienteUI;
import ClienteSOAP.Controle;
import ClienteSOAP.Frase;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadAdicionar extends Thread 
{
    ClienteUI UI;
    Controle control;
    Frase frase;
    
    public ThreadAdicionar(Frase f, ClienteUI U, Controle c)
    {
        this.UI = U;
        control = c;
        frase = f;
    }
    
    public void run()
    {
        UI.bloquear(false);
        try {
            sleep(3000);
        } catch (InterruptedException ex) {
            //Logger.getLogger(ThreadConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean f = control.adicionar(frase.getFrase(), frase.getTipo());
        if(f)
        {
            UI.setStatus("OK");
        }
        else
            UI.setStatus("ERRO");
        UI.bloquear(true);
    }
}
