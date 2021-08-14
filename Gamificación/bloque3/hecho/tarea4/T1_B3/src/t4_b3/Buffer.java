package t4_b3;

import java.util.ArrayList;

public class Buffer {

    ArrayList<Double> buffer = new ArrayList<>();
    private int capacidad = buffer.size();
    private double temperatura = 0;

    public synchronized void generarTemperatura() throws InterruptedException {

        while (capacidad == 10) {
            try{
                wait();
            }catch(Exception e){}
        }

        temperatura = Math.floor(Math.random() * (40 + 10 + 1) + (-10));
        buffer.add(temperatura);
        capacidad++;
        notifyAll();

    }

    public synchronized void mostrarTemperatura() throws InterruptedException {

        while (capacidad == 0) {
           try{
               wait();
           } catch(Exception e){}
        }

        System.out.println("La temperatura es de: " + temperatura + " ºC.");
        buffer.remove(temperatura);
        capacidad--;
        notifyAll();
    }

}
