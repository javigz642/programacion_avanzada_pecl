/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

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

    private String nombreCiudad;
    private TextLog logger;

    private JTextField jTextFieldGate1Aeropuerto;
    private JTextField jTextFieldGate2Aeropuerto;
    private JTextField jTextFieldGate3Aeropuerto;
    private JTextField jTextFieldGate4Aeropuerto;
    private JTextField jTextFieldGate5Aeropuerto;
    private JTextField jTextFieldGate6Aeropuerto;

    public PuertaEmbarque(String nombreCiudad, TextLog logger, JTextField jTextFieldGate1Aeropuerto, JTextField jTextFieldGate2Aeropuerto, JTextField jTextFieldGate3Aeropuerto, JTextField jTextFieldGate4Aeropuerto, JTextField jTextFieldGate5Aeropuerto, JTextField jTextFieldGate6Aeropuerto) {
        this.nombreCiudad = nombreCiudad;
        this.logger = logger;
        this.jTextFieldGate1Aeropuerto = jTextFieldGate1Aeropuerto;
        this.jTextFieldGate2Aeropuerto = jTextFieldGate2Aeropuerto;
        this.jTextFieldGate3Aeropuerto = jTextFieldGate3Aeropuerto;
        this.jTextFieldGate4Aeropuerto = jTextFieldGate4Aeropuerto;
        this.jTextFieldGate5Aeropuerto = jTextFieldGate5Aeropuerto;
        this.jTextFieldGate6Aeropuerto = jTextFieldGate6Aeropuerto;
    }

    public void entrarPuerta(Avion avion, Paso paso) {

        try {
            if (avion.isEmbarque()) {

                if (puertaEmbarque.tryAcquire()) {
                    logger.log("Avion " + avion.getIdentificador() + " tiene Gate 5 libre (exclusiva Embarque) ", nombreCiudad);
                    avion.getOrigen().getAeropuerto().getAreaEstacionamiento().salirAreaEstacionamiento(avion); //debe de tener una puerta de embarque antes de salir del area de estacionamiento
                    aviones[4] = avion;
                    imprimirArrayAviones(aviones, 5);
                    logger.log("Avion " + avion.getIdentificador() + " entrando Gate 5 (exclusiva Embarque)", nombreCiudad);
                    puertaEmbarque(avion);

                    aviones[4] = null;
                    paso.mirar();
                    imprimirArrayAviones(aviones, 5);
                    logger.log("Avion " + avion.getIdentificador() + " saliendo Gate 5 (exclusiva Embarque) con " + avion.getPasajerosActual() + " pasajeros ", nombreCiudad);
                    puertaEmbarque.release();

                } else {
                    puertasLibres[avion.getNumero() % 4].acquire();
                    logger.log("Avion " + avion.getIdentificador() + " tiene Gate " + (avion.getNumero()%4 + 3) + " libre para Embarque ", nombreCiudad);
                    avion.getOrigen().getAeropuerto().getAreaEstacionamiento().salirAreaEstacionamiento(avion);//debe de tener una puerta de embarque antes de salir del area de estacionamiento
                    aviones[avion.getNumero() % 4] = avion;
                    imprimirArrayAviones(aviones, avion.getNumero() % 4 + 1);
                    logger.log("Avion " + avion.getIdentificador() + " entrando Gate " + (avion.getNumero() % 4 + 3) + " para Embarque ", nombreCiudad);
                    puertaEmbarque(avion);

                    aviones[avion.getNumero() % 4] = null;
                    paso.mirar();
                    imprimirArrayAviones(aviones, avion.getNumero() % 4 + 1);
                    logger.log("Avion " + avion.getIdentificador() + " saliendo Gate " + (avion.getNumero() % 4 + 3) + " con " + avion.getPasajerosActual() + " pasajeros ", nombreCiudad);
                    puertasLibres[avion.getNumero() % 4].release();

                }
            } else {
                if (puertaDesembarque.tryAcquire()) {
                    logger.log("Avion " + avion.getIdentificador() + " tiene Gate 6 libre (exclusiva Desembarque). Yendo hacia Gate 6... ", nombreCiudad);
                    Thread.sleep((int) (Math.random() * 2000) + 3001); //paso de Area de Rodaje a Desembarque
                    avion.getDestino().getAeropuerto().getAreaRodaje().salirAreaRodaje(avion); //tiene que tener una puerta de desembarque libre antes de avanzar
                    aviones[5] = avion;
                    imprimirArrayAviones(aviones, 6);
                    logger.log("Avion " + avion.getIdentificador() + " entrando Gate 6 (exclusiva Desembarque) ", nombreCiudad);
                    puertaDesembarque(avion);

                    aviones[5] = null;
                    paso.mirar();
                    imprimirArrayAviones(aviones, 6);
                    logger.log("Avion " + avion.getIdentificador() + " saliendo Gate 6 (exclusiva Desembarque)", nombreCiudad);
                    puertaDesembarque.release();

                } else {
                    puertasLibres[avion.getNumero() % 4].acquire();
                    logger.log("Avion " + avion.getIdentificador() + " tiene Gate " + (avion.getNumero() % 4 + 3) + " libre para Desembarque. Yendo hacia Gate " + (avion.getNumero() % 4 + 3) + "... ", nombreCiudad);
                    Thread.sleep((int) (Math.random() * 2000) + 3001); //paso de Area de Rodaje a Desembarque
                    avion.getDestino().getAeropuerto().getAreaRodaje().salirAreaRodaje(avion); //tiene que tener una puerta de desembarque libre antes de avanzar
                    aviones[avion.getNumero() % 4] = avion;
                    imprimirArrayAviones(aviones, avion.getNumero() % 4 + 1);
                    logger.log("Avion " + avion.getIdentificador() + " entrando Gate " + (avion.getNumero() % 4 + 3) + " para Desembarque de " + avion.getPasajerosActual() + " pasajeros ", nombreCiudad);
                    puertaDesembarque(avion);

                    aviones[avion.getNumero() % 4] = null;
                    paso.mirar();
                    imprimirArrayAviones(aviones, avion.getNumero() % 4 + 1);
                    logger.log("Avion " + avion.getIdentificador() + " saliendo Gate " + (avion.getNumero() % 4 + 3), nombreCiudad);
                    puertasLibres[avion.getNumero() % 4].release();

                }
            }
            paso.mirar();

        } catch (InterruptedException ex) {
            Logger.getLogger(PuertaEmbarque.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void puertaEmbarque(Avion avion) {

        try {
            int max = avion.getPasajerosMax();
            int intentos = 0;
            int pasajeros;

            pasajeros = avion.getOrigen().getAeropuerto().recogerPasajerosAvion(max);
            avion.setPasajerosActual(pasajeros);

            if (pasajeros > 0) {

                logger.log("Avion " + avion.getIdentificador() + " EMBARCANDO " + pasajeros + " pasajeros ", nombreCiudad);
                Thread.sleep((int) (Math.random() * 2000) + 1001);
            }

            while (intentos < 3 && avion.getPasajerosActual() < max) {

                logger.log("Avion " + avion.getIdentificador() + " no esta lleno. Intentadolo llenar mas tarde. Capacidad = " + max + " Actuales = " + avion.getPasajerosActual() + " Intento numero " + (intentos + 1), nombreCiudad);
                Thread.sleep((int) (Math.random() * 4000) + 1001);
                pasajeros = avion.getOrigen().getAeropuerto().recogerPasajerosAvion(max - avion.getPasajerosActual());
                avion.setPasajerosActual(avion.getPasajerosActual() + pasajeros);

                if (pasajeros > 0) {

                    logger.log("Avion " + avion.getIdentificador() + " EMBARCANDO " + pasajeros + " pasajeros ", nombreCiudad);
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

                logger.log("Avion " + avion.getIdentificador() + " DESEMBARCANDO " + avion.getPasajerosActual() + " pasajeros ", nombreCiudad);
                avion.getDestino().getAeropuerto().bajarPasajerosAvion(avion.getPasajerosActual());
                avion.setPasajerosActual(0);
                Thread.sleep((int) (Math.random() * 4000) + 1001);
            } else {
                logger.log("Avion " + avion.getIdentificador() + " DESEMBARCANDO " + avion.getPasajerosActual() + " pasajeros. No se baja nadie ", nombreCiudad);

            }
        } catch (InterruptedException ex) {
            Logger.getLogger(PuertaEmbarque.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void imprimirArrayAviones(Avion[] arrayAviones, int numeroGate) {
        String stringAux = "";
        if (arrayAviones[numeroGate - 1] != null) {
            stringAux = arrayAviones[numeroGate - 1].getIdentificador();
        }

        switch (numeroGate) {
            case 1:
                jTextFieldGate1Aeropuerto.setText(stringAux);
                break;
            case 2:
                jTextFieldGate2Aeropuerto.setText(stringAux);
                break;
            case 3:
                jTextFieldGate3Aeropuerto.setText(stringAux);
                break;
            case 4:
                jTextFieldGate4Aeropuerto.setText(stringAux);
                break;
            case 5:
                jTextFieldGate5Aeropuerto.setText(stringAux);
                break;
            case 6:
                jTextFieldGate6Aeropuerto.setText(stringAux);
                break;
            default:
                throw new AssertionError();
        }

    }

}
