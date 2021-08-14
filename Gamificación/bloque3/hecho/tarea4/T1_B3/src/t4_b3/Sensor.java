package t4_b3;

public class Sensor extends Thread {

    Buffer t;

    public Sensor(Buffer t) {
        this.t = t;
    }

    public void run() {

        try {
            while (true) {
                t.generarTemperatura();

                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
        }
    }
}