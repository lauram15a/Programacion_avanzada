
package ascensor;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Laura y Victor
 */
public class Planta 
{
    private int id_planta;
    private boolean pulsado;
    private ArrayList<Persona> personas = new ArrayList<>();
    private final ReentrantLock cerrojo_boton = new ReentrantLock();
    private final ReentrantLock cerrojo_persona = new ReentrantLock();
    
    public Planta (int n_planta)
    {
        id_planta = n_planta;
        pulsado = false;
        personas = new ArrayList<Persona>();
    }
    
    ////////////////////////////////////////////////////////////////////////////
    // GETTER Y SETTER
    ////////////////////////////////////////////////////////////////////////////
    
    public int getId_Planta() 
    {
        return id_planta;
    }

    public void setId_Planta(int numPlanta) 
    {
        this.id_planta = numPlanta;
    }

    public boolean isPulsado() 
    {
        boolean boton;
        cerrojo_boton.lock();
        try 
        {
            boton = pulsado;
        } 
        finally 
        {
            cerrojo_boton.unlock();
        }
        return boton;
    }

    public void setPulsado(boolean estaPulsado) 
    {
        cerrojo_boton.lock();
        try 
        {
            this.pulsado = estaPulsado;
        } 
        finally 
        {
            cerrojo_boton.unlock();
        }
    }

    public ArrayList<Persona> getPersonas() 
    {
        ArrayList<Persona> persona;
        cerrojo_persona.lock();
        try 
        {
            persona = personas;
        } 
        finally 
        {
            cerrojo_persona.unlock();
        }
        return persona;
    }
}
