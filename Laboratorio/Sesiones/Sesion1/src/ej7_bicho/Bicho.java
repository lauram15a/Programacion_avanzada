/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ej7_bicho;

import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author laura
 */
public class Bicho extends Thread
{
    private int generacion;
    private String nombre;
    private long tiempoVida;
        
    public Bicho (int generacion)
    {
        this.generacion = generacion;
    }
    
    public void nace()
    {
        if (generacion == 1)
        {
            nombre = "ADAN";
        } else {
            nombre = generarNombre();
        }
        tiempoVida = (new Date()).getTime();
        
        System.out.println("NACE: " + nombre + " - Generacion " + generacion);
        
        Random aleatorio = new Random();
        
        try
        {
            int tiempo = aleatorio.nextInt(501) + 500;  //Rango aleatorios int  (de 1000 a 1500)
            sleep(tiempo);
        } catch (InterruptedException e)
        {
            System.out.println("Error en el sleep " + e);
        }
        
        reproduce();
    }
    
    public void reproduce()
    {
        if (generacion != 5)
        {
//            nace(generacion + 1);
            Bicho bicho = new Bicho(generacion + 1);
            bicho.start();
            try {
                bicho.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Bicho.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public void muere(long t0)
    {
        System.out.println("MUERE: " + nombre + " - Generacion " + generacion + " - a los " + (tiempoVida - t0) + " milisegundos");
        
    }
    
    public static String generarNombre()
    {
        //rangos numéricos código Ascii (mayúsculas)
        int num1 = 65;
        int num2 = 90; 
        
        char palabra[] = new char[4]; 
        
        
        for (int i = 0; i < 4; i++)
        {
            int numAleatorio = (int)Math.floor(Math.random()*(num2 - num1) + num1);
            char char_letra = (char)numAleatorio;
            palabra[i] = char_letra;
        }
        
        String nombre = "";
        
        for (int i = 0; i < 4; i++)
        {
            String letra = Character.toString(palabra[i]);
            nombre = nombre + letra;
        }
        
        return nombre;
        
    }
    
    
    @Override
    public void run()
    {
        long t0 = (new Date()).getTime();
        nace();
        muere(t0);
    }
    
    
}
