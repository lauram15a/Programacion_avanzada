/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tenista;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author laura
 */
public class Pista {
    
    private ArrayList<Tenista> jugadores = new ArrayList();
    private int cont;
    private int numJugadores;
    private int numJugadoresMax;
    private Lock cerrojo = new ReentrantLock();
    
    
    
    
    
}
