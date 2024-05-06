/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

public class Ciudad {

    private Aeropuerto aeropuerto;
    
    private ArrayList<Autobus> autobusesHaciaAeropuerto = new ArrayList<>();
    private ArrayList<Autobus> autobusesHaciaCiudad = new ArrayList<>();
    private int pasajerosParada;
    private String nombre;
    
    private Random random = new Random();
    
    private Semaphore SemAutobusesHaciaAeropuerto = new Semaphore(1);
    private Semaphore SemAutobusesHaciaCiudad = new Semaphore(1);
    
    private JTextField jTextFieldTransferAeropuertoAutobus;
    private JTextField jTextFieldTransferCiudadAutobus;
    
    private TextLog logger;

    public Ciudad(String nombre, Aeropuerto aeropuerto, JTextField jTextFieldTransferAeropuertoAutobus, JTextField jTextFieldTransferCiudadAutobus, TextLog logger) {
        this.nombre = nombre;
        this.aeropuerto = aeropuerto;
        this.jTextFieldTransferAeropuertoAutobus = jTextFieldTransferAeropuertoAutobus;
        this.jTextFieldTransferCiudadAutobus = jTextFieldTransferCiudadAutobus;
        this.logger = logger;
    }

    /**
     * Método para que un autobús recoja pasajeros en la ciudad.
     *
     * @param a Autobús que recoge pasajeros.
     * @param paso Paso utilizado para sincronización.
     */
    public void recogerPasajerosAutobusCiudad(Autobus a, Paso paso) {
        try {
            logger.log("Bus " + a.getIdentificador() + " esperando pasajeros en CIUDAD ", nombre);
            Thread.sleep((int) (Math.random() * 3000) + 2001);
            paso.mirar();
            pasajerosParada = random.nextInt(51);
            a.setPasajeros(pasajerosParada);
            logger.log("Bus " + a.getIdentificador() + " recogiendo " + pasajerosParada + " pasajeros en CIUDAD ", nombre);
        } catch (InterruptedException ex) {
            Logger.getLogger(Ciudad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método para que un autobús deje pasajeros en la ciudad.
     *
     * @param a Autobús que deja pasajeros.
     */
    public void bajarPasajerosAutobusCiudad(Autobus a) {

        logger.log("Bus " + a.getIdentificador() + " bajando " + a.getPasajeros() + " pasajeros en CIUDAD ", nombre);
        a.setPasajeros(0);

    }

    /**
     * Método para que un autobús recoja pasajeros en el aeropuerto.
     *
     * @param a Autobús que recoge pasajeros.
     */
    public void recogerPasajerosAutobusAeropuerto(Autobus a) {

        aeropuerto.recogerPasajerosAutobus(a);

    }

    /**
     * Método para que un autobús deje pasajeros en el aeropuerto.
     *
     * @param a Autobús que deja pasajeros.
     * @param paso Paso utilizado para sincronización.
     */
    public void bajarPasajerosAutobusAeropuerto(Autobus a, Paso paso) {

        try {
            SemAutobusesHaciaAeropuerto.acquire();
            autobusesHaciaAeropuerto.remove(a);
            SemAutobusesHaciaAeropuerto.release();
            aeropuerto.bajarPasajerosAutobus(a);
            SemAutobusesHaciaAeropuerto.acquire();
            paso.mirar();
            imprimirArrayAutobus(jTextFieldTransferAeropuertoAutobus, autobusesHaciaAeropuerto);
            SemAutobusesHaciaAeropuerto.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Ciudad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método para que un autobús se dirija hacia el aeropuerto.
     *
     * @param a Autobús que se dirige hacia el aeropuerto.
     * @param paso Paso utilizado para sincronización.
     */
    public void irAeropuertoAutobus(Autobus a, Paso paso) {

        try {
            SemAutobusesHaciaAeropuerto.acquire();
            autobusesHaciaAeropuerto.add(a);
            imprimirArrayAutobus(jTextFieldTransferAeropuertoAutobus, autobusesHaciaAeropuerto);
            paso.mirar();
            SemAutobusesHaciaAeropuerto.release();
            logger.log("Bus " + a.getIdentificador() + " yendo hacia AEROPUERTO con " + a.getPasajeros() + " pasajeros ", nombre);
            Thread.sleep((int) (Math.random() * 5000) + 5001);
            paso.mirar();
            SemAutobusesHaciaAeropuerto.acquire();
            autobusesHaciaAeropuerto.remove(a);
            SemAutobusesHaciaAeropuerto.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Ciudad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método para que un autobús se dirija hacia la ciudad.
     *
     * @param a Autobús que se dirige hacia la ciudad.
     * @param paso Paso utilizado para sincronización.
     */
    public void irCiudadAutobus(Autobus a, Paso paso) {

        try {
            SemAutobusesHaciaCiudad.acquire();
            paso.mirar();
            autobusesHaciaCiudad.add(a);
            imprimirArrayAutobus(jTextFieldTransferCiudadAutobus, autobusesHaciaCiudad);
            SemAutobusesHaciaCiudad.release();
            logger.log("Bus " + a.getIdentificador() + " yendo hacia CIUDAD con " + a.getPasajeros() + " pasajeros ", nombre);
            Thread.sleep((int) (Math.random() * 5000) + 5001);
            SemAutobusesHaciaCiudad.acquire();
            autobusesHaciaCiudad.remove(a);
            paso.mirar();
            imprimirArrayAutobus(jTextFieldTransferCiudadAutobus, autobusesHaciaCiudad);
            SemAutobusesHaciaCiudad.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Ciudad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método para imprimir la lista de autobuses hacia un destino en un
     * JTextField.
     *
     * @param jTextFieldDestino JTextField donde se imprimirá la lista de
     * autobuses.
     * @param arrayBuses ArrayList de autobuses hacia el destino.
     */
    public void imprimirArrayAutobus(JTextField jTextFieldDestino, ArrayList<Autobus> arrayBuses) {
        String stringAux = "";
        for (int i = 0; i < arrayBuses.size(); i++) {
            stringAux += arrayBuses.get(i).getIdentificador() + " / ";
        }
        jTextFieldDestino.setText(stringAux);
    }

    //Métodos getter
    public String getNombre() {
        return nombre;
    }

    public Aeropuerto getAeropuerto() {
        return aeropuerto;
    }

}
