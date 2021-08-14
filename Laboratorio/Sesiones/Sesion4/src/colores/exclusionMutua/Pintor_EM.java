package colores.exclusionMutua;
import colores.*;
import java.awt.Color;
import javax.swing.JButton;
// Pintor_EM es un hilo que hace que el botón cambie de color siguiendo la secuencia:
// 1. Se pinta de negro durante un tiempo aleatorio que varía entre un mínimo de 0 y un máximo de 0.2 segundos.
// 2. Obtiene el primer color que le corresponde y pinta con ese color
// 3. Espera entre 0 y 0.05 segundos
// 4. Toma el segundo color, lo mezcla con el primero y pinta con la mezcla
// 5. Espera entre 0 y 0.1 seg. y vuelve a empezar el ciclo.
public class Pintor_EM extends Thread
{
    private JButton b;
    private Paleta_EM p;
    private int numPintor;
    private Color negro = Color.black;
    
    public Pintor_EM(JButton b, Paleta_EM p, int numPintor)
    {
        this.b=b;
        this.p=p;
        this.numPintor = numPintor;
    }
    
    public void run()
    {
        while (true)
        {
            b.setBackground(negro);                 //Pinta el botón de negro
            try
            {
                sleep((int)(200*Math.random()));    //Espera entre 0 y 0.2 seg.
            } catch (InterruptedException e){ }
            
            Color uno = p.tomaColor(numPintor);     //Toma de la paleta el primer color
            b.setBackground(uno);                   //y pinta el botón
            try
            {
                sleep((int)(50*Math.random()));       //Espera entre 0 y 0.05 seg.
            } catch (InterruptedException e){ }
            
            Color dos = p.tomaColor(numPintor+1);     //Toma de la paleta el segundo color
            Color mezcla = p.mezclaColores(uno, dos); //los mezcla
            b.setBackground(mezcla);                  //y pinta el botón
            try
            {
                sleep((int)(100*Math.random()));      //Espera entre 0 y 0.1 seg.
            } catch (InterruptedException e){ }
            p.dejaColor(numPintor);
            p.dejaColor(numPintor+1);
        }
    }
}