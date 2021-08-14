package ejemplo3;

import java.awt.Color;

public class Paleta
{
    private Color [] colores = new Color[4];
    
    public Paleta()
    {
        colores[0]=Color.red;
        colores[1]=Color.blue;
        colores[2]=Color.green;
        colores[3]=Color.yellow;
    }
    public Color tomaColor()
    {
        int i = (int)(4 * Math.random());  //Elige color al azar
        return colores[i];
    }
}
