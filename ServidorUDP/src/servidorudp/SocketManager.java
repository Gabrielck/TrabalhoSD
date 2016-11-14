
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

    public  DatagramSocket AbreSocket(int porta) throws Exception{
        socket = new DatagramSocket(porta);
        return socket;
    }
    
    public static void FechaSocket(DatagramSocket socket) throws Exception{
        socket.close();
    }
    
    public DatagramPacket GetMessage() throws Exception{
                
        Frase frase = new Frase();
        Boolean nova = false;
        byte vet[];
        while(true) {
        novaMensagem:
            while(true){
                if(!nova)
                {
                    vet = new byte[110];
                    packet = new DatagramPacket(vet, vet.length);
                    socket.receive(packet);
                    String[] aux = new String (vet).split("#");
                    if(!aux[2].trim().equals("@@@"))
                    {
                        System.out.println("Mensagem inincial inválida!");
                        SendMessage(socket, packet, "00#0");
                        continue;
                    }
                }
                if (packet != null) {
                    System.out.println("entrou");
                    int tam = 0, op;
                    vet = new byte[110];
                    vet = packet.getData();

                    String[] vs = new String (vet).split("#");

                    System.out.println("posição 1: " + vs[1]);

                    op  = Integer.parseInt(vs[0].trim());
                    System.out.println(op);
                    tam = Integer.parseInt(vs[1].trim());
                    String fr[] = new String[tam]; // Frase de Retorno
                    String vetorAbertura[] = new String[5]; // 5 é o tamanho máximo indices do vetor de opções*/        

                        for(int i = 0; i < tam; i++){
                            vet = new byte[110];
                            packet = new DatagramPacket(vet, vet.length);
                            socket.receive(packet);
                            vet = packet.getData();
                            System.out.println("Mensagem recebida: "+new String(vet));
                            vetorAbertura = new String (vet).split("#");
                            if(vetorAbertura[2]!=null)
                            {
                                System.out.println("Segundo indice: "+vetorAbertura[2]);
                                if(vetorAbertura[2].trim().equals("@@@"))
                                {
                                    nova = true;
                                    System.out.println("Falha na transmissao anterior, nova mensagem recebida");
                                    break novaMensagem;
                                }
                            }

                            if(Integer.parseInt(vetorAbertura[0].trim()) != op){
                                continue;
                            }
                            //if(op == 2 || op == 4) // Se for inserção ou alteração
                            Integer aa = (Integer.parseInt(vetorAbertura[1].trim()))-1;
                            System.out.println("Vetor indice: "+aa);
                            fr[(Integer.parseInt(vetorAbertura[1].trim()))-1] = vetorAbertura[2].trim(); //"-1" é porque é mandado o n° de partições e não a posição                   
                        }

                    if(!ValidarMensagem(fr)){
                       //return (DatagramPacket) null;   
                       System.out.println("Mensagem inincial inválida!");
                       SendMessage(socket, packet, "00#0");
                    }
                    RespondeCliente respondeCliente = new RespondeCliente();
                    respondeCliente.setSocket(socket);
                    respondeCliente.setPacket(packet);
                    respondeCliente.setFrase(frase);
                    respondeCliente.setVet(vet);
                    respondeCliente.setOp(op);
                    respondeCliente.setFr(fr);
                    respondeCliente.setVetorAbertura(vetorAbertura);
                    respondeCliente.start();
                   
                  return packet;
                }
            }
        }
    }
    
    public static void SendMessage(DatagramSocket socket, DatagramPacket pacote, String s) throws Exception{
        String mensagem = new String(s);
        byte vet[] = new byte[110];
        
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

