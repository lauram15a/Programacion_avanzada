/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ascensor;

import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Laura y Victor
 */
public class Main 
{
    public static void main(String[] args) throws InterruptedException, IOException 
    {
       
        Hospital h = new Hospital();
        Control c = new Control(h);
        
        try 
        {
            Registry registry = LocateRegistry.createRegistry(1099); //Arranca rmiregistry local en el puerto 1099
            Naming.rebind("//localhost/Metodos", c);   //rebind sólo funciona sobre una url del equipo local             
        } 
        catch (RemoteException | MalformedURLException ex) 
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // GENERADOR DE PERSONAS
        int i = 1;
        while ((i <= 6000) && (h.isEvacuacion() == false))
        {
            int origen_persona = (int) (20 * Math.random());
            Persona p = new Persona(h, i, origen_persona);
            p.start();
            sleep((int)(500 + Math.random() * 1500));
            h.getPlantas_Hospital()[origen_persona].getPersonas().add(p);
            i++;
        } 
    }
}
