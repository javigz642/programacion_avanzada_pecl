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
    
    private Avion[] aviones = new Avion[4];
    
    private Semaphore pista1 = new Semaphore(1, true);
    private Semaphore pista2 = new Semaphore(1, true);
    private Semaphore pista3 = new Semaphore(1, true);
    private Semaphore pista4 = new Semaphore(1, true);
    private Semaphore[] pistas = {pista1, pista2, pista3, pista4};

    public Pista(Aeropuerto aeropuerto) {
        this.aeropuerto = aeropuerto;
    }
    
    public void pedirPista(Avion avion) {
        
        try {
            if (avion.isEmbarque()) {
                pistas[avion.getNumero()%4].acquire();
                aviones[avion.getNumero()%4] = avion;
                System.out.println(avion.getIdentificador() + " tiene libre la PISTA " + (avion.getNumero()+1) + " para despegar"); 
                despegar(avion);
                
            } else {
                while (!pistas[avion.getNumero()%4].tryAcquire()) {
                    System.out.println(avion.getIdentificador() + " dando un rodeo para tener pista libre");
                    Thread.sleep((int) (Math.random() * 4000) + 1001);
                }
                aviones[avion.getNumero()%4] = avion;
                System.out.println(avion.getIdentificador() + " tiene libre la PISTA " + (avion.getNumero()+1) + " para aterrizar");
                aterrizar(avion);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Pista.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void despegar(Avion avion){
        
        try {
            System.out.println(avion.getIdentificador() + " realizando verificaciones PISTA");
            Thread.sleep((int) (Math.random() * 2000) + 1001);
            System.out.println(avion.getIdentificador() + " despegando con " + avion.getPasajerosActual() + " pasajeros desde la PISTA " + (avion.getNumero()+1));
            Thread.sleep((int) (Math.random() * 4000) + 1001);
            System.out.println(avion.getIdentificador() + " ha dejado libre la PISTA " + (avion.getNumero()+1));
            aviones[avion.getNumero()%4] = null;
            pistas[avion.getNumero()%4].release();
            
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Pista.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }

    private void aterrizar(Avion avion) {

        try {
            System.out.println(avion.getIdentificador() + " aterrizando con " + avion.getPasajerosActual() + " pasajeros en la PISTA " + (avion.getNumero()+1));
            Thread.sleep((int) (Math.random() * 4000) + 1001); 
            System.out.println(avion.getIdentificador() + " ha dejado libre la PISTA " + (avion.getNumero()+1));
            aviones[avion.getNumero()%4] = null;
            pistas[avion.getNumero()%4].release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Pista.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


}
