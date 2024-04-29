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
public class AreaRodaje {
    
    private Aeropuerto aeropuerto;
    
    private ArrayList<Avion> aviones = new ArrayList<>();
    
    private Semaphore control = new Semaphore(1);

    public AreaRodaje(Aeropuerto aeropuerto) {
        this.aeropuerto = aeropuerto;
    }
    
    public void entrarAreaRodaje(Avion avion){

        try {
            control.acquire();
            aviones.add(avion);
            control.release();
            System.out.println(avion.getIdentificador() + " ha entrado al area de rodaje del aeropuerto de " + aeropuerto.ciudad.getNombre());
            Thread.sleep((int) (Math.random() * 4000) + 1001);
        } catch (InterruptedException ex) {
            Logger.getLogger(AreaRodaje.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void salirAreaRodaje(Avion avion) {
        try {
            control.acquire();
            aviones.remove(avion);
            control.release();
            System.out.println(avion.getIdentificador() + " ha entrado al area de rodaje del aeropuerto de " + aeropuerto.ciudad.getNombre());
            Thread.sleep((int) (Math.random() * 4000) + 1001);
        } catch (InterruptedException ex) {
            Logger.getLogger(AreaRodaje.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
