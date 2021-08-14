/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea1.bloque3;

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
    
    @Override
    public void run()
    {
        while (true)
        {
            System.out.println(comun.devolverTemperatura());
            try
            {
                sleep(1000);
            } catch (InterruptedException e){}
        }
    }
    
}
