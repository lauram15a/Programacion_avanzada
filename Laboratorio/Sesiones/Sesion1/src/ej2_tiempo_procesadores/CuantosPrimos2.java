package ej2_tiempo_procesadores;

/*
Modificar el Ejemplo 2 en la misma forma que en el ejercicio anterior. 
Comprobar que si el ordenador tiene 2 o más procesadores o núcleos, ahora la suma 
de los tiempos parciales es mucho menor mayor que el tiempo total. 
Cuantos más procesadores tenga el ordenador, mayor será la diferencia. 
Estudiar la relación entre la ganancia de tiempo de ejecución y el número de procesadores o núcleos.
*/


// Calcula cuántos primos hay hasta 10.000.000 y muestra el tiempo que tarda en calcularlo

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CuantosPrimos2 {
    public static void main(String[] x){
           
        long t0 = (new Date()).getTime(); //t0=instante de inicio de los cálculos
        
        PrimosThread2 p1 = new PrimosThread2(1, 2000000, 1, t0);
        PrimosThread2 p2 = new PrimosThread2(2000001,4000000, 2, t0);
        PrimosThread2 p3 = new PrimosThread2(4000001,6000000, 3, t0);
        PrimosThread2 p4 = new PrimosThread2(6000001,8000000, 4, t0);
        PrimosThread2 p5 = new PrimosThread2(8000001,10000000, 5, t0);

        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();

        try{

            p1.join(); 
            p2.join(); 
            p3.join(); 
            p4.join(); 
            p5.join(); //esperamos a que terminen todos

        } catch (InterruptedException ex){
            Logger.getLogger(CuantosPrimos2.class.getName()).log(Level.SEVERE, null, ex);
        }

        int n = p1.cuantos() + p2.cuantos() + p3.cuantos() + p4.cuantos() + p5.cuantos();
        long t1 = (new Date()).getTime(); //t1=instante de final de los cálculos
        System.out.println("\n2: Número de primos menores que 10.000.000: "+ n +" calculado en "+ (t1-t0) +" miliseg.");
    }
}