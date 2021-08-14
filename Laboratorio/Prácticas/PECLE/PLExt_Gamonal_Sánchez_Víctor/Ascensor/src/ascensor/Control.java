/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ascensor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Laura y Victor
 */
public class Control extends UnicastRemoteObject implements Metodos
{
    private final Hospital h;
    private final Lock cerrojo1 = new ReentrantLock();
    private final Lock cerrojo2 = new ReentrantLock();

    public Control(Hospital h) throws RemoteException
    {
        this.h = h;
    }    
    
    ////////////////////////////////////////////////////////////////////////////
    //modulo vigilante
    ////////////////////////////////////////////////////////////////////////////
    
    /**
     * Este módulo permitirá consultar de forma remota el estado de los dos ascensores del hospital, 
     * mostrando su estado de forma automática con una periodicidad de 1 segundo. 
     * La información relativa a la situación de cada ascensor mostrada será: piso en el que se 
     * encuentra el ascensor, cuántas personas se encuentran en su interior, así como sus destinos y 
     * las personas que se encuentran esperando en cada planta.
     * @throws java.rmi.RemoteException
     */
    @Override
    public void metodoModuloVigilante() throws RemoteException
    {
        cerrojo1.lock();
        try
        {
            System.out.println("___________________________________________________________________________________________MODULO VIGILANTE_____________________________________________________________________________________________________________");
            for (int i = 0; i < h.getAscensores().length; i++)
            {
                System.out.println("ASCENSOR: " + h.getAscensores()[i].getId_ascensor() + " - Estado: " + h.getAscensores()[i].getEstado() + " - Nº personas: " + h.getAscensores()[i].getNum_personas() + " - ** Planta Actual ** - " + h.getAscensores()[i].getPlanta_actual());

                System.out.println("// PERSONAS DENTRO ");
                for (int j = 0; j < h.getAscensores()[i].getPersonas_en_ascensor().size(); j++)
                {
                    System.out.println(h.getAscensores()[i].getPersonas_en_ascensor().get(j).getId_persona() + " - Origen: " + h.getAscensores()[i].getPersonas_en_ascensor().get(j).getOrigen() + " - Destino: " + h.getAscensores()[i].getPersonas_en_ascensor().get(j).getDestino());
                }
                System.out.println("-------------------------------------");
            }

            for (int i = h.getPlantas_Hospital().length - 1; i >= 0; i--) //recorremos al revés
            {
                System.out.println("-|-|-|- " + i + " -|-|-|-");
                for (int j = 0; j < h.getPlantas_Hospital()[i].getPersonas().size(); j++)
                {
                    Persona p = h.getPlantas_Hospital()[i].getPersonas().get(j);
                    System.out.println(p.getId_persona() + " - Origen: " + p.getOrigen() + " - Destino: " + p.getDestino());
                }
                System.out.println("");
            } 
            System.out.println("____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________\n");
        }
        finally            
        {
            cerrojo1.unlock();
        }               
    }
    
    ////////////////////////////////////////////////////////////////////////////
    //modulo evacuacion
    ////////////////////////////////////////////////////////////////////////////

    /**
     * dejarán de crearse nuevas personas y, automáticamente, la planta de destino 
     * de todas las personas que estén esperando a un ascensor será la planta baja,
     * para abandonar el hospital.
     * @throws RemoteException
     */    
    @Override
    public void metodoModuloEvacuacion() throws RemoteException
    {
        cerrojo2.lock();
        try
        {
            h.setEvacuacion(true);
            cambiarDestinoACero();
        }
        finally
        {
            cerrojo2.unlock();
        }
    }
    
    /**
     * cambiamos todos los destino a cero
     */
    private void cambiarDestinoACero()
    {
        //cambiamos a los que estan dentro de los ascensores
        for (int i = 0; i < h.getAscensores().length; i++)
        {
            for (int j = 0; j < h.getAscensores()[i].getPersonas_en_ascensor().size(); j++)
            {
                Persona p = h.getAscensores()[i].getPersonas_en_ascensor().get(j);
                p.setDestino(0);
            }
        }
        
        //cambiamos a los que están en las plantas
        for (int i = h.getPlantas_Hospital().length - 1; i >= 0; i--) //recorremos al revés
        {
            for (int j = 0; j < h.getPlantas_Hospital()[i].getPersonas().size(); j++)
            {
                Persona p = h.getPlantas_Hospital()[i].getPersonas().get(j);
                p.setDestino(0);
            }
        } 
    }
}
