/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tenista;

/**
 *
 * @author laura
 */
public class ClasePrincipal {

    
    public static void main(String[] args) 
    {
        PistaTenis pista = new PistaTenis();
        
        for (int i=0; i<10; i++)
        {
            Tenista tenista = new Tenista(i, pista);
            tenista.start();
        }
    }
    
}
