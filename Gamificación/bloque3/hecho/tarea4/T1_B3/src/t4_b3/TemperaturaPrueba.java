
package t4_b3;

/**
 *
 * @author Víctor
 */
public class TemperaturaPrueba {
    public static void main(String[] args) {
        
        Buffer temperatura = new Buffer();
        
        Sensor s1 = new Sensor(temperatura);
        Display d1 = new Display(temperatura);
        
        s1.start();
        d1.start();
    }
}