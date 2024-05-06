/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

public class Pista {

    private Avion[] aviones = new Avion[4];
    private String nombreCiudad;

    private Semaphore pista1 = new Semaphore(1, true);
    private Semaphore pista2 = new Semaphore(1, true);
    private Semaphore pista3 = new Semaphore(1, true);
    private Semaphore pista4 = new Semaphore(1, true);
    private Semaphore[] pistas = {pista1, pista2, pista3, pista4};

    private JTextField jTextFieldPista1Aeropuerto;
    private JTextField jTextFieldPista2Aeropuerto;
    private JTextField jTextFieldPista3Aeropuerto;
    private JTextField jTextFieldPista4Aeropuerto;
    
    private PasoPistas pasoPistas;
    
    private TextLog logger;

    public Pista(JTextField jTextFieldPista1Aeropuerto, JTextField jTextFieldPista2Aeropuerto,
            JTextField jTextFieldPista3Aeropuerto, JTextField jTextFieldPista4Aeropuerto,
            String nombreCiudad, TextLog logger, PasoPistas pasoPistas) {
        this.jTextFieldPista1Aeropuerto = jTextFieldPista1Aeropuerto;
        this.jTextFieldPista2Aeropuerto = jTextFieldPista2Aeropuerto;
        this.jTextFieldPista3Aeropuerto = jTextFieldPista3Aeropuerto;
        this.jTextFieldPista4Aeropuerto = jTextFieldPista4Aeropuerto;
        this.nombreCiudad = nombreCiudad;
        this.logger = logger;
        this.pasoPistas = pasoPistas;
    }

    /**
     * Método que permite a un avión solicitar acceso a la pista para aterrizar
     * o despegar.
     *
     * @param avion Avión que solicita acceso a la pista.
     * @param paso Instancia de Paso para comprobar si el programa esta
     * detenido.
     */
    public void pedirPista(Avion avion, Paso paso) {

        try {
            if (avion.isEmbarque()) {
                paso.mirar();
                pasoPistas.mirar(avion.getNumero() % 4);
                pistas[avion.getNumero() % 4].acquire();
                logger.log("Avion " + avion.getIdentificador() + " tiene libre la PISTA " + (avion.getNumero() + 1) + " para despegar ", nombreCiudad);
                avion.getOrigen().getAeropuerto().getAreaRodaje().salirAreaRodaje(avion);
                aviones[avion.getNumero() % 4] = avion;
                imprimirArrayAviones(aviones, avion.getNumero() % 4);
                logger.log("Avion " + avion.getIdentificador() + " entrando PISTA " + (avion.getNumero() + 1) + " para despegar ", nombreCiudad);
                despegar(avion, paso);

            } else {
                paso.mirar();
                pasoPistas.mirar(avion.getNumero() % 4);
                while (!pistas[avion.getNumero() % 4].tryAcquire()) {
                    logger.log("Avion " + avion.getIdentificador() + " dando un rodeo para tener pista libre ", nombreCiudad);
                    Thread.sleep((int) (Math.random() * 4000) + 1001);
                }
                paso.mirar();
                pasoPistas.mirar(avion.getNumero() % 4);
                aviones[avion.getNumero() % 4] = avion;
                logger.log("Avion " + avion.getIdentificador() + " tiene libre la PISTA " + (avion.getNumero() + 1) + " para aterrizar ", nombreCiudad);
                imprimirArrayAviones(aviones, avion.getNumero() % 4);
                aterrizar(avion, paso);
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(Pista.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Método que gestiona el despegue de un avión desde la pista.
     *
     * @param avion Avión que realiza el despegue.
     * @param paso Instancia de Paso para comprobar si el programa esta
     * detenido.
     */
    private void despegar(Avion avion, Paso paso) {

        try {
            logger.log("Avion " + avion.getIdentificador() + " realizando verificaciones PISTA ", nombreCiudad);
            Thread.sleep((int) (Math.random() * 2000) + 1001);
            paso.mirar();
            logger.log("Avion " + avion.getIdentificador() + " despegando con " + avion.getPasajerosActual() + " pasajeros desde la PISTA " + (avion.getNumero() + 1), nombreCiudad);
            Thread.sleep((int) (Math.random() * 4000) + 1001);
            paso.mirar();
            logger.log("Avion " + avion.getIdentificador() + " ha dejado libre la PISTA " + (avion.getNumero() + 1), nombreCiudad);
            aviones[avion.getNumero() % 4] = null;
            imprimirArrayAviones(aviones, avion.getNumero() % 4);
            pistas[avion.getNumero() % 4].release();

        } catch (InterruptedException ex) {
            Logger.getLogger(Pista.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Método que gestiona el aterrizaje de un avión en la pista.
     *
     * @param avion Avión que realiza el aterrizaje.
     * @param paso Instancia de Paso para comprobar si el programa esta
     * detenido.
     */
    private void aterrizar(Avion avion, Paso paso) {

        try {
            imprimirArrayAviones(aviones, avion.getNumero() % 4);
            logger.log("Avion " + avion.getIdentificador() + " aterrizando con " + avion.getPasajerosActual() + " pasajeros en la PISTA " + (avion.getNumero() + 1), nombreCiudad);
            Thread.sleep((int) (Math.random() * 4000) + 1001);
            paso.mirar();
            aviones[avion.getNumero() % 4] = null;
            imprimirArrayAviones(aviones, avion.getNumero() % 4);
            logger.log("Avion " + avion.getIdentificador() + " ha dejado libre la PISTA " + (avion.getNumero() + 1), nombreCiudad);
            pistas[avion.getNumero() % 4].release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Pista.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Método que actualiza la impresion de los aviones en las pistas.
     *
     * @param arrayAviones Array de aviones que ocupan las pistas.
     * @param numeroPista Número de la pista.
     */
    public void imprimirArrayAviones(Avion[] arrayAviones, int numeroPista) {
        String stringAux = "";
        if (arrayAviones[numeroPista] != null) {
            stringAux = arrayAviones[numeroPista].getIdentificador();
        }
        numeroPista++;

        switch (numeroPista) {
            case 1:
                jTextFieldPista1Aeropuerto.setText(stringAux);
                break;
            case 2:
                jTextFieldPista2Aeropuerto.setText(stringAux);
                break;
            case 3:
                jTextFieldPista3Aeropuerto.setText(stringAux);
                break;
            case 4:
                jTextFieldPista4Aeropuerto.setText(stringAux);
                break;
            default:
                throw new AssertionError();
        }

    }

    public void cerrarPasoPistas(int x) {
        pasoPistas.cerrar(x);
    }

    public void abrirPasoPistas(int x) {
        pasoPistas.abrir(x);
    }
}
