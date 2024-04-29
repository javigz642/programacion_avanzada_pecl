/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parte1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguel
 */
public class PuertaEmbarque {

    protected Aeropuerto aeropuerto;
    
    private ArrayList<Avion> avionesLibres = new ArrayList<>();
    private ArrayList<Avion> avionEmbarque = new ArrayList<>();
    private ArrayList<Avion> avionDesembarque = new ArrayList<>();

    private Semaphore puertasLibres = new Semaphore(4, true);
    private Semaphore puertaEmbarque = new Semaphore(1, true);
    private Semaphore puertaDesembarque = new Semaphore(1, true);

    public PuertaEmbarque(Aeropuerto aeropuerto) {

        this.aeropuerto = aeropuerto;
    }

    public void entrarPuerta(Avion avion) {

        try {
            if (avion.isEmbarque()) {

                if (puertaEmbarque.tryAcquire()) {
                    aeropuerto.areaEstacionamiento.salirArea(avion);
                    avionEmbarque.add(avion);
                    System.out.println(avion.getIdentificador() + " ha entrado en la puerta de embarque del aeropuerto de " + aeropuerto.ciudad.getNombre());
                    PuertaEmbarque(avion);
                    avionEmbarque.remove(avion);
                    System.out.println(avion.getIdentificador() + " ha salido de la puerta de embarque del aeropuerto de " + aeropuerto.ciudad.getNombre());
                    puertaEmbarque.release();
                    aeropuerto.areaRodaje.entrarAreaRodaje(avion); //entra solo al Area de Rodaje
                    
                } else {
                    puertasLibres.acquire();
                    aeropuerto.areaEstacionamiento.salirArea(avion);
                    avionesLibres.add(avion);
                    System.out.println(avion.getIdentificador() + " ha entrado en la puerta libre del aeropuerto de " + aeropuerto.ciudad.getNombre());
                    PuertaLibre(avion);
                    avionesLibres.remove(avion);
                    System.out.println(avion.getIdentificador() + " ha salido de la puerta libre del aeropuerto de " + aeropuerto.ciudad.getNombre());
                    puertasLibres.release();
                    aeropuerto.areaRodaje.entrarAreaRodaje(avion); //entra solo al Area de Rodaje
                    
                    
                }
            } else {
                if (puertaDesembarque.tryAcquire()) {
                    aeropuerto.areaRodaje.salirAreaRodaje(avion);
                    System.out.println(avion.getIdentificador() + " yendo desde Area de Rodaje a Embarque del aeropuerto de " + aeropuerto.ciudad.getNombre());
                    Thread.sleep((int) (Math.random() * 2000) + 3001); //paso de Area de Rodaje a Embarque
                    avionDesembarque.add(avion);
                    System.out.println(avion.getIdentificador() + " ha entrado en la puerta de desembarque del aeropuerto de " + aeropuerto.ciudad.getNombre());
                    PuertaDesembarque(avion);
                    avionDesembarque.remove(avion);
                    System.out.println(avion.getIdentificador() + " ha salido de la puerta de desembarque del aeropuerto de " + aeropuerto.ciudad.getNombre());
                    puertaDesembarque.release();
                    aeropuerto.areaEstacionamiento.entrarArea(avion); //entra solo al Area de Estacionamiento
                    
                } else {
                    puertasLibres.acquire();
                    aeropuerto.areaRodaje.salirAreaRodaje(avion);
                    System.out.println(avion.getIdentificador() + " yendo desde Area de Rodaje a Embarque del aeropuerto de " + aeropuerto.ciudad.getNombre());
                    Thread.sleep((int) (Math.random() * 2000) + 3001); //paso de Area de Rodaje a Embarque
                    avionesLibres.add(avion);
                    System.out.println(avion.getIdentificador() + " ha entrado en la puerta libre del aeropuerto de " + aeropuerto.ciudad.getNombre());
                    PuertaLibre(avion);
                    avionesLibres.remove(avion);
                    System.out.println(avion.getIdentificador() + " ha salido de la puerta libre del aeropuerto de " + aeropuerto.ciudad.getNombre());
                    puertasLibres.release();
                    aeropuerto.areaEstacionamiento.entrarArea(avion); //entra solo al Area de Estacionamiento
                    
                }
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(PuertaEmbarque.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

    private void PuertaEmbarque(Avion avion) {

        try {
            int max = avion.getPasajerosMax();
            int intentos = 0;
            
            System.out.println(avion.getIdentificador() + " cogiendo pasajeros en el aeropuerto de " + aeropuerto.ciudad.getNombre());
            avion.setPasajerosActual(aeropuerto.recogerPasajerosAvion(max));
            
            while (intentos < 2 && avion.getPasajerosActual() < max) {
                
                avion.setPasajerosActual(aeropuerto.recogerPasajerosAvion(max - avion.getPasajerosActual()));
                
                intentos++;
                Thread.sleep((int) (Math.random() * 4000) + 1001);    
            }
            
        } catch (InterruptedException ex) {
            Logger.getLogger(PuertaEmbarque.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            puertaEmbarque.release();
        }

    }

    private void PuertaDesembarque(Avion avion) {
        
        System.out.println(avion.getIdentificador() + " dejando pasajeros en el aeropuerto de " + aeropuerto.ciudad.getNombre());
        aeropuerto.bajarPasajerosAvion(avion.getPasajerosActual());
        puertaDesembarque.release();

    }

    private void PuertaLibre(Avion avion) {

        if (avion.isEmbarque()) {
            PuertaEmbarque(avion);
        } else {
            PuertaDesembarque(avion);
        }
        puertasLibres.release();

    }
    
    

}
