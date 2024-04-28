/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parte1;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Miguel
 */
public class Pista {
    
    private Aeropuerto aeropuerto;
    
    private Semaphore pistas = new Semaphore(4, true);

    public Pista(Aeropuerto aeropuerto) {
        this.aeropuerto = aeropuerto;
    }
    
    public void accederPista(Avion avion) throws InterruptedException{
        
        pistas.acquire();
        System.out.println(avion.getIdentificador() + " accediendo a pista del aeropuerto de " + aeropuerto.ciudad.getNombre());
        System.out.println(avion.getIdentificador() + " realizando verificaciones");
        Thread.sleep((int) (Math.random() * 2000) + 1001);
        despegar(avion);
        
    }
    
    private void despegar(Avion avion) throws InterruptedException{
        
        System.out.println(avion.getIdentificador() + " despegando de la pista del aeropuerto de " + aeropuerto.ciudad.getNombre());
        Thread.sleep((int) (Math.random() * 4000) + 1001);
        pistas.release();
        
    }
}
