/*
 * La clase Productor define hilos que envían mensajes a un buzón de mensajes.
 * Los mensajes constan de un prefijo String y un sufijo que es un entero del 1 al 5
 * El prefijo, el número de mensajes a escribir y el buzón donde hacerlo,
 *  se reciben como parámetros en el constructor.
 * Entre mensaje y mensaje, esperan un tiempo aleatorio entre 0.5 y 1 seg.
 */

package ejemplo1;

public class Productor extends Thread
{
    private String prefijo;
    private int numMensajes;
    private Buzon miBuzon;

    public Productor(String prefijo, int n, Buzon buzon)
    {
        this.prefijo=prefijo;
        numMensajes=n;
        miBuzon=buzon;
    }

    public void run()
    {
        for(int i=1; i<=numMensajes; i++)
        {
            try
            {
                sleep((int)(500+500*Math.random()));
            } catch(InterruptedException e){  }
            miBuzon.enviaMensaje(prefijo+i);
        }
    }
}
