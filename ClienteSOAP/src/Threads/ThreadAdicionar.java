
package Threads;


import ClienteSOAP.ClienteUI;
import proxy.Controle;
import proxy.Frase;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
        {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar");
            UI.setStatus("ERRO");
        }
            
        UI.bloquear(true);
    }
}
