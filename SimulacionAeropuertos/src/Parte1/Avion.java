/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parte1;
// Esto es un cambio

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ediso
 */
public class Avion extends Thread{
    
    private Ciudad origen;
    private Ciudad destino;
    
    private String identificador;
    private int numero;
    
    private int pasajerosActual;
    private final int pasajerosMax;
    private boolean embarque;
    
    //Constructor NO definitivo!!
    public Avion(String identificador, int numero, Ciudad origen, Ciudad destino) {
        this.identificador = identificador +"-"+ String.format("%04d", numero);
        this.numero = numero;
        this.pasajerosMax = (int) (Math.random()*201) +100;
        this.origen = origen;
        this.destino = destino;
    }
    
    public void run(){
        
        try {
            origen.aeropuerto.hangar.entrarHangar(this);
            Thread.sleep(0);
            origen.aeropuerto.areaEstacionamiento.entrarArea(this);
            embarque = true;
            origen.aeropuerto.puertaEmbarque.entrarPuerta(this);
            origen.aeropuerto.areaRodaje.entrarAreaRodaje(this);
            origen.aeropuerto.pista.accederPista(this);
            origen.aeropuerto.aerovia.volar(this);
            embarque = false;
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Avion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public String getIdentificador() {
        return identificador;
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
    
    
    
    
    
    
    
    

    
    
    
    
    
}
