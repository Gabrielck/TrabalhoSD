
package ClienteSOAP;



import javax.swing.JOptionPane;
import proxy.Controle;
import proxy.ControleService;
import proxy.Frase;

public class Cliente {


    public static void main(String[] args) {
        Controle control;
        
        try
        {
            ControleService ser = new ControleService();
            control = ser.getControlePort();
        }
        catch(Exception E)
        {
                  JOptionPane.showMessageDialog(null, "ERRO ao se conectar ao servidor: ");
                  E.printStackTrace();
                  return;
        }
        ClienteUI ui = new ClienteUI(control);
        ui.setVisible(true);
    }
    
}
