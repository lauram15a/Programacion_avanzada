/* 
* La clase ListaThreads permite gestionar las listas de threads en los monitores,
* haciendo uso de los métodos meter y sacar threads de forma sincronizada.
* Cada vez que una lista se modifica, se imprime su nuevo contenido en el 
* JTextField que toma como parámetro el constructor.
*/
package clases;

/**
 *
 * @author Laura y Víctor
 */
import java.util.*;
import javax.swing.JTextField;

public class ListaThreads
{
    ArrayList<Visitante> lista; //hemos cambiado el arraylist --> ahora almacena Visitante
    JTextField tf;
    
    
    public ListaThreads(JTextField tf)
    {
        lista = new ArrayList<Visitante>();
        this.tf=tf;
    }
    
    /**
     * Introducimos hilo en el Array 
     * Tanto cola de espera como exposición 
     * @param v
     */
    public synchronized void meter(Visitante v)
    {
        lista.add(v);
        imprimir();
    }
    
    /**
     * Sacamos hilo del Array 
     * Tanto cola de espera como exposición
     * @param v 
     */
    public synchronized void sacar(Visitante v)
    {
        lista.remove(v);
        imprimir();
    }
    
    /**
     * Sacamos por pantalla el array
     * tanto cola de espera como exposición
     */
    public void imprimir()
    {
        String contenido="";
        for(int i=0; i<lista.size(); i++)
        {
           contenido=contenido+lista.get(i).getName()+" ";
        }
        tf.setText(contenido);
    }
}
