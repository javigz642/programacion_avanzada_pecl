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
    private TextLog logger;

    public Autobus(String identificador, int numero, int pasajeros, Ciudad ciudad, Paso paso, TextLog logger) {
        this.identificador = identificador + "-" + String.format("%04d", numero);
        this.pasajeros = pasajeros;
        this.ciudad = ciudad;
        this.paso = paso;
        this.logger = logger;
    }

    public void run() {

        logger.log("Bus " + this.identificador + " creado ", ciudad.getNombre());
        while (true) {
            paso.mirar();
            ciudad.recogerPasajerosAutobusCiudad(this, paso);
            paso.mirar();
            ciudad.irAeropuertoAutobus(this, paso);
            paso.mirar();
            ciudad.bajarPasajerosAutobusAeropuerto(this, paso);
            paso.mirar();
            ciudad.recogerPasajerosAutobusAeropuerto(this);
            paso.mirar();
            ciudad.irCiudadAutobus(this, paso);
            paso.mirar();
            ciudad.bajarPasajerosAutobusCiudad(this);

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
