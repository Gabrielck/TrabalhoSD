/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorudp;

/**
 *
 * @author Matheus Lyra
 */
public class Mensagem{
    
    private String partMessage;
    private int index;
    
    public String getPartMessage() {
        return partMessage;
    }

    public void setPartMessage(String partMessage) {
        this.partMessage = partMessage;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
