/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
// Esto es un cambio

import java.util.Random;

public class Avion extends Thread {

    private Ciudad origen;
    private Ciudad destino;
    private Ciudad aux;

    private String identificador;
    private int numero;
    private int pasajerosActual;
    private final int pasajerosMax;
    private boolean embarque;
    private int vuelos = 0;
    
    private Random random = new Random();
    private Paso paso;

    // Logger para registrar eventos en el Avion
    private TextLog logger;

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

        logger.log("Avion " + this.getIdentificador() + " creado ", this.getOrigen().getNombre());
        paso.mirar();
        origen.getAeropuerto().getHangar().entrarHangar(this);
        paso.mirar();
        origen.getAeropuerto().getHangar().salirHangar(this);

        while (true) {

            embarque = true; //indica que va a embarcar
            paso.mirar();

            origen.getAeropuerto().getAreaEstacionamiento().entrarAreaEstacionamiento(this);
            paso.mirar();
            //sale solo del area de estacionamiento cuando tiene una puerta de embarque disponible

            origen.getAeropuerto().getPuertaEmbarque().entrarPuerta(this, paso);
            paso.mirar();
            //sale solo de la puerta de embarque

            origen.getAeropuerto().getAreaRodaje().entrarAreaRodaje(this);
            paso.mirar();
            //sale solo del area de rodaje cuando consigue una pista disponible

            origen.getAeropuerto().getPista().pedirPista(this, paso);
            paso.mirar();
            //sale solo de la pista

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

            destino.getAeropuerto().getTaller().entrarTaller(this,paso);
            paso.mirar();
            destino.getAeropuerto().getTaller().salirTaller(this, paso);
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
