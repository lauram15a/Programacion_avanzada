package ej1_tiempos.hilos;

/*
Modificar el Ejemplo 1 para que, además de calcular el tiempo necesario para calcular cuántos primos hay en total, 
se imprima el tiempo que ha tardado el cálculo de cada uno de los tramos. 
Verificar que el tiempo total es mayor o igual que la suma de los tiempos de los cinco tramos.
*/

// Calcula cuántos primos hay hasta 10.000.000 y muestra el tiempo que tarda en startlo y el tiempo que ha tardado cada uno de los tramos

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CuantosPrimos1 {
    
    public static void main(String[] x){
        
        long t0 = (new Date()).getTime(); //t0=instante de inicio de los cálculos

        PrimosThread1 p1 = new PrimosThread1(1,2000000, 1, t0);
        PrimosThread1 p2 = new PrimosThread1(2000001,4000000, 2, t0);
        PrimosThread1 p3 = new PrimosThread1(4000001,6000000, 3, t0);
        PrimosThread1 p4 = new PrimosThread1(6000001,8000000, 4, t0);
        PrimosThread1 p5 = new PrimosThread1(8000001,10000000, 5, t0);

        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
           
           
        try {
            
            p1.join();
            p2.join();
            p3.join();
            p4.join();
            p5.join();
            
        } catch (InterruptedException ex) {
            Logger.getLogger(CuantosPrimos1.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        int n = p1.cuantos() + p2.cuantos() + p3.cuantos() + p4.cuantos() + p5.cuantos();
        long t1 = (new Date()).getTime(); //t1=instante de final de los cálculos
        System.out.println("\n1: Número de primos menores que 10.000.000: "+ n +" calculado en "+ (t1-t0) +" miliseg.");
    }
}