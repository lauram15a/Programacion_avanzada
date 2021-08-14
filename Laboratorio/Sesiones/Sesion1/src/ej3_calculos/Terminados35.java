/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ej3_calculos;

/**
 *
 * @author laura
 */
public class Terminados35 extends Thread {

    public void suma35() {
        int result = 0;
        int inicio = 10000;

        while (inicio != 0) {
            if (terminado3(inicio) || terminado5(inicio)) {
                result = result + inicio;
            }
            inicio--;
        }
        System.out.println("Suma de los números terminados en 3 o en 5 entre el 1 y el 10000: " + result);
    }

    public boolean terminado3(int n) {
        return (n % 10 == 3);
    }

    public boolean terminado5(int n) {
        return (n % 10 == 5);
    }

    public void run() {
        suma35();
    }
}
