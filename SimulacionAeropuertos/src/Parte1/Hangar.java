/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parte1;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sombe
 */
public class Hangar {
    
    private Aeropuerto aeropuerto;
    
    private ArrayList<Avion> aviones = new ArrayList<>();
    
    private Semaphore control = new Semaphore(1);
    
        
    public Hangar(Aeropuerto aeropuerto){
        
        this.aeropuerto = aeropuerto;
    }
    
    
    public void entrarHangar(Avion avion){
        
        
        try {
            control.acquire();
            aviones.add(avion);
            control.release();
            System.out.println(avion.getIdentificador()+ " entrando HANGAR");

        } catch (InterruptedException ex) {
            Logger.getLogger(Hangar.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    public void salirHangar(Avion avion){
        
        try {
            control.acquire();
            aviones.remove(avion);
            control.release();
            System.out.println(avion.getIdentificador()+ " saliendo HANGAR");
        } catch (InterruptedException ex) {
            Logger.getLogger(Hangar.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void reposar (Avion avion){
        
        try {
            control.acquire();
            aviones.add(avion);
            control.release();
            System.out.println(avion.getIdentificador()+ " reposando HANGAR");
            //Thread.sleep((int) (Math.random() * 15000) + 15001);
        } catch (InterruptedException ex) {
            Logger.getLogger(Hangar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
