/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tenista;

/**
 *
 * @author laura
 */
public class Tenista extends Thread
{
    private int id;
    private int sexo;  //0=M, 1=F
    private PistaTenis pista;

    public Tenista(int id, PistaTenis pista) 
    {
        this.id = id;
        this.pista = pista;
        
        if (id%2 == 0)
        {
            sexo = 0;
        }
        else
        {
            sexo = 1;
        }
    }

    public int getIid() 
    {
        return id;
    }

    public int getSexo() 
    {
        return sexo;
    }   
    
    @Override
    public void run()
    {
        pista.entrar(this);
        
    }
    
    public void jugar()
    {
        try 
        {
            sleep((int) (500 + 500 * Math.random()));
        } 
        catch (InterruptedException e) 
        {
            System.out.println("Error en jugar()");
        }
    }
}
