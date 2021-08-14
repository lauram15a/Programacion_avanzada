
import java.util.PriorityQueue;
import java.util.concurrent.Semaphore;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author laura
 */
public class ITV 
{
    
    Semaphore semaforo = new Semaphore(1);
    PriorityQueue listaCoches = new PriorityQueue <Integer>();
    int tiempoTotal = 0;


public void nuevoCoche (int numeroCoche) 
{
    try
    {
        semaforo.acquire();
        listaCoches.add(numeroCoche);
        semaforo.release();
    }
    catch (InterruptedException e)
    {
        System.out.println(e);
    }
}
}
