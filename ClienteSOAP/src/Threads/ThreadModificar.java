
package Threads;


import ClienteSOAP.ClienteUI;
import proxy.Controle;
import proxy.Frase;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadModificar extends Thread 
{
    ClienteUI UI;
    Controle control;
    String frase;
    int id;
    int tipo;
    
    public ThreadModificar(String s,int i, int ti, ClienteUI U, Controle c)
    {
        this.UI = U;
        control = c;
        frase = s;
        id = i;
        tipo = ti;
    }
    
    public void run()
    {
        UI.bloquear(false);
        try {
            sleep(3000);
        } catch (InterruptedException ex) {
            //Logger.getLogger(ThreadConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean f = control.alterar(id, frase, tipo);
        if(f)
        {
            UI.setStatus("OK");
        }
        else
            UI.setStatus("ERRO");
        UI.bloquear(true);
    }
}
