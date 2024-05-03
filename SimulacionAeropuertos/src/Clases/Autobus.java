/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author ediso
 */
public class Autobus extends Thread {

    private Ciudad ciudad;

    private String identificador;
    private int pasajeros;
    private Paso paso;
    //private final int pasajerosMax = 50;

    public Autobus(String identificador, int numero, int pasajeros, Ciudad ciudad, Paso paso) {
        this.identificador = identificador + "-" + String.format("%04d", numero);
        this.pasajeros = pasajeros;
        this.ciudad = ciudad;
        this.paso = paso;
    }

    public void run() {
        while (true) {
            paso.mirar();
            ciudad.recogerPasajerosCiudadAutobus(this);
            paso.mirar();
            ciudad.irAeropuertoAutobus(this,paso);
            paso.mirar();
            ciudad.bajarPasajerosAlAeropuertoAutobus(this,paso);
            paso.mirar();
            ciudad.recogerPasajerosAeropuertoAutobus(this);
            paso.mirar();
            ciudad.irCiudadAutobus(this,paso);
            paso.mirar();
            ciudad.bajarPasajerosACiudadAutobus(this);
            
        }

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

    public Ciudad getCiudad() {
        return ciudad;
    }

}
