
package Threads;


import ClienteSOAP.ClienteUI;
import proxy.Controle;
import proxy.Frase;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ThreadConsulta extends Thread 
{
    ClienteUI UI;
    Controle control;
    int id;
    
    public ThreadConsulta(int i, ClienteUI U, Controle c)
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
            Logger.getLogger(ThreadConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
        Frase f = control.consultar(id);
        if(f.getId() != -1)
        {
            UI.setSaida(f.getFrase());
            UI.setStatus("OK");
        }
        else
        {
             JOptionPane.showMessageDialog(null, "Erro em consulta");
             UI.setStatus("ERRO");
        }
       
        UI.bloquear(true);
    }
}
