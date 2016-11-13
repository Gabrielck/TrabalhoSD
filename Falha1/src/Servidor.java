/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author rebonatto
 */
public class Servidor {

    public static void main(String[] args) throws Exception {
        InetAddress adress1;

        int porta;

        String adress;

        System.out.println("Digite o número do servidor: ");
        Scanner entrada = new Scanner(System.in);
        adress = entrada.nextLine();
        porta = 2006;
        
        Comunica vrau;
        vrau = new ThreadServer(adress, porta);
        
        //vrau();

        //adress = InetAddress.getLocalHost();

    }
}
