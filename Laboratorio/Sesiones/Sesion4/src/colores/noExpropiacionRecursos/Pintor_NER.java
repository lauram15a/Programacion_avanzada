package colores.noExpropiacionRecursos;
import colores.*;
import java.awt.Color;
import javax.swing.JButton;
// Pintor_NER es un hilo que hace que el botón cambie de color siguiendo la secuencia:
// 1. Se pinta de negro durante un tiempo aleatorio que varía entre un mínimo de 0 y un máximo de 0.2 segundos.
// 2. Obtiene el primer color que le corresponde y pinta con ese color
// 3. Espera entre 0 y 0.05 segundos
// 4. Toma el segundo color, lo mezcla con el primero y pinta con la mezcla
// 5. Espera entre 0 y 0.1 seg. y vuelve a empezar el ciclo.
public class Pintor_NER extends Thread
{
    private JButton b;
    private Paleta_NER p;
    private int numPintor;
    private Color negro = Color.black;
    
    public Pintor_NER(JButton b, Paleta_NER p, int numPintor)
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
            
            Color uno = p.tomaColor1(numPintor);     //Toma de la paleta el primer color
            b.setBackground(uno);                   //y pinta el botón
            try
            {
                sleep((int)(50*Math.random()));       //Espera entre 0 y 0.05 seg.
            } catch (InterruptedException e){ }
            
            //NUEVO
            Color dos = p.tomaColor2(numPintor+1);     //Toma de la paleta el segundo color
            if(dos.equals(Color.black))
            {
                p.dejaColor(numPintor);
            }
            else
            {
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
}