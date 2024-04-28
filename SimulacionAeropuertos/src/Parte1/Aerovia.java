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
public class Aerovia {
    
    Ciudad origen;

    
    private Semaphore control = new Semaphore(1, true);

    public Aerovia(Ciudad origen) {
        this.origen = origen;
    }
    
    public void volar(Avion avion) throws InterruptedException{
        

        System.out.println(avion.getIdentificador() + " volando desde " + avion.getOrigen().getNombre() + " con destino " + avion.getDestino().getNombre());
        Thread.sleep((int) (Math.random() * 15000) +15000);

    }
    
}
