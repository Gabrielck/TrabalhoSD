
package ClienteSOAP;


import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.swing.JOptionPane;


public class Cliente {


    public static void main(String[] args) {
        Controle control;
        
        try
        {
            ControleService ser = new ControleService();
            control = ser.getControlePort();
            
            Frase teste = new Frase();
            teste = control.consultar(10);
            
            System.out.println(teste.getFrase());
        }
        catch(Exception E)
        {
                  JOptionPane.showMessageDialog(null, "ERRO ao se conectar ao servidor: ");
                  return;
        }

        //System.out.println(control.consultar());        
        //System.out.println(control.mensagemAleatoria(1));        
        
        ClienteUI ui = new ClienteUI(control);
        ui.setVisible(true);
    }
    
}
