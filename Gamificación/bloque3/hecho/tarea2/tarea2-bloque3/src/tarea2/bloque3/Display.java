/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea2.bloque3;


/**
 *
 * @author 
 */
public class Display extends Thread{
    
    private Comun comun;
    
    //constructor
    public Display(Comun comun) {
        this.comun = comun;
    }
    
    public void sacarNumeroBuffer()
    {
        comun.devolverTemperatura();
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
            sacarNumeroBuffer();
        }        
    }
    
}
