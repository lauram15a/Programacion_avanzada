
import java.util.logging.Level;
import java.util.logging.Logger;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 
 */
public class Termometro extends Thread
{
    private int temperatura;
    private Comun comun;
    private int contador = 0;
    
    //constructor

    public Termometro(Comun comun) {
        this.comun = comun;
    }
    
    public void meterNumeroBuffer() throws InterruptedException
    {
        int temperatura = (int) (-50 + 150 * Math.random());  //Rango aleatorios int  (de 1000 a 1500)
        comun.enviarTemperatura(temperatura); 
        try 
        {
            sleep(1000);
        } 
        catch (InterruptedException e) 
        {
            System.out.println("Error en meterNumeroBuffer()");
        }
    }
    
    @Override
    public void run()
    {
        while (true)
        {
            try {
                meterNumeroBuffer();
            } catch (InterruptedException ex) {
                Logger.getLogger(Termometro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
