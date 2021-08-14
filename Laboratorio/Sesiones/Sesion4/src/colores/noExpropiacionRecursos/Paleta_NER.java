package colores.noExpropiacionRecursos;
import colores.*;
import java.awt.Color;
// La clase Paleta_NER contiene los colores del parchís y los sirve a los pintores
public class Paleta_NER
{
    private Color [] colores = new Color[4];
    private boolean[] ocupado = new boolean[4];
    
    public Paleta_NER()
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
    
    
    //NUEVO
    public synchronized Color tomaColor1(int n)
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
    
    public synchronized Color tomaColor2(int n)
    {
        int i=n%4; //para evitar errores de índice, si n>3 se sustituye por su resto
        Color colorDevuelto;
        if(ocupado[i])
        {
            colorDevuelto = Color.black;
        }
        else
        {
            ocupado[i]=true;
            colorDevuelto = colores[i];
        }
        return colorDevuelto;
    }
    
    public synchronized void dejaColor(int n)
    {
        int i=n%4; //para evitar errores de índice, si n>3 se sustituye por su resto
        ocupado[i]=false;
        notifyAll();
    }
    
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
    */
    
}