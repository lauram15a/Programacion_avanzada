/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 *
 * @author 
 */
public class Comun 
{
    private ArrayList inter = new ArrayList();
    private int numNumerosMax = 10; 
    private Semaphore mutex = new Semaphore(1, true);  //garantiza la exclusión mutua --- 1 para que haya disponibilidad para entrar
    private Semaphore hayDatos = new Semaphore(0, true); //0 porque inicialmente no hay datos
    private Semaphore hayEspacio = new Semaphore(numNumerosMax, true);
    
    //termometro
    public void enviarTemperatura(int temperatura) throws InterruptedException
    {
        hayEspacio.acquire();
        mutex.acquire(); //si puedo ingresar sigo, si no, espero
        System.out.println("Termometro mete: " + temperatura);
        inter.add(temperatura);
        mutex.release(); //sumo al contador 1
        hayDatos.release();
    }
    
    //display
    public void devolverTemperatura() throws InterruptedException
    {
        hayDatos.acquire();
        mutex.acquire();
        System.out.println("Display saca: " + inter.get(0));
        inter.remove(0);
        mutex.release();
        hayEspacio.release();        
    }
}
