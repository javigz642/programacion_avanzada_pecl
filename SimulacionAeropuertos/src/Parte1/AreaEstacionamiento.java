/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parte1;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguel
 */
public class AreaEstacionamiento {

    private Aeropuerto aeropuerto;
    
    private ArrayList<Avion> aviones = new ArrayList<>();
    
    private Semaphore control = new Semaphore(1);
    

    public AreaEstacionamiento(Aeropuerto aeropuerto) {

        this.aeropuerto = aeropuerto;
    }

    public void entrarArea(Avion avion) {

        try {
            control.acquire();
            aeropuerto.hangar.salirHangar(avion);
            aviones.add(avion);
            System.out.println(avion.getIdentificador() + " ha entrado al area de estacionamiento de " + aeropuerto.ciudad.getNombre());

        } catch (InterruptedException ex) {
            Logger.getLogger(Hangar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            control.release();
        }

    }

    public void salirArea(Avion avion) {

        try {
            control.acquire();
            aviones.remove(avion);
            System.out.println(avion.getIdentificador() + " ha salido del area de estacionamiento de " + aeropuerto.ciudad.getNombre());
        } catch (InterruptedException ex) {
            Logger.getLogger(Hangar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            control.release();
        }
    }

}
