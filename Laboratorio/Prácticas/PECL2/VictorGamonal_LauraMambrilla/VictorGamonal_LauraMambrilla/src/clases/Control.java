/*
 * La clase Control define los métodos necesarios para llevar a cabo
 * el control del programa mediante el uso de Locks y Condition.
 * El método mirar comprueba si un visitante puede entrar a la exposición.
 * El método abrir permite al siguiente visitante en la cola entrar a la exposición.
 * El método cerrar permite a un visitante de la exposición marcharse.
 * Los métodos abrir y cerrar serán compartidos por los distintos clientes 
 * mediante el uso de RMI.
 */
package clases;

/**
 *
 * @author Laura y Víctor
 */
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Control extends UnicastRemoteObject implements Metodos
{
    private boolean cerrado;
    private Lock cerrojo = new ReentrantLock();
    private Condition parar = cerrojo.newCondition();

    public Control() throws RemoteException
    {
        cerrado = false;
    }
    
 
    /**
     * comprueba
     */
    public void mirar()
    {
        try
        {
            cerrojo.lock();
            while(cerrado)
            {
                try
                {
                    parar.await();
                } catch(InterruptedException ie){ }
            }
        }
        finally
        {
            cerrojo.unlock();
        }
    }


    /**
     * reanuda
     * @throws java.rmi.RemoteException
     */
    @Override
    public void abrir() throws RemoteException  //así comparto el método
    {
        try
        {
            cerrojo.lock();
            cerrado=false; //Se cambia la condición por la que otros hilos podrían estar esperando
            parar.signalAll();
        }
        finally
        {
            cerrojo.unlock();
        }
    }

   
    /**
     * detiene
     * @throws java.rmi.RemoteException
     */
    @Override
    public void cerrar() throws RemoteException
    {
        try
        {
            cerrojo.lock();
            cerrado=true;
        }
        finally
        {
            cerrojo.unlock();
        }
    }

} 
