/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tenista;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author laura
 */
public class PistaTenis 
{
    private int numPersonas;
    private int numPersonasMax = 2;
    private ArrayList<Tenista> jugadores = new ArrayList();
    private ArrayList<Tenista> lista_espera = new ArrayList();
    private Lock cerrojo = new ReentrantLock();
    private Condition pistaLlena = cerrojo.newCondition();
    private Condition pistaVacia = cerrojo.newCondition();
    private Condition pistaM = cerrojo.newCondition();
    private Condition pistaF = cerrojo.newCondition();
    private int cont = 0;
    
 
    public void entrar(Tenista tenista)
    {
        cerrojo.lock();
        lista_espera.add(tenista);
        try
        {
            cont++;
            System.out.println("\n" + cont + "-----------------------------------------------------------------------------------------------------");
            try
            {
                //pista llena
                while (numPersonas == numPersonasMax)
                {
                    System.out.println("...lo siento, pista llena...");
                    lista_espera.add(tenista);
                    imprimirEspera();
                    pistaLlena.await();
                }
            }
            catch (InterruptedException e){}

            //1 jugador pista
            while (numPersonas == 1)
            {
                if (jugadores.get(0).getSexo() != tenista.getSexo())
                {
                    if (tenista.getSexo() == 1) 
                    {
                        try 
                        {
                            //hombre en pista
                            while (jugadores.get(0).getSexo() == 1) //el que está en la pista es un hombre
                            {
                                System.out.println("...lo siento, él es hombre y tú mujer...");
                                lista_espera.add(tenista);
                                imprimirEspera();
                                pistaM.await();
                            }
                        }
                        catch (InterruptedException e){}
                    } 
                    else 
                    {
                        try 
                        {
                            //mujer en pista
                            while (jugadores.get(0).getSexo() == 0) //el que está en la pista es un hombre
                            {
                                System.out.println("...lo siento, ella es mujer y tú hombre...");
                                lista_espera.add(tenista);
                                imprimirEspera();
                                pistaF.await();
                            }
                        }
                        catch (InterruptedException e){}

                    }
                }
                jugadores.add(tenista);
                numPersonas++;
                imprimirEspera();
                imprimirPista();                
                jugada();
            }
            //pista vacia
            while (numPersonas == 0)
            {
                if (tenista.getSexo() == 1)   //mujer 
                {
                    pistaF.signal();
                }
                else
                {
                    pistaM.signal();
                }
                pistaVacia.signal();
                jugadores.add(tenista);
                imprimirPista();
                numPersonas++;
            }
            
        }
        finally
        {
            System.out.println("holi");
            imprimirPista();
            imprimirEspera();
            cerrojo.unlock();
        }
        
    }
    
    public void salir()
    {
        cerrojo.lock();
        try
        {
            for (int i = 0; i < jugadores.size(); i++)
            {
                jugadores.remove(i);
                numPersonas--;
                System.out.println("saliendo " + (i+1) + "...");
            }
            try
            {
                pistaVacia.await();
            }
            catch(InterruptedException e){}
        }
        finally
        {
            imprimirPista();
            pistaLlena.signal();
            cerrojo.unlock();
        }
    }
    
    public void imprimirPista()
    {
        System.out.println("\nPISTA");
        if (jugadores.size() > 0)
        {
            for (int i = 0; i < jugadores.size(); i++) 
            {
                  System.out.println("-Jugador " + (i+1) + " --> id: " + jugadores.get(i).getIid() + "   sexo: " + jugadores.get(i).getSexo());
            }
        }
        else
        {
            System.out.println("Fin del juego");
        }
    }
    
    public void imprimirEspera()
    {
        System.out.println("\nESPERA");
        for (int i = 0; i < lista_espera.size(); i++) 
        {
              System.out.println("-" + (i+1) + " --> id: " + lista_espera.get(i).getIid() + "   sexo: " + lista_espera.get(i).getSexo());
        }
            }
    
    public void jugada()
    {
        System.out.println("jugando...");
        Tenista j = jugadores.get(0);
        j.jugar();
        salir();
    }
}
