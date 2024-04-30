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

    private ArrayList<Avion> avionEmbarque = new ArrayList<>();
    private ArrayList<Avion> avionDesembarque = new ArrayList<>();
    private Avion[] avionesLibres = new Avion[4];

    
    private Semaphore puertaEmbarque = new Semaphore(1, true);
    private Semaphore puertaDesembarque = new Semaphore(1, true);
    
    private Semaphore puertaLibre1 = new Semaphore(1, true);
    private Semaphore puertaLibre2 = new Semaphore(1, true);
    private Semaphore puertaLibre3 = new Semaphore(1, true);
    private Semaphore puertaLibre4 = new Semaphore(1, true);
    private Semaphore[] puertasLibres = {puertaLibre1, puertaLibre2, puertaLibre3, puertaLibre4};

    public PuertaEmbarque(Aeropuerto aeropuerto) {

        this.aeropuerto = aeropuerto;
    }

    public void entrarPuerta(Avion avion) {

        try {
            if (avion.isEmbarque()) {

                if (puertaEmbarque.tryAcquire()) {
                    avionEmbarque.add(avion);
                    System.out.println(avion.getIdentificador() + " entrando Gate 1 (exclusiva Embarque)");
                    puertaEmbarque(avion);
                    avionEmbarque.remove(avion);
                    System.out.println(avion.getIdentificador() + " saliendo Gate 1 (exlclusiva Embarque) con " + avion.getPasajerosActual() + " pasajeros");
                    puertaEmbarque.release();

                } else {
                    puertasLibres[avion.getNumero()%4].acquire();
                    avionesLibres[avion.getNumero()%4] = avion;
                    System.out.println(avion.getIdentificador() + " entrando Gate " + (avion.getNumero()%4 + 3) + " para Embarque");
                    puertaEmbarque(avion);
                    System.out.println(avion.getIdentificador() + " saliendo Gate " + (avion.getNumero()%4 + 3) + " con " + avion.getPasajerosActual() + " pasajeros");
                    avionesLibres[avion.getNumero()%4] = null;
                    puertasLibres[avion.getNumero()%4].release();

                }
            } else {
                if (puertaDesembarque.tryAcquire()) {
                    aeropuerto.areaRodaje.salirAreaRodaje(avion); //tiene que tener una puerta de desembarque libre antes de avanzar
                    System.out.println(avion.getIdentificador() + " tiene Gate 2 libre. Yendo desde RODAJE hacia ESTACIONAMIENTO");
                    //Thread.sleep((int) (Math.random() * 2000) + 3001); //paso de Area de Rodaje a Desembarque
                    avionDesembarque.add(avion);
                    System.out.println(avion.getIdentificador() + " entrando Gate 2 (exclusiva Desembarque)");
                    puertaDesembarque(avion);
                    avionDesembarque.remove(avion);
                    System.out.println(avion.getIdentificador() + " saliendo Gate 2 (exclusiva Desembarque)");;
                    puertaDesembarque.release();

                } else {
                    puertasLibres[avion.getNumero()%4].acquire();
                    aeropuerto.areaRodaje.salirAreaRodaje(avion); //tiene que tener una puerta de desembarque libre antes de avanzar
                    System.out.println(avion.getIdentificador() + " tiene Gate " + (avion.getNumero()%4 + 3) + " libre. Yendo desde RODAJE hacia ESTACIONAMIENTO");
                    Thread.sleep((int) (Math.random() * 2000) + 3001); //paso de Area de Rodaje a Desembarque
                    avionesLibres[avion.getNumero()%4] = avion;;
                    System.out.println(avion.getIdentificador() + " entrando Gate " + (avion.getNumero()%4 + 3) + " para Desembarque de " + avion.getPasajerosActual() + " pasajeros");
                    puertaDesembarque(avion);
                    avionesLibres[avion.getNumero()%4] = null;
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

            pasajeros = aeropuerto.recogerPasajerosAvion(max);
            avion.setPasajerosActual(pasajeros);

            if (pasajeros > 0) {
                System.out.println(avion.getIdentificador() + " EMBARCANDO " + pasajeros + " pasajeros");
                Thread.sleep((int) (Math.random() * 2000) + 1001);
            }

            while (intentos < 2 && avion.getPasajerosActual() < max) {
                
                System.out.println(avion.getIdentificador() + " no esta lleno. Intentadolo llenar mas tarde. Capacidad = " + max + " Actuales = " + avion.getPasajerosActual() + " Intento nº " + (intentos++));
                Thread.sleep((int) (Math.random() * 4000) + 1001);
                pasajeros = aeropuerto.recogerPasajerosAvion(max - avion.getPasajerosActual());
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
                aeropuerto.bajarPasajerosAvion(avion.getPasajerosActual());
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
