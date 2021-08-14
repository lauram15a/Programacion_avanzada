
package ascensor;

import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Laura y Victor
 */
public class Ascensor extends Thread
{
    
    private final int id_ascensor;
    private String estado;  //Parado, Bajando, Subiendo, Estropeado
    private final int capacidad;  // = 8
    private boolean esperando;
    private int planta_actual;
    private final ArrayList<Persona> personas_en_ascensor;
    private final Hospital hospital;
    
    // LOCKS 
    private final ReentrantLock cerrojo_estado = new ReentrantLock();
    private final ReentrantLock cerrojo_num_personas = new ReentrantLock();
    private final ReentrantLock cerrojo_plantas = new ReentrantLock();
    private final ReentrantLock cerrojo_espera = new ReentrantLock();
    private final ReentrantLock cerrojo_personas = new ReentrantLock();
    
    private final Lock cerrojo_bajar_personas = new ReentrantLock();
    private final Lock cerrojo_subir_personas = new ReentrantLock();
    
    
    public Ascensor(Hospital h, int id, boolean esperando) 
    {
        this.hospital = h;
        this.id_ascensor = id;
        this.estado = "P";
        this.capacidad = 8;
        this.esperando = esperando;
        this.planta_actual = (int) (21 * Math.random());
        this.personas_en_ascensor = new ArrayList<>();
    }    
    
    @Override
    public void run()
    {
        try {
            while (hospital.abierto())
            {
                if ("S".equals(getEstado())) 
                {
                    if (getPlanta_actual() < 21)
                    {
                        setPlanta_actual(getPlanta_actual() + 1);
                    }
                    bajarDelAscensor();
                    subirAlAscensor();
                    try 
                    {
                        hospital.sumarContadorMovimientos();
                    } 
                    catch (IOException ex) 
                    {
                        Logger.getLogger(Ascensor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try
                    {
                        sleep(500);
                    }
                    catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }
                else if ("B".equals(getEstado()))
                {
                    if (getPlanta_actual() >= 0)
                    {
                       setPlanta_actual(getPlanta_actual() - 1); 
                    }
                    bajarDelAscensor();
                    subirAlAscensor();
                    try 
                    {
                        hospital.sumarContadorMovimientos();
                    } 
                    catch (IOException ex) 
                    {
                        Logger.getLogger(Ascensor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try
                    {
                        sleep(500);
                    }
                    catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }
                else if ("P".equals(getEstado()))
                {
                    if(!isEsperando())
                    {
                        bajarDelAscensor();
                        subirAlAscensor();
                    }
                    try 
                    {
                        sleep(300);
                    } 
                    catch (InterruptedException e) 
                    {
                        System.out.println(e.getMessage());
                    }
                }
                else if ("E".equals(getEstado()))
                {
                    bajarAscensorEstropeado();
                    try 
                    {
                        sleep(new Random().nextInt(5001) + 10000);
                    } 
                    catch (InterruptedException e) 
                    {
                        System.out.println(e.getMessage());
                    }
                    setEstado("P");
                }
                cambioSentido();
            }
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Ascensor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //////////////////////////////////////////////////////////////////////////
    //GETTERS Y SETTERS
    /////////////////////////////////////////////////////////////////////////
        
    /**
     * Get the value of estado
     *
     * @return the value of estado
     */
    public String getEstado() 
    {
        String estado_aux;
        cerrojo_estado.lock();
        try 
        {
            estado_aux = estado;
        } 
        finally 
        {
            cerrojo_estado.unlock();
        }
        return estado_aux;
    }

    /**
     * Set the value of estado
     *
     * @param estado new value of estado
     */
    public void setEstado(String estado) 
    {
       cerrojo_estado.lock();
        try 
        {
            this.estado = estado;
        } 
        finally 
        {
            cerrojo_estado.unlock();
        }
    }

    /**
     * Get the value of esperando
     *
     * @return the value of esperando
     */
    public boolean isEsperando() 
    {
        boolean espera;
        cerrojo_espera.lock();
        try 
        {
            espera = esperando;
        } 
        finally 
        {
            cerrojo_espera.unlock();
        }
        return espera;
    }

    /**
     * Set the value of esperando
     *
     * @param esperando new value of esperando
     */
    public void setEsperando(boolean esperando) 
    {
        cerrojo_espera.lock();
        try 
        {
            this.esperando = esperando;
        } 
        finally 
        {
            cerrojo_espera.unlock();
        }
    }

    /**
     * Get the value of capacidad
     *
     * @return the value of capacidad
     */
    public int getCapacidad() 
    {
        return capacidad;
    }

    /**
     * Get the value of planta_actual
     *
     * @return the value of planta_actual
     */
    public int getPlanta_actual() 
    {
        int planta;
        cerrojo_plantas.lock();
        try 
        {
            planta = planta_actual;
        } 
        finally 
        {
            cerrojo_plantas.unlock();
        }
        return planta;
    }

    /**
     * Set the value of planta_actual
     *
     * @param planta_actual new value of planta_actual
     */
    public void setPlanta_actual(int planta_actual) 
    {
        cerrojo_plantas.lock();
        try 
        {
            this.planta_actual = planta_actual;
        } 
        finally 
        {
            cerrojo_plantas.unlock();
        }
    }

    /**
     * Get the value of num_personas
     *
     * @return the value of num_personas
     */
    public int getNum_personas() 
    {
        int num;
        cerrojo_num_personas.lock();
        try
        {
            num = personas_en_ascensor.size();
        } 
        finally 
        {
            cerrojo_num_personas.unlock();
        }
        return num;
    }
    
    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public int getId_ascensor()
    {
        return id_ascensor;
    }

    /**
     * Get the value of personas_en_ascensor
     *
     * @return the value of personas_en_ascensor
     */
    public ArrayList<Persona> getPersonas_en_ascensor() 
    {
        ArrayList<Persona> personas_aux = new ArrayList <>();
        cerrojo_personas.lock();
        try
        {
            personas_aux = personas_en_ascensor;
        } 
        finally 
        {
            cerrojo_personas.unlock();
        }
        return personas_aux;
    }
    
    
    ////////////////////////////////////////////////////////////////////////////
    // FUNCIONES
    ////////////////////////////////////////////////////////////////////////////
    
    /**
     * Se bajan las personas con ese destino
     * @param a 
     */
    private void bajarDelAscensor() 
    {
        cerrojo_bajar_personas.lock();
        try
        {
            int desplazamiento = 0;
            int veces = getPersonas_en_ascensor().size();
            for (int i = 0; i < veces; i++) 
            {
                Persona p = getPersonas_en_ascensor().get(i - desplazamiento);
            
                if (p.getDestino() == getPlanta_actual()) 
                {
                    getPersonas_en_ascensor().remove(p);
                    desplazamiento++;
                }
            }
        }
        finally
        {
            cerrojo_bajar_personas.unlock();
        }
    }
    
    /**
     * Se bajan todos los ocupantes del ascensor, pues el ascensor se ha estropeado
     * añadir esas personas a la planta actual
     */
    private void bajarAscensorEstropeado()
    {
        cerrojo_bajar_personas.lock();
        try
        {
            int desplazamiento = 0;
            int veces = getPersonas_en_ascensor().size();
            for (int i = 0; i < veces; i++) 
            {
                Persona p = getPersonas_en_ascensor().get(i - desplazamiento);
                Persona p_aux = new Persona (hospital, ("P" + p.getId_persona()), getPlanta_actual(), p.getDestino(), p.getSentido());
            
                hospital.getPlantas_Hospital()[getPlanta_actual()].getPersonas().add(p_aux);
                p_aux.start();
                desplazamiento++;
            }  
        }
        finally
        {
            cerrojo_bajar_personas.unlock();
        }   
    }
    
    /** Se suben al ascensor aquellas personas en la planta actual en la que nos encontramos
     *  Siempre y cuando la capacidad lo permita
     * @param a
     */    
    private synchronized void subirAlAscensor() 
    {
        for (int i = 0; i < hospital.getPlantas_Hospital()[getPlanta_actual()].getPersonas().size(); i++) 
        {
            Persona p = hospital.getPlantas_Hospital()[getPlanta_actual()].getPersonas().get(i);
            
            if (getNum_personas() < getCapacidad()) // si puedo meter gente
            {
                if (p.getSentido().equals("S")) // Si esa persona sube
                {
                    if (getEstado().equals("S")) // si ascensor esta subiendo
                    {
                        cerrojo_subir_personas.lock();
                        try
                        {
                            p.setEsta_en_ascensor(true); 
                            getPersonas_en_ascensor().add(p); //meto a la persona al ascensor
                            hospital.getPlantas_Hospital()[getPlanta_actual()].getPersonas().remove(p);// saco a la persona de la planta
                        }
                        finally 
                        {
                            cerrojo_subir_personas.unlock();
                        }
                    } 
                    else if (getEstado().equals("P") || getNum_personas() == 0) //si esta parado o no tiene personas
                    {
                        setEstado("S");
                        cerrojo_subir_personas.lock();
                        try
                        {
                            p.setEsta_en_ascensor(true); 
                            getPersonas_en_ascensor().add(p); //meto a la persona al ascensor
                            hospital.getPlantas_Hospital()[getPlanta_actual()].getPersonas().remove(p);// saco a la persona de la planta
                        }
                        finally 
                        {
                            cerrojo_subir_personas.unlock();
                        }
                    }
                }
                else // Baja
                {
                   if (getEstado().equals("B")) // si esta bajando
                    {
                        cerrojo_subir_personas.lock();
                        try
                        {
                            p.setEsta_en_ascensor(true); 
                            getPersonas_en_ascensor().add(p); //meto a la persona al ascensor
                            hospital.getPlantas_Hospital()[getPlanta_actual()].getPersonas().remove(p);// saco a la persona de la planta
                        }
                        finally 
                        {
                            cerrojo_subir_personas.unlock();
                        }
                    }
                    else if (getEstado().equals("P") || getNum_personas() == 0) //si esta parado o no tiene personas
                    {
                        setEstado("B");
                        cerrojo_subir_personas.lock();
                        try
                        {
                            p.setEsta_en_ascensor(true);  
                            getPersonas_en_ascensor().add(p); //meto a la persona al ascensor
                            hospital.getPlantas_Hospital()[getPlanta_actual()].getPersonas().remove(p);// saco a la persona de la planta
                        }
                        finally 
                        {
                            cerrojo_subir_personas.unlock();
                        }
                    }
                }
            }
        }
        hospital.getPlantas_Hospital()[getPlanta_actual()].setPulsado(false);
    }
    
    /**
     * cambio de sentido del ascensor en los siguientes casos:
     * "S" y llega a la planta 20
     * "B" y llega a la planta 0
     * @param a 
     */
    private void cambioSentido ()
    {
        if (("S".equals(getEstado())))
        {
            if (getNum_personas() == 0)
            {
                setEstado("P");
            }
            else 
            {
                if (getPlanta_actual() != 20)
                {
                    setEstado("S");
                }
                else
                {
                    setEstado("B");
                }
            }
        }        
        else if (("B".equals(getEstado())))
        {
            if (getNum_personas() == 0)
            {
                setEstado("P");
            }
            else
            {
                if (getPlanta_actual() != 0)
                {
                    setEstado("B");
                }
                else
                {
                    setEstado("S");
                }
            }
        }
        else if (("P".equals(getEstado())))
        {
            if (isEsperando())
            {
                setEstado("P");
            }
            else
            {
                if (hospital.getRegistro_llamadas().isEmpty())
                {
                    setEstado("P");
                }
                else if (hospital.getRegistro_llamadas().get(0) >= getPlanta_actual())
                {
                    setEstado("S");
                }    
                else
                {
                    setEstado("B");
                }
            }
        }
    }  
}
