
package servidorudp;

import bancoInterface.BancoDeDados;
import bancoInterface.Frase;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

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
        byte vet[] = new byte[100];
        
        Frase frase = new Frase();

        packet = new DatagramPacket(vet, vet.length);
        
        while(true){                
            socket.receive(packet);
            
            if (packet != null){
                int tam, op, tipo, cont = 0;
                vet = packet.getData();
                
                String[] vs = new String (vet).split("#");
                
                op  = Integer.parseInt(vs[0]);
                tam = Integer.parseInt(vs[1]);
                String fr[] = new String[tam]; // Frase de Retorno
                String vetorAbertura[] = new String[5]; // 5 é o tamanho máximo indices do vetor de opções               
                
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
                switch(op){ //operações do cliente com o banco
                   
                    case 1: // 1 - consulta
                        frase = BancoDeDados.consulta(Integer.parseInt(fr[0])); // todos os inteiros cabem em um índice
                        SendMessage(socket, packet, frase.getFrase());
                        break;
                    case 2: // 2 - inserção                      
                        BancoDeDados.inserir(MontarVarString(fr), op);
                        break;
                    case 3: // 3 - delete
                        boolean excluido = BancoDeDados.exluir(Integer.parseInt(fr[0]));
                        if(!excluido)
                            SendMessage(socket, packet, "FALHA NA EXCLUSÃO");
                        break;
                    case 4: // 4 - alteracao                   
                        BancoDeDados.alterar(Integer.parseInt(vetorAbertura[3]), MontarVarString(fr) , Integer.parseInt(vetorAbertura[4]));
                        break;
                    case 5: //5 - listar tipo
                        BancoDeDados.lista_tipo(Integer.parseInt(vetorAbertura[4]));
                        break;
                    case 6: // 6 - mensagem aleatoria
                        BancoDeDados.mensagem(Integer.parseInt(vetorAbertura[4]));
                        break;
                    default: SendMessage(socket, packet, "OPÇÃO INVÁLIDA");;                       
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
            if(vs[i] == null) return false;
        
        return true;
    }
    
}

