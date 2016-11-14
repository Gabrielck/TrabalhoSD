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
        
        Thread2 vrau;
        vrau = new Thread2();
        System.out.println("Chamou a thread");
        vrau.start();
        
        


    }
}
