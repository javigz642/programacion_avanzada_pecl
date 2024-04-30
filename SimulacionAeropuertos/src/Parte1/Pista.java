/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parte1;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguel
 */
public class Pista {
    
    private Aeropuerto aeropuerto;
    
    private ArrayList<Avion> aviones = new ArrayList<>();
    
    private Semaphore pistas = new Semaphore(4, true);

    public Pista(Aeropuerto aeropuerto) {
        this.aeropuerto = aeropuerto;
    }
    
    


    public void pedirPista(Avion avion) {
        
        try {
            if (avion.isEmbarque()) {
                accederPista(avion);
            } else {
                while (!pistas.tryAcquire()) {
                    System.out.println(avion.getIdentificador() + " dando un rodeo para pedir pista en el aeropuerto de " + aeropuerto.ciudad.getNombre());
                    Thread.sleep((int) (Math.random() * 4000) + 1001);
                }
                aterrizarPista(avion);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Pista.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void accederPista(Avion avion) {
        
        try {
            pistas.acquire();
            aeropuerto.areaRodaje.salirAreaRodaje(avion);
            aviones.add(avion);
            System.out.println(avion.getIdentificador() + " accediendo a la pista " + (aviones.indexOf(avion)+1) + " del aeropuerto de " + aeropuerto.ciudad.getNombre()); 
            System.out.println(avion.getIdentificador() + " realizando verificaciones");
            Thread.sleep((int) (Math.random() * 2000) + 1001);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Pista.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void aterrizarPista(Avion avion) {

        try {
            aviones.add(avion);
            System.out.println(avion.getIdentificador() + " aterrizando a la pista " + (aviones.indexOf(avion) + 1) + " del aeropuerto de " + aeropuerto.ciudad.getNombre());
            Thread.sleep((int) (Math.random() * 4000) + 1001);
            System.out.println(avion.getIdentificador() + " aterrizado en el aeropuerto de " + aeropuerto.ciudad.getNombre());
        } catch (InterruptedException ex) {
            Logger.getLogger(Pista.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void salirPista(Avion avion) {

        try {
            if(avion.isEmbarque()){
                System.out.println(avion.getIdentificador() + " despegando de la pista " + (aviones.indexOf(avion) + 1) + " del aeropuerto de " + aeropuerto.ciudad.getNombre());
                Thread.sleep((int) (Math.random() * 4000) + 1001);
            }
            else{
                System.out.println(avion.getIdentificador() + " saliendo de la pista " + (aviones.indexOf(avion) + 1) + " del aeropuerto de " + aeropuerto.ciudad.getNombre());            
            }
            aviones.remove(avion);
            pistas.release();
            System.out.println(avion.getIdentificador() + " ha abandonado la pista del aeropuerto de " + aeropuerto.ciudad.getNombre());
        } catch (InterruptedException ex) {
            Logger.getLogger(Pista.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
