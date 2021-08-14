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
public class Multiplos7 extends Thread{
    
    public void sumaMultiplos7 ()
    {
        int result = 0;
        int inicio = 10000;
        
        while (inicio != 0)
        {
            if (inicio % 7 == 0)
            {
                result = result + inicio;
            }
            inicio --;
        }
        
        System.out.println("Suma de los múltiplos de 7 entre el 1 y el 10000: " + result);
    }
    
    public void run()
    {
        sumaMultiplos7();
    }
}
