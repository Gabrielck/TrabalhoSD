
package ServidorSOAP;

import javax.swing.JOptionPane;
import javax.xml.ws.Endpoint;


public class Servidor {


    public static void main(String[] args) {
        String ip = JOptionPane.showInputDialog("Informe o endereço IP, ou localhost?");
        String url = "http://" + ip + "/soap/";
        try
        {
            Endpoint.publish(url, new Controle());
            JOptionPane.showMessageDialog(null, "Servidor iniciado\nURL: " + url + "?WSDL");
        }
        catch(Exception E)
        {
            JOptionPane.showMessageDialog(null, "ERRO ao iniciar o servidor SOAP");
            E.printStackTrace();
        }
    }
    
}
