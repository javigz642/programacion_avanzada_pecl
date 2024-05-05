/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
// Esto es un cambio

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ediso
 */
public class Avion extends Thread {

    private Ciudad origen;
    private Ciudad destino;
    private Ciudad aux;
    private Paso paso;
    private TextLog logger;

    private String identificador;
    private int numero;
    private Random random = new Random();

    private int pasajerosActual;
    private final int pasajerosMax;
    private boolean embarque;
    private int vuelos = 0;

    public Avion(String identificador, int numero, Ciudad origen, Ciudad destino, Paso paso, TextLog logger) {
        this.identificador = identificador + "-" + String.format("%04d", numero);
        this.numero = numero;
        this.pasajerosMax = (int) (Math.random() * 201) + 100;
        this.origen = origen;
        this.destino = destino;
        this.paso = paso;
        this.logger = logger;
    }
    
    public void run() {

        try {

            logger.log("Avion " + this.getIdentificador() + " creado ", this.getOrigen().getNombre());
            paso.mirar();
            origen.getAeropuerto().getHangar().entrarHangar(this);
            paso.mirar();
            origen.getAeropuerto().getHangar().salirHangar(this);

            while (true) {

                embarque = true; //indica que va a embarcar

                Thread.sleep(0);
                paso.mirar();
                
                origen.getAeropuerto().getAreaEstacionamiento().entrarAreaEstacionamiento(this);
                paso.mirar();
                //origen.getAeropuerto().getAreaEstacionamiento().salirAreaEstacionamiento(this);
                //sale solo del area de estacionamiento cuando tiene una puerta de embarque disponible
                paso.mirar();

                origen.getAeropuerto().getPuertaEmbarque().entrarPuerta(this, paso);
                //sale solo de la puerta de embarque
                paso.mirar();

                origen.getAeropuerto().getAreaRodaje().entrarAreaRodaje(this);
                paso.mirar();
                //origen.getAeropuerto().getAreaRodaje().salirAreaRodaje(this);
                //sale solo del area de rodaje cuando consigue una pista disponible
                paso.mirar();

                origen.getAeropuerto().getPista().pedirPista(this, paso);
                //sale solo de la pista
                paso.mirar();

                origen.getAeropuerto().getAerovia().entrarAerovia(this);
                paso.mirar();
                origen.getAeropuerto().getAerovia().abandonarAerovia(this);
                paso.mirar();

                vuelos++;

                embarque = false; //indica que va a desembarcar

                destino.getAeropuerto().getPista().pedirPista(this, paso);
                paso.mirar();
                //sale solo de la pista

                destino.getAeropuerto().getAreaRodaje().entrarAreaRodaje(this);
                paso.mirar();
                //sale por si solo del area cuando tiene puerta para desembarcar

                destino.getAeropuerto().getPuertaEmbarque().entrarPuerta(this, paso);
                paso.mirar();
                //sale solo de la puerta

                destino.getAeropuerto().getAreaEstacionamiento().entrarAreaEstacionamiento(this);
                paso.mirar();
                //destino.getAeropuerto().getAreaEstacionamiento().salirAreaEstacionamiento(this);
                //sale solo al entrar al taller
                paso.mirar();

                destino.getAeropuerto().getTaller().entrarTaller(this);
                paso.mirar();
                destino.getAeropuerto().getTaller().salirTaller(this);
                paso.mirar();

                if (random.nextInt(2) == 0) {

                    destino.getAeropuerto().getHangar().reposar(this);
                    paso.mirar();
                    destino.getAeropuerto().getHangar().salirHangar(this);
                    paso.mirar();

                }

                aux = origen;
                origen = destino;
                destino = aux;
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(Avion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getIdentificador() {
        return identificador;
    }

    public int getNumero() {
        return numero;
    }

    public int getPasajerosMax() {
        return pasajerosMax;
    }

    public int getPasajerosActual() {
        return pasajerosActual;
    }

    public void setPasajerosActual(int pasajerosActual) {
        this.pasajerosActual = pasajerosActual;
    }

    public boolean isEmbarque() {
        return embarque;
    }

    public Ciudad getOrigen() {
        return origen;
    }

    public Ciudad getDestino() {
        return destino;
    }

    public int getVuelos() {
        return vuelos;
    }

}
