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

    public void entrarPuerta(Avion avion, boolean embarque) {

        try {
            if (embarque) {

                if(puertaEmbarque.tryAcquire()){
                    System.out.println(avion.getIdentificador() + " ha entrado en la puerta de embarque de " + aeropuerto.ciudad.getNombre());
                }
                else{
                    puertasLibres.acquire();
                
                }              
            } else {
                if(puertaDesembarque.tryAcquire()){
                    System.out.println(avion.getIdentificador() + " ha entrado en la puerta de desembarque de " + aeropuerto.ciudad.getNombre());
                }
                else{
                    puertasLibres.acquire();
                }
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(PuertaEmbarque.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void PuertaEmbarque(Avion avion) throws InterruptedException {

        int max = avion.getPasajerosMax();
        int intentos = 0;
        
        avion.setPasajerosActual(aeropuerto.recogerPasajerosAvion(max));
        
        while(intentos < 3 && avion.getPasajerosActual() < max){
            
            avion.setPasajerosActual(aeropuerto.recogerPasajerosAvion(max - avion.getPasajerosActual()));
            
            intentos++;
            Thread.sleep((int) (Math.random() * 4000) + 1001);
            
        }    

    }

    public void PuertaDesembarque(Avion avion) {

    }
    
    public void PuertaLibre(Avion avion){
        
    }

}
