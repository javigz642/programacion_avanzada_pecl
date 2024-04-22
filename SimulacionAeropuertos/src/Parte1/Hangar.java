/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parte1;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author sombe
 */
public class Hangar {
    
    private Queue<Avion> aviones = new ConcurrentLinkedQueue<Avion>();
    private String ciudad;
        
    public Hangar(String ciudad){
        
        this.ciudad = ciudad;
    }
    
    
    public void entrarHangar(Avion avion){
        
        
        aviones.offer(avion);
        System.out.println(avion.getIdentificador()+ " ha entrado al hangar de " + ciudad);
    }
    
}
