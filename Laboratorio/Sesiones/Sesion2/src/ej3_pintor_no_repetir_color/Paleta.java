package ej3_pintor_no_repetir_color;

import java.awt.Color;
import static java.nio.file.Files.delete;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Paleta {

    private Color[] colores = new Color[4];
    private Lock cerrojo = new ReentrantLock();
    private Boolean[] disponible = new Boolean[]{true, true, true, true};

    public Paleta() {
        colores[0] = Color.red;
        colores[1] = Color.blue;
        colores[2] = Color.green;
        colores[3] = Color.yellow;
    }

    public Color tomaColor() {
        try {
            cerrojo.lock();

            boolean parar = false;
            int i = 0;
            while (!parar) {
                i = (int) ((colores.length - 1) * Math.random());  //Elige color al azar
                if (disponible[i]) {

                    parar = true;
                    disponible[i] = false;
                }
            }
            //disponible[i] = si_disponible(disponible, i);
            return colores[i];
        } finally {

            cerrojo.unlock();

        }
    }

        
    public void setDisponiblePosicion (Color color)
    {
        int pos = 0;
        for (int i = 0; i < disponible.length ; i++){
            if (color == colores[i]){
                pos = i;
            }
        }
        disponible[pos] = !disponible[pos];
    }

}
