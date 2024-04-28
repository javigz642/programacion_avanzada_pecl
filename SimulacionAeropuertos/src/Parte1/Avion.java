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
    
    private Ciudad ciudad;
    
    private String identificador;
    private int numero;
    
    private int pasajerosActual;
    private final int pasajerosMax;
    private boolean embarque;
    
    //Constructor NO definitivo!!
    public Avion(String identificador, int numero, Ciudad ciudad) {
        this.identificador = identificador +"-"+ String.format("%04d", numero);
        this.numero = numero;
        this.pasajerosMax = (int) (Math.random()*201) +100;
        this.ciudad = ciudad;
    }
    
    public void run(){
        
        try {
            ciudad.aeropuerto.hangar.entrarHangar(this);
            Thread.sleep(0);
            ciudad.aeropuerto.areaEstacionamiento.entrarArea(this);
            embarque = true;
            ciudad.aeropuerto.puertaEmbarque.PuertaEmbarque(this);
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
    
    
    
    
    
    

    
    
    
    
    
}
