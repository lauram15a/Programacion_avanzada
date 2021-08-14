package t4_b3;

public class Display extends Thread {

    Buffer t;

    public Display(Buffer t) {
        this.t = t;
    }

    public void run() {

        try {
            while (true) {
                t.mostrarTemperatura();

                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
        }
    }
}