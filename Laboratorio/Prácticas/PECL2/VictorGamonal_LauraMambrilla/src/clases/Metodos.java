/*
 * Aquí se encuentran los métodos que necesitábamos de Control
 * La interfaz "remota" moduloControl instancia una variable de esta clase
 */
package clases;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Laura y Víctor
 */
public interface Metodos extends Remote
{
    void abrir() throws RemoteException;
    void cerrar() throws RemoteException;
}
