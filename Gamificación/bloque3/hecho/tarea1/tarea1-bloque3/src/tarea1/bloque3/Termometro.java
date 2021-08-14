package tarea1.bloque3;

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
    
    //constructor

    public Termometro(Comun comun) {
        this.comun = comun;
    }
    
    @Override
    public void run()
    {
        while (true)
        {
            temperatura = (int)((-50)+100*Math.random());
            comun.enviarTemperatura(temperatura);    
            try
            {
                sleep(1000);
            } catch (InterruptedException e){}
        }
    }
}
