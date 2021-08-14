/*
 * Programa que lanza cuatro lectores y un escritor.
 * que se comunican a través de un buzón de mensajes.
 * Debe comprobarse que no se pierden los mensajes ni se leen dos veces
 */
package ejemplo1;

public class PruebaBuzon1
{
    public static void main(String[] s)
    {
        //buzón
        Buzon buzonX = new Buzon();
        //productores
        Productor pedro = new Productor("Pedro ",5,buzonX);
        Productor juan = new Productor("Juan ",4,buzonX);
        Productor antonio = new Productor("Antonio ",6,buzonX);
        Productor luis = new Productor("Luis ",7,buzonX);
        //consumidor
        Consumidor jose = new Consumidor(22,buzonX);
        
        //inicio hilos
        pedro.start();
        juan.start();
        antonio.start();
        luis.start();
        jose.start();
    }
}
