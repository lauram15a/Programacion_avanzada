/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea1.bloque3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author
 */
public class Comun 
{
    private int vc;
    private Lock cerrojo = new ReentrantLock();
    private int cont = 1;
    private Condition temperaturaTomada = cerrojo.newCondition();
    private Condition temperaturaNoTomada = cerrojo.newCondition();
    

    public void enviarTemperatura(int temperatura)
    {
        cerrojo.lock();
        try
        {
            while (cont != 0)
            {
                try
                {
                    temperaturaTomada.await();
                }
                catch (InterruptedException e){}
            }
            temperaturaNoTomada.signal();
            vc = temperatura;
            cont++;
            System.out.println(vc);
        }
        finally
        {
            cerrojo.unlock();
        }
    }
    
    public double devolverTemperatura()
    {
        cerrojo.lock();
        try
        {
            while (cont == 0)
            {
                try
                {
                    temperaturaNoTomada.await();
                }
                catch (InterruptedException e){}
            }
            temperaturaTomada.signal();
            cont--; 
            return vc;
        }
        finally 
        {
            cerrojo.unlock();
        }
    }
}
