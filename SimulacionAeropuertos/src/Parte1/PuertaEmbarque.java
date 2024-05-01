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

    private Avion[] aviones = new Avion[6];

    private Semaphore puertaEmbarque = new Semaphore(1, true);
    private Semaphore puertaDesembarque = new Semaphore(1, true);
    
    private Semaphore puertaLibre1 = new Semaphore(1, true);
    private Semaphore puertaLibre2 = new Semaphore(1, true);
    private Semaphore puertaLibre3 = new Semaphore(1, true);
    private Semaphore puertaLibre4 = new Semaphore(1, true);
    private Semaphore[] puertasLibres = {puertaLibre1, puertaLibre2, puertaLibre3, puertaLibre4};
    
    //textfield
    
    private String nombreCiudad;
    private TextLog logger;

    public PuertaEmbarque(String nombreCiudad, TextLog logger) {
        this.nombreCiudad = nombreCiudad;
        this.logger = logger;
    }


    public void entrarPuerta(Avion avion) {

        try {
            if (avion.isEmbarque()) {

                if (puertaEmbarque.tryAcquire()) {
                    aviones[4] = avion;
                    System.out.println(avion.getIdentificador() + " entrando Gate 5 (exclusiva Embarque)");
                    puertaEmbarque(avion);
                    aviones[4] = null;
                    System.out.println(avion.getIdentificador() + " saliendo Gate 5 (exclusiva Embarque) con " + avion.getPasajerosActual() + " pasajeros");
                    puertaEmbarque.release();

                } else {
                    puertasLibres[avion.getNumero()%4].acquire();
                    aviones[avion.getNumero()%4] = avion;
                    System.out.println(avion.getIdentificador() + " entrando Gate " + (avion.getNumero()%4 + 3) + " para Embarque");
                    puertaEmbarque(avion);
                    System.out.println(avion.getIdentificador() + " saliendo Gate " + (avion.getNumero()%4 + 3) + " con " + avion.getPasajerosActual() + " pasajeros");
                    aviones[avion.getNumero()%4] = null;
                    puertasLibres[avion.getNumero()%4].release();

                }
            } else {
                if (puertaDesembarque.tryAcquire()) {
                    avion.getDestino().aeropuerto.areaRodaje.salirAreaRodaje(avion); //tiene que tener una puerta de desembarque libre antes de avanzar
                    System.out.println(avion.getIdentificador() + " tiene Gate 6 libre. Yendo desde RODAJE hacia Desembarque");
                    Thread.sleep((int) (Math.random() * 2000) + 3001); //paso de Area de Rodaje a Desembarque
                    aviones[5] = avion;
                    System.out.println(avion.getIdentificador() + " entrando Gate 6 (exclusiva Desembarque)");
                    puertaDesembarque(avion);
                    aviones[5] = null;
                    System.out.println(avion.getIdentificador() + " saliendo Gate 6 (exclusiva Desembarque)");;
                    puertaDesembarque.release();

                } else {
                    puertasLibres[avion.getNumero()%4].acquire();
                    avion.getDestino().aeropuerto.areaRodaje.salirAreaRodaje(avion); //tiene que tener una puerta de desembarque libre antes de avanzar
                    System.out.println(avion.getIdentificador() + " tiene Gate " + (avion.getNumero()%4 + 3) + " libre. Yendo desde RODAJE hacia Desembarque");
                    Thread.sleep((int) (Math.random() * 2000) + 3001); //paso de Area de Rodaje a Desembarque
                    aviones[avion.getNumero()%4] = avion;;
                    System.out.println(avion.getIdentificador() + " entrando Gate " + (avion.getNumero()%4 + 3) + " para Desembarque de " + avion.getPasajerosActual() + " pasajeros");
                    puertaDesembarque(avion);
                    aviones[avion.getNumero()%4] = null;
                    System.out.println(avion.getIdentificador() + " saliendo Gate " + (avion.getNumero()%4 + 3));
                    puertasLibres[avion.getNumero()%4].release();
                }
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(PuertaEmbarque.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void puertaEmbarque(Avion avion) {

        try {
            int max = avion.getPasajerosMax();
            int intentos = 0;
            int pasajeros;

            pasajeros = avion.getOrigen().aeropuerto.recogerPasajerosAvion(max);
            avion.setPasajerosActual(pasajeros);

            if (pasajeros > 0) {
                System.out.println(avion.getIdentificador() + " EMBARCANDO " + pasajeros + " pasajeros");
                Thread.sleep((int) (Math.random() * 2000) + 1001);
            }

            while (intentos < 3 && avion.getPasajerosActual() < max) {
                
                System.out.println(avion.getIdentificador() + " no esta lleno. Intentadolo llenar mas tarde. Capacidad = " + max + " Actuales = " + avion.getPasajerosActual() + " Intento numero " + (intentos+1));
                Thread.sleep((int) (Math.random() * 4000) + 1001);
                pasajeros = avion.getOrigen().aeropuerto.recogerPasajerosAvion(max - avion.getPasajerosActual());
                avion.setPasajerosActual(avion.getPasajerosActual() + pasajeros);

                if (pasajeros > 0) {
                    System.out.println(avion.getIdentificador() + " EMBARCANDO " + pasajeros + " pasajeros ");
                    Thread.sleep((int) (Math.random() * 2000) + 1001);
                }

                intentos++;
                
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(PuertaEmbarque.class.getName()).log(Level.SEVERE, null, ex);
        } 

    }

    private void puertaDesembarque(Avion avion) {
        try {
            
            if (avion.getPasajerosActual() > 0) {
                
                System.out.println(avion.getIdentificador() + " DESEMBARCANDO " + avion.getPasajerosActual() + " pasajeros");
                avion.getOrigen().aeropuerto.bajarPasajerosAvion(avion.getPasajerosActual());
                avion.setPasajerosActual(0);
                Thread.sleep((int) (Math.random() * 4000) + 1001);   
            }
            else{
                System.out.println(avion.getIdentificador() + " DESEMBARCANDO " + avion.getPasajerosActual() + " pasajeros. No se baja nadie.");
                
            }
        }catch (InterruptedException ex) {
                Logger.getLogger(PuertaEmbarque.class.getName()).log(Level.SEVERE, null, ex);
            }
    }


}
