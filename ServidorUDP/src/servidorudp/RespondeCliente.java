
package servidorudp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import bancoInterface.BancoDeDados;
import bancoInterface.Frase;
import java.util.ArrayList;
import java.util.List;
import static servidorudp.SocketManager.EnviarMensagemParticionada;
import static servidorudp.SocketManager.MontarVarString;
import static servidorudp.SocketManager.SendMessage;


/**
 *
 * @author Matheus Lyra
 */
public class RespondeCliente extends Thread{
   DatagramSocket socket;
   DatagramPacket packet;
   Frase frase;
   byte[] vet;
   int op;
   String[] fr, vetorAbertura;
   
     public String[]getVetorAbertura() {
        return vetorAbertura;
    }

    public void setVetorAbertura(String[] vetorAbertura) {
        this.vetorAbertura = vetorAbertura;
    }

    public String[] getFr() {
        return fr;
    }

    public void setFr(String[] fr) {
        this.fr = fr;
    }

    public int getOp() {
        return op;
    }

    public void setOp(int op) {
        this.op = op;
    }

    public byte[] getVet() {
        return vet;
    }

    public void setVet(byte[] vet) {
        this.vet = vet;
    }

    public Frase getFrase() {
        return frase;
    }

    public void setFrase(Frase frase) {
        this.frase = frase;
    }
   
    public DatagramSocket getSocket() {
        return socket;
    }

    public void setSocket(DatagramSocket socket) {
        this.socket = socket;
    }

    public DatagramPacket getPacket() {
        return packet;
    }

    public void setPacket(DatagramPacket packet) {
        this.packet = packet;
    }
    
        
    public void run() {
        
        try{
                /*
                   op#nrodepacotes
                
                    1#nroPacote#codfrase            || 2#nroPacote#frase#tipo || 3#nroPacote#codfrase
                    4#nroPacote#frase#codfrase#tipo || 5#nroPacote#tipo       || 6#nroPacote#tipo
                */ 
                //String fraseinserir;
                System.out.println("switch");
                switch(op){ //operações do cliente com o banco
                   
                    case 1: // 1 - consulta
                        System.out.println("switch 1 - "+Integer.parseInt(fr[0]));
                        frase = BancoDeDados.consulta(Integer.parseInt(fr[0])); // todos os inteiros cabem em um índice
                        if(frase != null)
                        {
                          EnviarMensagemParticionada(socket, packet, frase.getFrase(), 0);
                          System.out.println(frase.getFrase());
                        }
                        else{
                            System.out.println("frase nula");
                            SendMessage(socket, packet, "0#0");
                        }                           
                                
                        break;
                    case 2: // 2 - inserção
                        System.out.println("switch 2" );
                        BancoDeDados.inserir(MontarVarString(fr), Integer.parseInt(vetorAbertura[3].trim()));
                        break;
                    case 3: // 3 - delete
                        System.out.println("switch 3");
                        boolean excluido = BancoDeDados.exluir(Integer.parseInt(vetorAbertura[2].trim()));
                        if(excluido)
                            SendMessage(socket, packet, "Excluido");
                        else
                            SendMessage(socket, packet, "Não Excluido");
                        
                        break;
                    case 4: // 4 - alteracao  
                        System.out.println("switch 4: ");
                        boolean alterado = BancoDeDados.alterar(Integer.parseInt(vetorAbertura[3].trim()), MontarVarString(fr) , Integer.parseInt(vetorAbertura[4].trim()));
                        if(alterado)
                            SendMessage(socket, packet, "Alterado");
                        else
                            SendMessage(socket, packet, "Não Alterado");
                        break;
                    case 5: //5 - listar tipo
                        System.out.println("switch 5:");
                        Frase[] frases;
                        frases = BancoDeDados.lista_tipo(Integer.parseInt(vetorAbertura[2].trim()));
                        if(frases == null)
                            SendMessage(socket, packet, "0#0");
                        else{
                            SendMessage(socket, packet, "5#" + frases.length);
                            for(int i = 0; i < frases.length; i++){
                                System.out.println("String: " + frases[i].getFrase());
                                EnviarMensagemParticionada(socket, packet, frases[i].getFrase(), i);  
                            }
                        }                       
                        break;
                    case 6: // 6 - mensagem aleatoria
                        System.out.println("switch 6:");
                        frase = BancoDeDados.mensagem(Integer.parseInt(vetorAbertura[2].trim()));
                        if(frase == null)
                            SendMessage(socket, packet, "0#0");
                        else{
                            EnviarMensagemParticionada(socket, packet, frase.getFrase(), 0);                        
                            System.out.println("Aleatória: " + frase.getFrase());                            
                            }
                        break;
                    default: SendMessage(socket, packet, "Cliente Encerrado!");    
                }
                System.out.println(new String(packet.getData()));
             // return packet;
             //socket.close();
            } catch (Exception ex) {
           Logger.getLogger(RespondeCliente.class.getName()).log(Level.SEVERE, null, ex);
       }
  
          
      
    }    
}