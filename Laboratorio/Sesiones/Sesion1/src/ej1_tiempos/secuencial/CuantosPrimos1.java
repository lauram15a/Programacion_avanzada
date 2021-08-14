package ej1_tiempos.secuencial;

// Calcula cuántos primos hay hasta 10.000.000 y muestra el tiempo que tarda en calcularlo y el tiempo que ha tardado cada uno de los tramos

import ej1_tiempos.secuencial.Primos;
import java.util.*;

public class CuantosPrimos1 {
    public static void main(String[] x){
           long t0 = (new Date()).getTime(); //t0=instante de inicio de los cálculos
           Primos p1 = new Primos(1,2000000);
           Primos p2 = new Primos(2000001,4000000);
           Primos p3 = new Primos(4000001,6000000);
           Primos p4 = new Primos(6000001,8000000);
           Primos p5 = new Primos(8000001,10000000);
           
           p1.calcular();
           long t1 = (new Date()).getTime(); //t1 = tiempo p1
           p2.calcular();
           long t2 = (new Date()).getTime(); //t2 = tiempo p2
           p3.calcular();
           long t3 = (new Date()).getTime(); //t3 = tiempo p3
           p4.calcular();
           long t4 = (new Date()).getTime(); //t4 = tiempo p4
           p5.calcular();
           long t5 = (new Date()).getTime(); //t5 = tiempo p5
           
           int n = p1.cuantos() + p2.cuantos() + p3.cuantos() + p4.cuantos() + p5.cuantos();
           long tFinal = (new Date()).getTime(); //t1=instante de final de los cálculos
           
           long pt1 = t1-t0;
           long pt2 = t2-t1;
           long pt3 = t3-t2;
           long pt4 = t4-t3;
           long pt5 = t5-t4;
           
           System.out.println("1er tramo --> " + pt1);
           System.out.println("2o tramo --> " + pt2);
           System.out.println("3er tramo --> " + pt3);
           System.out.println("4o tramo --> " + pt4);
           System.out.println("5o tramo --> " + pt5);
           System.out.println("\nSuma de los 5 procesos --> " + (pt1+pt2+pt3+pt4+pt5));
           System.out.println("\nTiempo total --> " + (tFinal-t0));
           System.out.println("\nNúmero de primos menores que 10.000.000: "+ n +" calculado en "+ (tFinal-t0) +" miliseg.");
    }
}
