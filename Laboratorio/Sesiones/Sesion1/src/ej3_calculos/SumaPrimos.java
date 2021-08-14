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
public class SumaPrimos extends Thread {

    public void sumaNumPrimos() {
        int result = 0;
        int inicio = 10000;

        while (inicio != 0) {
            if (esPrimo(inicio)) {
                result = result + inicio;
            }
            inicio--;
        }
        System.out.println("Suma de los números primos entre el 1 y el 10000: " + result);
    }

    public boolean esPrimo(int n) {
        if (n == 1 || n == 2) {
            return true;
        } else {
            int comprobante = 1;
            for (int i = 2; i < n; i++) {
                if (n % i == 0) {
                    comprobante = comprobante * 0;
                }
            }
            return comprobante == 1;
        }
    }

    public void run() {
        sumaNumPrimos();
    }
}
