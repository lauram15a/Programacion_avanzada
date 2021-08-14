package ej5_coloresVarios;

180
// Pintor es un Thread que pinta un botón con colores al azar
import java.awt.Color;
import javax.swing.JButton;
public class Pintor extends Thread {
    
    JButton b;
    Color paleta[] = new Color[12];
    
    public Pintor(JButton b){
        this.b=b;
        paleta[0] = Color.BLACK;
        paleta[1] = Color.RED;
        paleta[2] = Color.GREEN;
        paleta[3] = Color.BLUE;
        paleta[4] = Color.YELLOW;
        paleta[5] = Color.PINK;
        paleta[7] = Color.WHITE;
        paleta[8] = Color.GRAY;
        paleta[9] = Color.ORANGE;
        paleta[10] = Color.MAGENTA;
        paleta[11] = Color.CYAN;
        
        start();
    }
    
    public void run(){
          while (true){
            int i = (int)(5. * Math.random());
            b.setBackground(paleta[i]);
        }
    }
}