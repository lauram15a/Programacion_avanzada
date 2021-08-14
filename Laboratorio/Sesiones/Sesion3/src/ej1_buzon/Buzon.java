/*
 * La clase Buzon tiene que estar protegida con un cerrojo
 * El método enviaMensaje debe esperar si el buzón está lleno
 * El método recibeMensaje debe esperar si el buzón está vacío.
 * Cuando un hilo completa su operación, desbloquea a los que estén esperando
 * para que puedan continuar intentando su acción.
 */
package ej1_buzon;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Buzon
{
    private String mensaje;
    private boolean hayMensaje=false;
    private Lock cerrojo = new ReentrantLock();
    private Condition buzonLleno = cerrojo.newCondition();
    private Condition buzonVacio = cerrojo.newCondition();
 
    public void enviaMensaje(String msg)
    {
        try
        {
            cerrojo.lock();
            
            while (hayMensaje)
            {
                try {
                    buzonLleno.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Buzon.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            hayMensaje=true;
            mensaje=msg;
            
            buzonVacio.signal();
        }
        finally
        {
            cerrojo.unlock();
        }
        
    }
 
    public String recibeMensaje()
    {
        try
        {
            cerrojo.lock();
            
            while (!hayMensaje)
            {
                try {
                    buzonVacio.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Buzon.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            hayMensaje=false;
            buzonLleno.signal();
            
            return mensaje;
        }
        finally
        {
            cerrojo.unlock();
        }
    }
}