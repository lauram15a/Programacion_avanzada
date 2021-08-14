/*
 * La clase Buzon tiene que estar protegida con un cerrojo
 * El método enviaMensaje debe esperar si el buzón está lleno
 * El método recibeMensaje debe esperar si el buzón está vacío.
 * Cuando un hilo completa su operación, desbloquea a los que estén esperando
 * para que puedan continuar intentando su acción.
 */
package ejemplo1;

public class Buzon
{
    private String mensaje;
    private boolean hayMensaje=false;

    public void enviaMensaje(String msg)  //debe esperar si el buzón está lleno
    {
        hayMensaje=true;
        mensaje=msg;
    }

    public String recibeMensaje()   //debe esperar si el buzón está vacío
    {
        hayMensaje=false;
        return mensaje;
    }
}