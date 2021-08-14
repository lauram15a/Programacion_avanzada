/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea2.bloque3;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author 
 */
public class Comun 
{
    private ArrayList inter = new ArrayList();
    private Lock cerrojo = new ReentrantLock();
    private int numNumerosMax = 10; 
    private Condition registroLleno = cerrojo.newCondition();
    private Condition registroVacio = cerrojo.newCondition();

    //termometro
    public void enviarTemperatura(int temperatura)
    {
        cerrojo.lock();
        System.out.println("");
        try
        {
            while (inter.size() > (numNumerosMax-1))
            {
                try
                {
                    registroLleno.await();
                }
                catch (InterruptedException e){}
            }
            registroVacio.signal();
            inter.add(temperatura);
            System.out.println("Termometro- ha metido : " + inter.get(inter.size()-1));
            System.out.println("");
        }
        finally
        {
            cerrojo.unlock();
        }
    }
    
    //display
    public void devolverTemperatura()
    {
        cerrojo.lock();
        System.out.println("");
        try
        {
            while (inter.size() < 1)
            {
                try
                {
                    registroVacio.await();
                }
                catch (InterruptedException e){}
            }
            registroLleno.signal();
            System.out.println("Display saca: " + inter.get(0));
            inter.remove(0);
        }
        finally 
        {
            cerrojo.unlock();
        }
    }
}
