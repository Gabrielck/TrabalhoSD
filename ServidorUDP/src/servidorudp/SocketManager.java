
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
                int tam, op, tipo, cont = 0;
                vet = packet.getData();
                                
                String[] vs = new String (vet).split("#");
                
                System.out.println("posição 1: " + vs[1]);
   
                op  = Integer.parseInt(vs[0]);
                tam = Integer.parseInt(vs[1]);
                String fr[] = new String[tam]; // Frase de Retorno
                String vetorAbertura[] = new String[5]; // 5 é o tamanho máximo indices do vetor de opções       */        
                
                System.out.println("teste: " + tam);
                
                for(int i = 0; i < tam; i++){
                    socket.receive(packet);
                                        
                    vet = packet.getData();
                    vetorAbertura = new String (vet).split("#");
                    
                    if(Integer.parseInt(vetorAbertura[0]) != op){
                        continue;
                    }
                                  
                    if(op == 2 || op == 4) // Se for inserção ou alteração
                        fr[Integer.parseInt(vetorAbertura[1])-1] = vetorAbertura[2]; //"-1" é porque é mandado o n° de partições e não a posição
                }
                           
                System.out.println("testezinho"+vetorAbertura[1]);
                
                if(!ValidarMensagem(fr)){
                   //return (DatagramPacket) null;
                   SendMessage(socket, packet, "FALHA DE OMISSÃO");
                }
                /*
                   op#nrodepacotes
                
                    1#nroPacote#codfrase            || 2#nroPacote#frase#tipo || 3#nroPacote#codfrase
                    4#nroPacote#frase#codfrase#tipo || 5#nroPacote#tipo       || 6#nroPacote#tipo
                */ 
                //String fraseinserir;
               /* switch(op){ //operações do cliente com o banco
                   
                    case 1: // 1 - consulta
                        frase = BancoDeDados.consulta(Integer.parseInt(fr[0])); // todos os inteiros cabem em um índice
                        EnviarMensagemParticionada(socket, packet, frase.getFrase());                       
                        break;
                    case 2: // 2 - inserção                      
                        BancoDeDados.inserir(MontarVarString(fr), op);
                        break;
                    case 3: // 3 - delete
                        boolean excluido = BancoDeDados.exluir(Integer.parseInt(fr[0]));
                        if(!excluido)
                            SendMessage(socket, packet, "0");
                        break;
                    case 4: // 4 - alteracao                   
                        BancoDeDados.alterar(Integer.parseInt(vetorAbertura[3]), MontarVarString(fr) , Integer.parseInt(vetorAbertura[4]));
                        break;
                    case 5: //5 - listar tipo
                        Frase[] frases;
                        frases = BancoDeDados.lista_tipo(Integer.parseInt(vetorAbertura[4]));
                        for(int i = 0; i < frases.length; i++)
                            EnviarMensagemParticionada(socket, packet, frases[i].getFrase());  
                        break;
                    case 6: // 6 - mensagem aleatoria
                        frase = BancoDeDados.mensagem(Integer.parseInt(vetorAbertura[4]));
                        SendMessage(socket, packet, frase.getFrase());
                        break;
                    default: SendMessage(socket, packet, "0");                       
                }
*/
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
        socket.close();            
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
            if(vs[i] == null) return false;
        
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

