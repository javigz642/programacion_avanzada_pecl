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

    private String identificador;
    private int numero;
    Random random = new Random();

    private int pasajerosActual;
    private final int pasajerosMax;
    private boolean embarque;
    private int vuelos = 0;

    //Constructor NO definitivo!!
    public Avion(String identificador, int numero, Ciudad origen, Ciudad destino) {
        this.identificador = identificador + "-" + String.format("%04d", numero);
        this.numero = numero;
        this.pasajerosMax = (int) (Math.random() * 201) + 100;
        this.origen = origen;
        this.destino = destino;
    }

    public void run() {

        try {
            
            System.out.println("Avion " + this.getIdentificador() + " creado");
            
            origen.aeropuerto.hangar.entrarHangar(this);
            origen.aeropuerto.hangar.salirHangar(this);
                
            while (true) {
                
                embarque = true; //indica que va a embarcar

                

                Thread.sleep(0);

                origen.aeropuerto.areaEstacionamiento.entrarArea(this);
                origen.aeropuerto.areaEstacionamiento.salirArea(this);

                origen.aeropuerto.puertaEmbarque.entrarPuerta(this);
                //sale solo de la puerta de embarque

                origen.aeropuerto.areaRodaje.entrarAreaRodaje(this);
                origen.aeropuerto.areaRodaje.salirAreaRodaje(this);

                origen.aeropuerto.pista.pedirPista(this);
                //sale solo de la pista

                origen.aeropuerto.aerovia.entrarAerovia(this);
                origen.aeropuerto.aerovia.abandonarAerovia(this);

                vuelos++;

                embarque = false; //indica que va a desembarcar

                destino.aeropuerto.pista.pedirPista(this);
                //sale solo de la pista

                destino.aeropuerto.areaRodaje.entrarAreaRodaje(this);
                //sale por si solo del area cuando tiene puerta para desembarcar

                destino.aeropuerto.puertaEmbarque.entrarPuerta(this);
                //sale solo de la puerta

                destino.aeropuerto.areaEstacionamiento.entrarArea(this);
                destino.aeropuerto.areaEstacionamiento.salirArea(this);

                destino.aeropuerto.taller.entrarTaller(this);
                destino.aeropuerto.taller.salirTaller(this);

                if (random.nextInt(2) == 0) {

                    destino.aeropuerto.hangar.reposar(this);
                    destino.aeropuerto.hangar.salirHangar(this);

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
