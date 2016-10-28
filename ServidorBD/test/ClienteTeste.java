
import bancoInterface.*;





/*
 * To change this license header, choose License Headesrs in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**\
 *
 * @author gabrielkr
 */
public class ClienteTeste {
     public static void main(String[] args) throws Exception {
        BancoDeDados.inserir("Frase de teste", 2);
        BancoDeDados.alterar(1, "aasdfasdfasd", 15);
        
        Frase a = BancoDeDados.consulta(10);
        if(a != null)
             System.out.println(a.getFrase());
      
        Frase b = BancoDeDados.mensagem(5);
        if(b != null)
             System.out.println(b.getFrase());
      
        Frase c[] = BancoDeDados.lista_tipo(5);
        if(c != null)
            for(Frase x: c)
                System.out.println(x.getFrase());
        
    }
}
