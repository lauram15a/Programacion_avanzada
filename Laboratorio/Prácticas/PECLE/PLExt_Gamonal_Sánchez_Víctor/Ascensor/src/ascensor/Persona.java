/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ascensor;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Laura y Victor
 */
public class Persona extends Thread
{
    
    private String id_persona;
    private int origen;
    private int destino;
    private String sentido;
    private boolean esta_en_ascensor = false;
    private Hospital hospital;
    
    private ReentrantLock cerrojo_en_ascensor = new ReentrantLock();
    
    public Persona(Hospital h, int num_id_persona, int origen_planta) 
    {
        this.hospital = h;
        this.id_persona = "P" + num_id_persona;
        this.origen = origen_planta;
        destino = (int) (20 * Math.random());
        while (this.origen == this.destino)  //nos aseguramos que origen != destino
        {
            this.destino = (int) (20 * Math.random());
        }        
        //sentido
        if (this.origen < this.destino)
        {
            this.sentido = "S";
        }
        else
        {
            this.sentido = "B";
        }
    }
    
    public Persona(Hospital h, String num_id_persona, int origen_planta, int destino_planta, String sentido)
    {
        this.hospital = h;
        this.id_persona = num_id_persona;
        this.origen = origen_planta;
        this.destino = destino_planta;
        this.sentido = sentido;
    }
    
    @Override
    public void run()
    {
        while (!isEsta_en_ascensor()) 
        {
            try 
            {
                if (!hospital.getPlantas_Hospital()[origen].isPulsado()) 
                {
                    hospital.pulsarBoton(origen);
                }
                sleep(100);
            } 
            catch (InterruptedException e) 
            {
                System.out.println(e.getMessage());
            }
        }
    }
    
    //////////////////////////////////////////////////////////////////////////
    //GETTERS Y SETTERS
    /////////////////////////////////////////////////////////////////////////
    
    /**
     * Get the value of origen
     *
     * @return the value of origen
     */
    public synchronized int getOrigen() 
    {
        return origen;
    }

    /**
     * Set the value of origen
     *
     * @param origen new value of origen
     */
    public void setOrigen(int origen) 
    {
        this.origen = origen;
    }

    /**
     * Get the value of esta_en_ascensor
     *
     * @return the value of origen
     */
    public boolean isEsta_en_ascensor() 
    {
        boolean ascensor;
        cerrojo_en_ascensor.lock();
        try 
        {
            ascensor = esta_en_ascensor;
        } 
        finally 
        {
            cerrojo_en_ascensor.unlock();
        }
        return ascensor;
    }

    /**
     * Set the value of esta_en_ascensor
     *
     * @param esta_en_ascensor
     */
    public void setEsta_en_ascensor(boolean esta_en_ascensor)
    {
        cerrojo_en_ascensor.lock();
        try 
        {
            this.esta_en_ascensor = esta_en_ascensor;
        } 
        finally 
        {
            cerrojo_en_ascensor.unlock();
        }
    }

    
    /**
     * Get the value of destino
     *
     * @return the value of destino
     */
    public synchronized int getDestino() 
    {
        return destino;
    }

    /**
     * Set the value of destino
     *
     * @param destino new value of destino
     */
    public void setDestino(int destino) 
    {
        this.destino = destino;
    }

    /**
     * Get the value of id_persona
     *
     * @return the value of id_persona
     */
    public synchronized String getId_persona() 
    {
        return id_persona;
    }

    /**
     * Set the value of id_persona
     *
     * @param id_persona new value of id_persona
     */
    public void setId_persona(String id_persona) 
    {
        this.id_persona = id_persona;
    }

    /**
     * Get the value of sentido
     *
     * @return the value of sentido
     */
    public synchronized String getSentido() 
    {
        return sentido;
    }

    /**
     * Set the value of sentido
     *
     * @param sentido new value of sentido
     */
    public void setSentido(String sentido) 
    {
        this.sentido = sentido;
    }

}
