/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parte1;
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

    private String identificador;
    private int numero;
    Random random = new Random();

    private int pasajerosActual;
    private final int pasajerosMax;
    private boolean embarque;
    private int vuelos = 0;

    public Avion(String identificador, int numero, Ciudad origen, Ciudad destino, Paso paso) {
        this.identificador = identificador + "-" + String.format("%04d", numero);
        this.numero = numero;
        this.pasajerosMax = (int) (Math.random() * 201) + 100;
        this.origen = origen;
        this.destino = destino;
        this.paso = paso;
    }

    public void run() {

        try {
            
            System.out.println("Avion " + this.getIdentificador() + " creado");
            paso.mirar();
            origen.aeropuerto.hangar.entrarHangar(this);
            paso.mirar();
            origen.aeropuerto.hangar.salirHangar(this);

            while (true) {
                
                embarque = true; //indica que va a embarcar

                

                Thread.sleep(0);
                paso.mirar();
                origen.aeropuerto.areaEstacionamiento.entrarArea(this);
                paso.mirar();
                origen.aeropuerto.areaEstacionamiento.salirArea(this);
paso.mirar();
                origen.aeropuerto.puertaEmbarque.entrarPuerta(this);
                //sale solo de la puerta de embarque
paso.mirar();
                origen.aeropuerto.areaRodaje.entrarAreaRodaje(this);
                paso.mirar();
                origen.aeropuerto.areaRodaje.salirAreaRodaje(this);
paso.mirar();
                origen.aeropuerto.pista.pedirPista(this);
                //sale solo de la pista
paso.mirar();
                origen.aeropuerto.aerovia.entrarAerovia(this);
                paso.mirar();
                origen.aeropuerto.aerovia.abandonarAerovia(this);
paso.mirar();
                vuelos++;

                embarque = false; //indica que va a desembarcar

                destino.aeropuerto.pista.pedirPista(this);
                paso.mirar();
                //sale solo de la pista

                destino.aeropuerto.areaRodaje.entrarAreaRodaje(this);
                paso.mirar();
                //sale por si solo del area cuando tiene puerta para desembarcar

                destino.aeropuerto.puertaEmbarque.entrarPuerta(this);
                paso.mirar();
                //sale solo de la puerta

                destino.aeropuerto.areaEstacionamiento.entrarArea(this);
                paso.mirar();
                destino.aeropuerto.areaEstacionamiento.salirArea(this);
                paso.mirar();

                destino.aeropuerto.taller.entrarTaller(this);
                paso.mirar();
                destino.aeropuerto.taller.salirTaller(this);
                paso.mirar();

                if (random.nextInt(2) == 0) {

                    destino.aeropuerto.hangar.reposar(this);
                    paso.mirar();
                    destino.aeropuerto.hangar.salirHangar(this);
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
