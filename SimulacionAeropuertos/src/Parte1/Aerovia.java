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
public class Aerovia {
    
    private Aeropuerto aeropuerto;
    
    private ArrayList<Avion> aviones = new ArrayList<>();
    
    private Semaphore control = new Semaphore(1, true);

    public Aerovia(Aeropuerto aeropuerto) {
        this.aeropuerto = aeropuerto;
    }
    
    public void entrarAerovia(Avion avion) {
        

        try {
            control.acquire();
            aviones.add(avion);
            control.release();
            System.out.println(avion.getIdentificador() + " volando desde " + avion.getOrigen().getNombre() + " con destino " + avion.getDestino().getNombre() );
            //Thread.sleep((int) (Math.random() * 15000) +15001);
        } catch (InterruptedException ex) {
            Logger.getLogger(Aerovia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void abandonarAerovia(Avion avion) {
        

        try {
            control.acquire();
            aviones.remove(avion);
            control.release();
            System.out.println(avion.getIdentificador() + " llegando a " + avion.getDestino().getNombre() + " desde " + avion.getOrigen().getNombre());
        } catch (InterruptedException ex) {
            Logger.getLogger(Aerovia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    
}
