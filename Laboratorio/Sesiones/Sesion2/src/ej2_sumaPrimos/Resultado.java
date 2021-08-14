// Un objeto de la clase Resultado es un BigInteger con un método sumar(int) 
package ej2_sumaPrimos;
import java.math.BigInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Resultado {
    
    private BigInteger suma = new BigInteger("0");
    private Lock cerrojo = new ReentrantLock();
    public BigInteger getSuma(){
        return suma;
    }

    public void sumar(int n){
        cerrojo.lock();
        BigInteger bn = new BigInteger(String.valueOf(n));
        suma = suma.add(bn);
        System.out.println("sumar " + String.valueOf(n) +" = "+ suma.toString());
        cerrojo.unlock();
    }
}
