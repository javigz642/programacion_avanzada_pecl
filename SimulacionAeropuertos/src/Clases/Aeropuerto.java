package Clases;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ediso
 */
public class Aeropuerto extends Thread {

    private int personasDentro = 5000;
    private String nombreCiudad;

    private Hangar hangar;
    private AreaEstacionamiento areaEstacionamiento;
    private Taller taller;
    private PuertaEmbarque puertaEmbarque;
    private AreaRodaje areaRodaje;
    private Pista pista;
    private Aerovia aerovia;
    private TextLog logger;

    private Semaphore control = new Semaphore(1, true);

    private JTextField jTextFieldGate1Aeropuerto;
    private JTextField jTextFieldGate2Aeropuerto;
    private JTextField jTextFieldGate3Aeropuerto;
    private JTextField jTextFieldGate4Aeropuerto;
    private JTextField jTextFieldGate5Aeropuerto;
    private JTextField jTextFieldGate6Aeropuerto;

    private JTextField jTextFieldNumeroPasajerosAeropuerto;

    public Aeropuerto(Hangar hangar, AreaEstacionamiento areaEstacionamiento, PuertaEmbarque puertaEmbarque, Taller taller,
            AreaRodaje areaRodaje, Pista pista, Aerovia aerovia,
            JTextField jTextFieldGate1Aeropuerto, JTextField jTextFieldGate2Aeropuerto,
            JTextField jTextFieldGate3Aeropuerto, JTextField jTextFieldGate4Aeropuerto,
            JTextField jTextFieldGate5Aeropuerto, JTextField jTextFieldGate6Aeropuerto,
            JTextField jTextFieldNumeroPasajerosAeropuerto, TextLog logger, String nombreCiudad
    ) {
        this.hangar = hangar;
        this.areaEstacionamiento = areaEstacionamiento;
        this.puertaEmbarque = puertaEmbarque;
        this.taller = taller;
        this.areaRodaje = areaRodaje;
        this.pista = pista;
        this.aerovia = aerovia;
        this.jTextFieldGate1Aeropuerto = jTextFieldGate1Aeropuerto;
        this.jTextFieldGate2Aeropuerto = jTextFieldGate2Aeropuerto;
        this.jTextFieldGate3Aeropuerto = jTextFieldGate3Aeropuerto;
        this.jTextFieldGate4Aeropuerto = jTextFieldGate4Aeropuerto;
        this.jTextFieldGate5Aeropuerto = jTextFieldGate5Aeropuerto;
        this.jTextFieldGate6Aeropuerto = jTextFieldGate6Aeropuerto;

        this.jTextFieldNumeroPasajerosAeropuerto = jTextFieldNumeroPasajerosAeropuerto;

        this.logger = logger;
        this.nombreCiudad = nombreCiudad;

        imprimirPasajerosEnAeropuerto(jTextFieldNumeroPasajerosAeropuerto, personasDentro);

    }

    public void recogerPasajerosAutobus(Autobus a) {
        int pasajerosParada;

        try {
            control.acquire();
            do {
                pasajerosParada = (int) (Math.random() * 51);
            } while (pasajerosParada > personasDentro);

            logger.log("Bus " + a.getIdentificador() + " esperando pasajeros en AEROPUERTO ", nombreCiudad);
            Thread.sleep((int) (Math.random() * 3000) + 2001);
            personasDentro -= pasajerosParada;
            if (personasDentro < 0) {
                System.out.println("america");
            }
            imprimirPasajerosEnAeropuerto(jTextFieldNumeroPasajerosAeropuerto, personasDentro);
            control.release();
            logger.log("Bus " + a.getIdentificador() + " recogiendo " + pasajerosParada + " pasajeros en AEROPUERTO ", nombreCiudad);
            a.setPasajeros(pasajerosParada);
        } catch (InterruptedException ex) {
            Logger.getLogger(Aeropuerto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void bajarPasajerosAutobus(Autobus a) {

        try {
            control.acquire();
            logger.log("Bus " + a.getIdentificador() + " bajando " + a.getPasajeros() + " pasajeros en AEROPUERTO ", nombreCiudad);
            personasDentro += a.getPasajeros();
            imprimirPasajerosEnAeropuerto(jTextFieldNumeroPasajerosAeropuerto, personasDentro);
            control.release();
            a.setPasajeros(0);
        } catch (InterruptedException ex) {
            Logger.getLogger(Aeropuerto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int recogerPasajerosAvion(int pasajeros) {

        int pasajerosCogidos = 0;

        try {
            control.acquire();
            if (personasDentro > pasajeros) {
                personasDentro -= pasajeros;
                imprimirPasajerosEnAeropuerto(jTextFieldNumeroPasajerosAeropuerto, personasDentro);
                pasajerosCogidos = pasajeros;
            } else {

                pasajerosCogidos = personasDentro;
                personasDentro -= pasajerosCogidos;
                imprimirPasajerosEnAeropuerto(jTextFieldNumeroPasajerosAeropuerto, personasDentro);
            }
            control.release();

        } catch (InterruptedException ex) {
            Logger.getLogger(Aeropuerto.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pasajerosCogidos;
    }

    public void bajarPasajerosAvion(int pasajeros) {
        try {
            control.acquire();
            personasDentro += pasajeros;
            imprimirPasajerosEnAeropuerto(jTextFieldNumeroPasajerosAeropuerto, personasDentro);
            control.release();

        } catch (InterruptedException ex) {
            Logger.getLogger(Aeropuerto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void imprimirPasajerosEnAeropuerto(JTextField j, int personas) {
        j.setText(personas + "");
    }

    public int getPersonasDentro() {
        int personasAux = personasDentro;
        return personasAux;
    }

    public Hangar getHangar() {
        return hangar;
    }

    public AreaEstacionamiento getAreaEstacionamiento() {
        return areaEstacionamiento;
    }

    public Taller getTaller() {
        return taller;
    }

    public PuertaEmbarque getPuertaEmbarque() {
        return puertaEmbarque;
    }

    public AreaRodaje getAreaRodaje() {
        return areaRodaje;
    }

    public Pista getPista() {
        return pista;
    }

    public Aerovia getAerovia() {
        return aerovia;
    }

}
