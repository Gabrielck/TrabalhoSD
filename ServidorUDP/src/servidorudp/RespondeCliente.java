
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


/**
 *
 * @author Matheus Lyra
 */
public class RespondeCliente extends Thread{
   DatagramSocket socket;
   DatagramPacket packet;
   Frase frase;
   byte[] vet;

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
            
                if (packet != null){
                System.out.println("entrou");
                int tam = 0, op;
                vet = new byte[115];
                vet = packet.getData();
                System.out.println("PRIMEIRO PACOTE: " + new String(packet.getData()));
                String[] vs = new String (vet).split("#");
                
                System.out.println("posição 1: " + vs[1]);
   
                op  = Integer.parseInt(vs[0].trim());
                System.out.println(op);
                tam = Integer.parseInt(vs[1].trim());
                String fr[] = new String[tam]; // Frase de Retorno
                String vetorAbertura[] = new String[5]; // 5 é o tamanho máximo indices do vetor de opções*/        
                                
                for(int i = 0; i < tam; i++){
                    System.out.println("ENTROU FOR");
                    vet = new byte[115];
                    DatagramPacket packet = new DatagramPacket(vet, vet.length);
                    System.out.println("PACOTE: " + new String(packet.getData()));
                    socket.receive(packet);
                    vet = packet.getData();
                    vetorAbertura = new String (vet).split("#");
                    
                    if(Integer.parseInt(vetorAbertura[0].trim()) != op){
                        continue;
                    }
                    //if(op == 2 || op == 4) // Se for inserção ou alteração
                    fr[(Integer.parseInt(vetorAbertura[1].trim()))-1] = vetorAbertura[2].trim(); //"-1" é porque é mandado o n° de partições e não a posição                   
                }
                
                if(!ValidarMensagem(fr)){
                   //return (DatagramPacket) null;   
                   System.out.println("validar");
                   SendMessage(socket, packet, "0");
                }
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
             socket.close();
            }
            
            
            
        }catch(Exception ex){
            
        }
      
          
      
    }
    
     public static void SendMessage(DatagramSocket socket, DatagramPacket pacote, String s) throws Exception{
        String mensagem = new String(s);
        byte vet[] = new byte[115];
        
        vet = mensagem.getBytes();
        DatagramPacket pac = new DatagramPacket(vet, vet.length, pacote.getAddress(), pacote.getPort());
        
        System.out.println("Envio Cliente: " + new String(pac.getData()));
        socket.send(pac);
        //socket.close();            
    }
    
    public static String MontarVarString(String[] fr){
        System.out.println("MontarVarString");
        String fraseInserir = new String();
        for(int i = 0; i < fr.length; i++)
            fraseInserir += fr[i];
       
        System.out.println(fraseInserir);
        return fraseInserir;
    }
    
    public  static Boolean ValidarMensagem (String[] vs){
       //retorna true se vetor não perdeu mensagem
        for(int i = 0; i < vs.length; i++)
            if(vs[i].isEmpty()) return false;
        
        return true;
    }
    
    public static List<String> QuebrarMensagem (String sInteira, int StringPertence){
        List<String> strings = new ArrayList<String>();
        int index = 0, partString = 1;
        while (index<sInteira.length()){
            strings.add(StringPertence +"#"+ partString +"#"+ sInteira.substring(index, Math.min(index+100,sInteira.length())));
            index+=100;
            partString++;
        }
        return strings;
    }

    public static void EnviarMensagemParticionada (DatagramSocket socket, DatagramPacket packet, String mensagem, int codMens ) throws Exception{
        List<String> Mensagens = QuebrarMensagem(mensagem, codMens);

        SendMessage(socket, packet, new String(codMens+"#"+Mensagens.size()));
        for(int i = 0; i < Mensagens.size(); i++)
            SendMessage(socket, packet, Mensagens.get(i)); 
    }  
    
    
    
    
    
    
    
}