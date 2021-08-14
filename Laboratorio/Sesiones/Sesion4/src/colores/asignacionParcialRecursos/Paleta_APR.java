package colores.asignacionParcialRecursos;
import colores.*;
import java.awt.Color;
// La clase Paleta_APR contiene los colores del parchís y los sirve a los pintores
public class Paleta_APR
{
    private Color [] colores = new Color[4];
    private boolean[] ocupado = new boolean[4];
    
    public Paleta_APR()
    {
        colores[0]=new Color(128,0,0,255);
        colores[1]=new Color(128,0,128,255);
        colores[2]=new Color(0,0,128,255);
        colores[3]=new Color(128,128,0,255);
        
        for(int i=0;i<4;i++)
        {
            ocupado[i]=false;
        }
    }
    
    
    public synchronized Color [] tomaColores(int n)
    {
        int i = n%4;  //para evitar errores de índice, si n>3 se sustituye por su resto
        while (ocupado[i] | ocupado[(i+1)%4])
        {
            System.out.println("Pintor " + i + "   color1 = " + ocupado[i] + "  color2 = " + ocupado[(i+1)%4]);
            try
            {
                wait();  //mientras el color esté ocupado, espero                
            }
            catch (InterruptedException e)
            {
                System.out.println("Error en tomaColores " + e);
            }      
        }
        ocupado[i] = true;
        ocupado[(i+1)%n]=true;
        Color[] doscolores={colores[i],colores[(i+1)%4]};
        return doscolores;
    }
    
    public synchronized void dejaColores(int n) 
    {
        int i = n % 4; //para evitar errores de índice, si n>3 se sustituye por su resto
        ocupado[i] = false;
        ocupado[(i + 1) % 4] = false;
        notifyAll();
    }
    
    /*
    public synchronized Color tomaColor(int n)
    {
        int i=n%4; //para evitar errores de índice, si n>3 se sustituye por su resto
        while(ocupado[i])
        {
            try
            {
                //ERROR AQUÍ
                wait(); // mientras el color esté ocupado, espero
            } catch(InterruptedException e){}
        }
        ocupado[i]=true;
        return colores[i];
    }
    
    public synchronized void dejaColor(int n)
    {
        int i=n%4; //para evitar errores de índice, si n>3 se sustituye por su resto
        ocupado[i]=false;
        notifyAll();
    }
    */
    
    public Color mezclaColores(Color x, Color y)
    {
        Color colorMezclado;
        int mezclaRojo = x.getRed()+y.getRed();
        int mezclaVerde = x.getGreen()+y.getGreen();
        int mezclaAzul = x.getBlue()+y.getBlue();
        if(mezclaRojo>255) //Si la mezcla supera el valor 255, se pone 255 (que es el máximo) para que no dé error
        {
            mezclaRojo=255;
        }            
        if(mezclaVerde>255)
        {
            mezclaVerde=255;
        }
        if(mezclaAzul>255)
        {
            mezclaAzul=255;
        }
        colorMezclado = new Color (mezclaRojo, mezclaVerde, mezclaAzul, 255);
        return colorMezclado;
    }
    
}
