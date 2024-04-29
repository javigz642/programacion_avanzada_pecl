/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parte1;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Miguel
 */
public class Aerovia {
    
    private Aeropuerto aeropuerto;
    
    private ArrayList<Avion> aviones = new ArrayList<>();
    
    private Semaphore control = new Semaphore(1, true);

    public Aerovia(Aeropuerto aeropuerto) {
        this.aeropuerto = aeropuerto;
    }
    
    public void volar(Avion avion) throws InterruptedException{
        

        System.out.println(avion.getIdentificador() + " volando desde " + avion.getOrigen().getNombre() + " con destino " + avion.getDestino().getNombre());
        control.acquire();
        aviones.add(avion);
        control.release();
        Thread.sleep((int) (Math.random() * 15000) +15001);

    }
    
}
