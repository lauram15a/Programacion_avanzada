/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ej1_tiempos.hilos;

import java.util.Date;

/**
 *
 * @author laura
 */
public class PrimosThread1 extends Thread 
{

    private int id, x, y, n = 0;
    private long ti;

    public PrimosThread1(int x, int y, int id, long ti) 
    {

        this.x = x;
        this.y = y;
        this.id = id;
        this.ti = ti;

    }

    private boolean esPrimo(int n) 
    {

        int raiz = (int) Math.sqrt((double) n);
        boolean primo = true;
        int i = 2;

        while (primo && i <= raiz) 
        {
            if (n % i == 0) 
            {
                primo = false;
            }
            i++;
        }
        return primo;
    }

    public void run() 
    {

        for (int i = x; i <= y; i++) 
        {
            if (esPrimo(i)) 
            {
                n++;
            }
        }
        long tf = (new Date()).getTime();
        System.out.println("Soy el hilo " + id + " y he tardado " + (tf - ti) + " milisegundos.");
    }

    public int cuantos() 
    {

        return n;

    }

}
