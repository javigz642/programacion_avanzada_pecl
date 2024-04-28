/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parte1;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguel
 */
public class PuertaEmbarque {
    
    protected Aeropuerto aeropuerto;

    private Semaphore puertasLibres = new Semaphore(4, true);
    private Semaphore puertaEmbarque = new Semaphore(1, true);
    private Semaphore puertaDesembarque = new Semaphore(1, true);

    

    public PuertaEmbarque(Aeropuerto aeropuerto) {

        this.aeropuerto = aeropuerto;
    }

    public void entrarPuerta(Avion avion) {

        try {
            if (avion.isEmbarque()) {

                if(puertaEmbarque.tryAcquire()){
                    System.out.println(avion.getIdentificador() + " ha entrado en la puerta de embarque del aeropuerto de " + aeropuerto.ciudad.getNombre());
                    PuertaEmbarque(avion);
                }
                else{
                    puertasLibres.acquire();
                    System.out.println(avion.getIdentificador() + " ha entrado en la puerta libre del aeropuerto de " + aeropuerto.ciudad.getNombre());
                    PuertaLibre(avion);
                
                }              
            } else {
                if(puertaDesembarque.tryAcquire()){
                    System.out.println(avion.getIdentificador() + " ha entrado en la puerta de desembarque del aeropuerto de " + aeropuerto.ciudad.getNombre());
                    PuertaDesembarque(avion);
                }
                else{
                    puertasLibres.acquire();
                    System.out.println(avion.getIdentificador() + " ha entrado en la puerta libre del aeropuerto de " + aeropuerto.ciudad.getNombre());
                    PuertaLibre(avion);
                }
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(PuertaEmbarque.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void PuertaEmbarque(Avion avion) throws InterruptedException {

        int max = avion.getPasajerosMax();
        int intentos = 0;
        
        avion.setPasajerosActual(aeropuerto.recogerPasajerosAvion(max));
        
        while(intentos < 2 && avion.getPasajerosActual() < max){
            
            avion.setPasajerosActual(aeropuerto.recogerPasajerosAvion(max - avion.getPasajerosActual()));
            
            intentos++;
            Thread.sleep((int) (Math.random() * 4000) + 1001);
            
        }
        puertaEmbarque.release();

    }

    private void PuertaDesembarque(Avion avion) {
        
        puertaDesembarque.release();
    }
    
    private void PuertaLibre(Avion avion) throws InterruptedException{
        
        if (avion.isEmbarque()){
            PuertaEmbarque(avion);
        }
        else{
            PuertaDesembarque(avion);
        }
        puertasLibres.release();
        
    }

}
