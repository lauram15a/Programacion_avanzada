/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ej3_calculos;

import java.util.Date;

/**
 *
 * @author laura
 */
public class Calculos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {

        long t0 = (new Date()).getTime(); //t0=instante de inicio de los cálculos

        Multiplos7 p1 = new Multiplos7();
        SumaPrimos p2 = new SumaPrimos();
        Terminados35 p3 = new Terminados35();

        p1.start();
        p2.start();
        p3.start();

        try 
        {
            p1.join();
            long t1 = (new Date()).getTime(); //t0=instante de inicio de los cálculos
            p2.join();
            long t2 = (new Date()).getTime(); //t0=instante de inicio de los cálculos
            p3.join();
            long t3 = (new Date()).getTime(); //t0=instante de inicio de los cálculos

            System.out.println("\n ------------------------------------------ \n");

            System.out.println("El primero ha tardado: " + (t1 - t0));
            System.out.println("El segundo ha tardado: " + (t2 - t0));
            System.out.println("El tercero ha tardado: " + (t3 - t0));

        } catch (InterruptedException e) {
            System.out.println("Error");
        }

    }

}
