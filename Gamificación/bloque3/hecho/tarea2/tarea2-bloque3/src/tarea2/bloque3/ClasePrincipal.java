/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea2.bloque3;


/**
 *
 * @author 
 */
public class ClasePrincipal 
{

    public static void main(String[] args) {
        
        Comun comun = new Comun();
        
        Termometro pr1 = new Termometro(comun);
        Display pr2 = new Display(comun);
        
        pr1.start();
        pr2.start();
    }    
}
