package solucionPuente;

public class Vehiculo extends Thread
{
    int num;
    Puente puente;
    
    public Vehiculo(int num, Puente puente)
    {
        this.num=num;
        this.puente=puente;
    }
    
    public void run()
    {
        try
        {   
            puente.llegarPuente(num);
        } catch (InterruptedException e){ }
        
        puente.pasarPuente(num);
        try
        {
            sleep(1000);
        } catch(InterruptedException e){ }
        puente.salirPuente(num);
    }
    
    public int getNum() {
        return num;
    }
}
