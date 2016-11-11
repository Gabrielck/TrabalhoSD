
package servidorudp;

import bancoInterface.BancoDeDados;
import bancoInterface.Frase;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matheus Lyra
 */
public class SocketManager {
    
     DatagramPacket packet;
     DatagramSocket socket;

    public DatagramSocket AbreSocket(int porta) throws Exception{
        socket = new DatagramSocket(porta);
        return socket;
    }
    
    public DatagramPacket GetMessage() throws Exception{
        byte vet[] = new byte[110];
        
        Frase frase = new Frase();

        packet = new DatagramPacket(vet, vet.length);
        
        while(true){                
            socket.receive(packet);
                        
            if (packet != null){
                System.out.println("entrou");
                int tam = 0, op, tipo, cont = 0;
                vet = packet.getData();
                
                String[] vs = new String (vet).split("#");
                
                System.out.println("posição 1: " + vs[1]);
   
                op  = Integer.parseInt(vs[0].trim());
                System.out.println(op);
                tam = Integer.parseInt(vs[1].trim());
                String fr[] = new String[tam]; // Frase de Retorno
                String vetorAbertura[] = new String[5]; // 5 é o tamanho máximo indices do vetor de opções*/        
                                
                for(int i = 0; i < tam; i++){
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
                          EnviarMensagemParticionada(socket, packet, frase.getFrase());
                          System.out.println(frase.getFrase());
                        }
                        else{
                            System.out.println("frase nula");
                            SendMessage(socket, packet, "0");
                        }                           
                                
                        break;
                    case 2: // 2 - inserção
                        System.out.println("switch 2" + op);
                        BancoDeDados.inserir(MontarVarString(fr), Integer.parseInt(vetorAbertura[3].trim()));
                        break;
                    case 3: // 3 - delete
                        System.out.println("switch 3");
                        boolean excluido = BancoDeDados.exluir(Integer.parseInt(vetorAbertura[2].trim()));
                        if(!excluido)
                            SendMessage(socket, packet, "0");
                        break;
                    case 4: // 4 - alteracao  
                        System.out.println("switch 4: " + Integer.parseInt(vetorAbertura[4].trim()));
                        boolean alterado = BancoDeDados.alterar(Integer.parseInt(vetorAbertura[3].trim()), MontarVarString(fr) , Integer.parseInt(vetorAbertura[4].trim()));
                        if(!alterado)
                            SendMessage(socket, packet, "0");
                        break;
                    case 5: //5 - listar tipo
                        System.out.println("switch 5:");
                        Frase[] frases;
                        frases = BancoDeDados.lista_tipo(Integer.parseInt(vetorAbertura[2].trim()));
                        if(frases == null)
                            SendMessage(socket, packet, "0");
                        else{
                            for(int i = 0; i < frases.length; i++){
                                System.out.println("String: " + frases[i].getFrase());
                                EnviarMensagemParticionada(socket, packet, frases[i].getFrase());  
                            }
                        }                       
                        break;
                    case 6: // 6 - mensagem aleatoria
                        System.out.println("switch 6:");
                        frase = BancoDeDados.mensagem(Integer.parseInt(vetorAbertura[2].trim()));
                        if(frase == null)
                            SendMessage(socket, packet, "0");
                        else{
                            SendMessage(socket, packet, frase.getFrase());                        
                            System.out.println("Aleatória: " + frase.getFrase());                            
                            }
                        break;
                    default: SendMessage(socket, packet, "0");    
                }

              return packet;
            }
        }
    }
    
    public void SendMessage(DatagramSocket socket, DatagramPacket pacote, String s) throws Exception{
        String mensagem = new String(s);
        byte vet[] = new byte[100];
        
        vet = mensagem.getBytes();
        pacote = new DatagramPacket(vet, vet.length, pacote.getAddress(), pacote.getPort());
        socket.send(pacote);
        //socket.close();            
    }
    
    public String MontarVarString(String[] fr){
        String fraseInserir = new String();
        for(int i = 0; i < fr.length; i++)
            fraseInserir += fr[i];
        return fraseInserir;
    }
    
    public Boolean ValidarMensagem (String[] vs){
       //retorna true se vetor não perdeu mensagem
        for(int i = 0; i < vs.length; i++)
            if(vs[i].isEmpty()) return false;
        
        return true;
    }
    
    public List<String> QuebrarMensagem (String sInteira){
        List<String> strings = new ArrayList<String>();
        int index = 0;
        while (index<sInteira.length()){
            strings.add(sInteira.substring(index, Math.min(index+100,sInteira.length())));
            index+=10;
        }
        return strings;
    }

    public void EnviarMensagemParticionada (DatagramSocket socket, DatagramPacket packet, String mensagem) throws Exception{
        List<String> Mensagens = QuebrarMensagem(mensagem);
        for(int i = 0; i < Mensagens.size(); i++)
        SendMessage(socket, packet, Mensagens.get(i)); 
    }    
}

