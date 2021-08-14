package ejemplo1;

public class Visitante extends Thread
{
    Exposicion expo;
    
    public Visitante(int id, Exposicion expo)
    {
        super.setName(String.valueOf(id));
        this.expo=expo;
    }
    
    public void run()
    {
        try
        {
            sleep((int)(3000*Math.random()));
        } catch (InterruptedException e){ }
        expo.entrar(this); //Entra en la exposición, si hay hueco; y sino espera en la cola
        expo.mirar(this); //Está un tiempo dentro de la exposición
        expo.salir(this); //Sale de la exposición
    }
}
