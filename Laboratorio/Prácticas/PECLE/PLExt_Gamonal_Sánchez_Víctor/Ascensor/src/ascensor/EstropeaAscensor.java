/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ascensor;

import static java.lang.Thread.sleep;

/**
 *
 * @author Laura y Víctor
 */
public class EstropeaAscensor extends Thread
{
    
    private final Hospital hospital;
    
    public EstropeaAscensor(Hospital h)
    {
        hospital = h;
    }
    
    /**
     * se controlan los ascensores
     * @param n 
     */
    private void controlarAscensores(int n) 
    {
        for (int i = 0; i < hospital.getAscensores().length; i++) 
        {
            if (hospital.getAscensores()[i].getId_ascensor() == n) 
            {
                hospital.getAscensores()[i].setEsperando(true);
                hospital.getAscensores()[i].setEstado("E");
            } 
            else 
            {
                hospital.getAscensores()[i].setEsperando(false);
            }
        }
    }
    
    @Override
    public void run() 
    {
        while (hospital.getCont_movimientos() < hospital.getNum_movimientos()) 
        {
            try 
            {
                sleep((int) (10000 + 10000 * Math.random()));
            } 
            catch (InterruptedException e) 
            {
                System.out.println(e.getMessage());
            }
            int ascensor = (int) (Math.random() * 3) + 1;
            while (hospital.getAscensores()[ascensor - 1].isEsperando()) 
            {
                ascensor = (int) (Math.random() * 3) + 1;
            }
            controlarAscensores(ascensor);
        }
    }
}
