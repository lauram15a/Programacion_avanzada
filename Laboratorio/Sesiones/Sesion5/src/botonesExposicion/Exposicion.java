package botonesExposicion;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JTextField;

public class Exposicion
{
    int aforo;
    ListaThreads colaEspera, dentro;
    Semaphore semaforo;
    int controlador = 0;
    boolean abierto = true;
    Lock cerrojo = new ReentrantLock();
    Condition actualizando = cerrojo.newCondition();
    
    public Exposicion(int aforo, JTextField tfEsperan, JTextField tfDentro)
    {
        this.aforo=aforo;
        semaforo=new Semaphore(aforo,true);
        colaEspera=new ListaThreads(tfEsperan);
        dentro=new ListaThreads(tfDentro);
    }
    
    public void entrar(Visitante v)
    {        
        colaEspera.meter(v);
        try
        {
            semaforo.acquire();
        } catch(InterruptedException e){ }
        colaEspera.sacar(v);
        if (isAbierto())
        {
            dentro.meter(v);
        }
    }

    public void salir(Visitante v)
    {
        dentro.sacar(v);
        semaforo.release();
    }
    
    public void mirar(Visitante v)
    {
        try
        {
            Thread.sleep(2000+(int)(3000*Math.random()));
        } catch (InterruptedException e){ }
    }

    public void setAbierto(boolean abiertoo) {
        cerrojo.lock();
        controlador = 1;
        try
        {
            this.abierto = abiertoo;
        }
        finally
        {
            if (!abierto)
            {
                sacarColaEspera();
            }
            controlador = 0;
            actualizando.signal();
            cerrojo.unlock();
        }
        
    }

    public boolean isAbierto() {
        cerrojo.lock();
        try
        {
            while (controlador == 1)
            {
                try
                {
                    actualizando.await();
                }
                catch(InterruptedException e){}                
            }
            return abierto;
        }
        finally
        {
            cerrojo.unlock();
        }
    }
    
        public void sacarColaEspera()
    {
        while (!colaEspera.lista.isEmpty())
        {
            colaEspera.sacar(colaEspera.lista.get(0));
        }
    }
        
    
}
