/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ascensor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Laura y Victor
 */
public final class Hospital 
{
    
    private final int num_movimientos = 5000; //  total de movimientos a hacer por el programa
    private int cont_movimientos = 0; // contador de movimientos actuales
    private final int num_ascensores = 3;
    
    private boolean evacuacion;

    private final Planta plantas_Hospital[] = new Planta[21];
    private final Ascensor ascensores[] = new Ascensor[3];
    
    //archivo
    private final FileWriter archivo;
    BufferedWriter bw;  
    
    // LOCKS
    private final Lock cerrojo_movimiento = new ReentrantLock();
    private final Lock cerrojo_pulsar_boton = new ReentrantLock();
    
    
    public Hospital() throws IOException 
    {        
        for (int i = 0; i < 21; i++)
        {
            plantas_Hospital[i] = new Planta(i);
        }
        
        for (int i = 0; i < 3; i++) 
        {
            if (i != 2)
            {
                ascensores[i] = new Ascensor(this, i+1, false);
            }
            else
            {
                ascensores[i] = new Ascensor(this, i+1, true);
            }
            ascensores[i].start();
        }
        
        EstropeaAscensor ea = new EstropeaAscensor(this);
        ea.start();
        
        //fichero
        String ruta = "archivo.txt"; 
        archivo = new FileWriter(ruta);      
        bw = new BufferedWriter(archivo);
        
        evacuacion = false;
        
        imprimir();
    }

    /**
     * se suman los movimientos
     * @throws IOException 
     */
    public void sumarContadorMovimientos() throws IOException 
    {
        cerrojo_movimiento.lock();
        try 
        {
            cont_movimientos++;
            imprimir();
        } 
        finally 
        {
            cerrojo_movimiento.unlock();
        }
    }   
 
    /**
     * Comprueba si el hospital esta abierto o no
     * lo hace mirando si los movimientos no han superado al num max de mov
     * Además, se cerrará si se evacua el hospital
     * @return 
     * @throws java.io.IOException 
     */
    public boolean abierto() throws IOException
    {
        if (!(cont_movimientos < num_movimientos)) //esto es que va a estar cerrado
        {
            bw.close();
        }
        if (evacuacion && todasLasPlantasVacias() && todosLosAscensoresVacios())
        {
            return false;
        }
        else
        {
            return cont_movimientos < num_movimientos;
        }
    }
    
    /**
     * vemos si todas las plantas estan vacias o no
     * @return 
     */
    private synchronized boolean todasLasPlantasVacias()
    {
        int comprobante = 0;
        
        for (int i = 0; i < plantas_Hospital.length; i++)
        {
            if (!plantas_Hospital[i].getPersonas().isEmpty())
            {
                comprobante++;
            }
        }
        
        return comprobante == 0;
    }
    
    /**
     * comprobamos si estan todos los ascensores vacios o no
     * @return 
     */
    private synchronized boolean todosLosAscensoresVacios()
    {
        int comprobante = 0;
        
        for (int i = 0; i < ascensores.length; i++)
        {
            if (!ascensores[i].getPersonas_en_ascensor().isEmpty())
            {
                comprobante ++;
            }
        }
        
        return comprobante == 0;
    }
    
    /**
     * La persona cuando llega a la planta pulsa el boton del ascensor
       Nos encontramos una zona critica --> locks 
     * @param planta_origen
     */
    public synchronized void pulsarBoton(int planta_origen)
    {
        cerrojo_pulsar_boton.lock();
        try 
        {
            plantas_Hospital[planta_origen].setPulsado(true);
              
        } 
        finally 
        {
            cerrojo_pulsar_boton.unlock();
        }
    }
    
    /////////////////////////////////////////////////////
    //IMPRIMIR
    /////////////////////////////////////////////////////
    
    public synchronized void imprimir() throws IOException 
    {
        System.out.println("----------------------------------------------------------------------------\nMovimiento nº " + getCont_movimientos() + "\n");
        bw.write("----------------------------------------------------------------------------\nMovimiento nº " + getCont_movimientos() + "\n" + "\n");  
        
        System.out.println("Piso    Asc.1    Asc.2    Asc.3    Botón pulsado:    Destinos Asc.1    Destinos Asc.2    Destinos Asc.3    Personas planta");
        bw.write("\nPiso    Asc.1    Asc.2    Asc.3    Botón pulsado:    Destinos Asc.1    Destinos Asc.2    Destinos Asc.3    Personas planta\n");
        
        for (int i = plantas_Hospital.length - 1; i >= 0; i--) 
        { 
            System.out.print(" ");
            bw.write(" ");
            
            if (plantas_Hospital[i].getId_Planta() < 10) 
            {
                System.out.print(" ");
                bw.write(" ");
            }
            
            System.out.print(plantas_Hospital[i].getId_Planta() + "      ");
            bw.write(plantas_Hospital[i].getId_Planta() + "      ");
            
            for (int j = 0; j < 3; j++) 
            {
                if (ascensores[j].getPlanta_actual() == plantas_Hospital[i].getId_Planta()) //misma planta
                {  
                    if ("E".equals(ascensores[j].getEstado())) 
                    {
                        System.out.print(" E ");
                        bw.write(" E ");
                    } 
                    else 
                    {
                        System.out.print(ascensores[j].getEstado() + "#" + ascensores[j].getNum_personas());
                        bw.write(ascensores[j].getEstado() + "#" + ascensores[j].getNum_personas());
                    }
                } 
                else  //distinta planta
                {
                    System.out.print(" | ");
                    bw.write(" | ");
                }
                System.out.print("      ");
                bw.write("      ");
            }
            
            if (plantas_Hospital[i].isPulsado()) 
            {
                System.out.print("   --Sí");
                bw.write("   --Sí");
            } 
            else 
            {
                System.out.print("     No");
                bw.write("     No");
            }
            
            System.out.print("          ");
            bw.write("          ");
            
            for (int j = 0; j < num_ascensores; j++)
            {
                ArrayList<Persona> personas_ascensor = new ArrayList<>();
                for (int k = 0; k < ascensores[j].getNum_personas(); k++) 
                {
                    if (ascensores[j].getPersonas_en_ascensor().get(k).getDestino() == i) 
                    {
                        personas_ascensor.add(ascensores[j].getPersonas_en_ascensor().get(k));
                    }
                }
                
                int n_espacios = 7;
                
                if (personas_ascensor.size() > 1) 
                {
                    n_espacios = Math.max((14 - (personas_ascensor.size() * String.valueOf(ascensores[j].getPersonas_en_ascensor().get(0).getId_persona()).length() + (personas_ascensor.size() - 1) * 2)) / 2, 0);
                } 
                else if (personas_ascensor.size() == 1) 
                {
                    n_espacios = (14 - personas_ascensor.size() * String.valueOf(ascensores[j].getPersonas_en_ascensor().get(0).getId_persona()).length()) / 2;
                }
                
                for (int k = 0; k < n_espacios; k++) 
                {
                    System.out.print(" ");
                    bw.write(" ");
                }
                
                for (int k = 0; k < personas_ascensor.size(); k++) 
                {
                    System.out.print(personas_ascensor.get(k).getId_persona());
                    bw.write(personas_ascensor.get(k).getId_persona());
                    if (personas_ascensor.size() - 1 != k) {
                        System.out.print(", ");
                        bw.write(", ");
                    }
                }
                
                for (int k = 0; k < n_espacios; k++) 
                {
                    System.out.print(" ");
                    bw.write(" ");
                }
                System.out.print("    ");
                bw.write("    ");
            }
            
            for (int j = 0; j < plantas_Hospital[i].getPersonas().size(); j++) 
            {
                System.out.print(plantas_Hospital[i].getPersonas().get(j).getId_persona());
                bw.write(plantas_Hospital[i].getPersonas().get(j).getId_persona());
                if (plantas_Hospital[i].getPersonas().size() - 1 != j) 
                {
                    System.out.print(", ");
                    bw.write(", ");
                }
            }
            
            System.out.println("");
            bw.write("\n");
        }
        
        System.out.println("");
        bw.write("\n");
        for (int i = 0; i < ascensores.length; i++)
        {
            System.out.println("ASCENSOR: " + ascensores[i].getId_ascensor() + " - Estado: " + ascensores[i].getEstado() + " - Nº personas: " + ascensores[i].getNum_personas() + " - ** Planta Actual ** - " + ascensores[i].getPlanta_actual());
            bw.write("ASCENSOR: " + ascensores[i].getId_ascensor() + " - Estado: " + ascensores[i].getEstado() + " - Nº personas: " + ascensores[i].getNum_personas() + " - ** Planta Actual ** - " + ascensores[i].getPlanta_actual() + "\n");
            
            System.out.println("// PERSONAS DENTRO ");
            bw.write("// PERSONAS DENTRO \n");
            
            for (int j = 0; j < ascensores[i].getPersonas_en_ascensor().size(); j++)
            {
                System.out.println(ascensores[i].getPersonas_en_ascensor().get(j).getId_persona() + " - Origen: " + ascensores[i].getPersonas_en_ascensor().get(j).getOrigen() + " - Destino: " + ascensores[i].getPersonas_en_ascensor().get(j).getDestino());
                bw.write(ascensores[i].getPersonas_en_ascensor().get(j).getId_persona() + " - Origen: " + ascensores[i].getPersonas_en_ascensor().get(j).getOrigen() + " - Destino: " + ascensores[i].getPersonas_en_ascensor().get(j).getDestino() + "\n");      
            }
            System.out.println("-------------------------------------");
            bw.write("-------------------------------------\n");
        }
        bw.write("\n");
        bw.write("\n");
    }
    
    ///////////////////////////////////////////////////////////////
    // GETTERS Y SETTERS
    ///////////////////////////////////////////////////////////////
     public BufferedWriter getBW()
    {
        return bw;
    }

    public int getNum_movimientos() 
    {
        return num_movimientos;
    }
    
    public int getCont_movimientos() 
    {
        int cont;
        cerrojo_movimiento.lock();
        try
        {
            cont = cont_movimientos;
        } 
        finally 
        {
            cerrojo_movimiento.unlock();
        }
        return cont;
    }
    
    public synchronized Planta[] getPlantas_Hospital() 
    {
        return plantas_Hospital;
    }
    
    public Ascensor[] getAscensores() 
    {
        return ascensores;
    }
    
    public void setEvacuacion(boolean e)
    {
        evacuacion = e;
    }
    
    public boolean isEvacuacion()
    {
        return evacuacion;
    }
    
    public ArrayList<Integer> getRegistro_llamadas() 
    {
        ArrayList<Integer> llamadas = new ArrayList<>();
        
        for (int i = 0; i < 21; i++)
        {
            if (plantas_Hospital[i].isPulsado())
            {
                llamadas.add(i);
            }
        }
        return llamadas;
    }
}