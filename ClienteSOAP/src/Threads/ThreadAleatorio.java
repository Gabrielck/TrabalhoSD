
package Threads;


import ClienteSOAP.ClienteUI;
import proxy.Controle;
import proxy.Frase;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ThreadAleatorio extends Thread 
{
    ClienteUI UI;
    Controle control;
    int id;
    
    public ThreadAleatorio(int i, ClienteUI U, Controle c)
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
        Frase f = control.mensagemAleatoria(id);
        if(f.getId() != -1)
        {
            UI.setSaida("ID: " + Integer.toString(f.getId()) + "\n" + f.getFrase());
            UI.setStatus("OK");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Erro na operação mensagem aleatória");
            UI.setStatus("ERRO");
        }
            
        UI.bloquear(true);
    }
}
