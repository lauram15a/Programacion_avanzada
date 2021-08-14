package ej6_colores_tiempo;

// Pintor es un Thread que pinta un botón con colores al azar
import java.awt.Color;
import java.util.Random;
import javax.swing.JButton;


public class Pintor extends Thread 
{
    
    JButton b;
    Color paleta[] = new Color[5];
    
    public Pintor(JButton b)
    {
        this.b=b;
        paleta[0]=Color.BLACK;
        paleta[1]=Color.RED;
        paleta[2]=Color.GREEN;
        paleta[3]=Color.BLUE;
        paleta[4]=Color.YELLOW;
    }
    
    @Override
    public void run()
    {
        Random aleatorio = new Random();
        
        int contador = 0;
        
        while (true)
        {
            if (contador == 0)
            {
                b.setBackground(paleta[0]);
                try
                {
                    int tiempo = aleatorio.nextInt(2001)+2000;
                    sleep(tiempo);
                } catch(InterruptedException e){
                    System.out.println("Error en el sleep " + e);
                }
                
                contador ++;
            } else {
                int i = (int)(4. * Math.random());
                b.setBackground(paleta[i + 1]);
                try
                {
                    int tiempo = aleatorio.nextInt(2001)+3000;
                    sleep(tiempo);
                } catch(InterruptedException e){
                    System.out.println("Error en el sleep " + e);
                }
                contador --;
            }
            
        }
    }
}