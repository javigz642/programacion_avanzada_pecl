/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parte1;

/**
 *
 * @author ediso
 */
public class Autobus extends Thread {

    private String identificador;
    private int numero;
    private int pasajeros;
    private Ciudad ciudad;
    private Aeropuerto aeropuerto;

    public Autobus(String identificador, int numero, int pasajeros, Ciudad ciudad, Aeropuerto aeropuerto) {
        this.identificador = identificador +"-"+ String.format("%04d", numero);
        this.numero = numero;
        this.pasajeros = pasajeros;
        this.ciudad = ciudad;
        this.aeropuerto = aeropuerto;
    }

    //private final int pasajerosMax = 50;
    public void run() {
        
            ciudad.recogerPasajerosAutobus(this);
            ciudad.irAeropuertoAutobus(this);
            aeropuerto.bajarPasajerosAutobus(this);
            aeropuerto.recogerPasajerosAutobus(this);
            aeropuerto.irCiudadAutobus(this);
            ciudad.bajarPasajerosAutobus(this);
       
    }

    public int getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(int i) {
        pasajeros = i;
    }

    public String getIdentificador() {
        return identificador;
    }
}
