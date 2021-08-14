/* 
* La clase Visitante establece información de los visitantes, como su número
* y el método run, al cual accederán los hilos para realizar su ejecución
* (entrar a la exposición, salir, mirar).
*/
package clases;

/**
 *
 * @author Laura y Víctor
 */
public class Visitante extends Thread
{
    Exposicion expo;
    Control c;
    
    public Visitante(int id, Exposicion expo, Control c)
    {
        super.setName(String.valueOf(id));
        this.expo=expo;
        this.c = c;
    }
    
    public void run()
    {
        try
        {
            sleep((int)(3000*Math.random()));
            if (expo.isAbierto())
            {
                expo.entrar(this); //Entra en la exposición, si hay hueco; y sino espera en la cola
                c.mirar();  //comprueba que pueda hacer eso, es decir, si se ha dado o no a "detener"
                expo.mirar(this); //Está un tiempo dentro de la exposición
                c.mirar();
                expo.salir(this); //Sale de la exposición
                c.mirar();
            }
        } 
        catch (InterruptedException e)
        {
            System.out.println("----------interrumpido:  " + e);
        }        
    }    
    
}
