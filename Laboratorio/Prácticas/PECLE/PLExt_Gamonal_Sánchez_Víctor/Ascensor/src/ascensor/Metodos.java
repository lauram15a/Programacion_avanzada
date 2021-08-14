/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ascensor;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Aquí se encuentran los métodos que necesitábamos de Control
 * Las interfaces "remota" de los modulos instancian una variable de esta clase
 * 
 * @author Laura y Victor
 */

public interface Metodos extends Remote
{
    //Modulo vigilante
    void metodoModuloVigilante() throws RemoteException;
    
    //Modulo evacuacion
    void metodoModuloEvacuacion() throws RemoteException;
}
